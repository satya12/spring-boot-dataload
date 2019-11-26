package com.iconectiv.dataload.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iconectiv.dataload.model.USPSSSDataZIPA;
import com.iconectiv.dataload.model.USPSGPDataZIPA;
import com.iconectiv.dataload.repository.USPSDataRepository;
import com.iconectiv.dataload.repository.USPSGeoPlaceDataRepository;
import com.iconectiv.dataload.util.ConvertCsvToJson;

@Service
public class USPSDataLoadProcess {

	public static Logger logger = LoggerFactory.getLogger(USPSDataLoadProcess.class);

	private String inputFilePattern;
	private static String inputFiles;
	private static String outputfile;
	private static String savefile;
	ObjectMapper mapper = new ObjectMapper();

	AtomicInteger gpKeyAtomicInteger = new AtomicInteger(1);
	AtomicInteger recNoAtomicInteger = new AtomicInteger(1);

	@Autowired
	USPSDataRepository uspsDataRepo;

	@Autowired
	USPSGeoPlaceDataRepository geoRepo;
	
	@Autowired
	ConvertCsvToJson convertCsvToJson;
	
	@Value("${usps.inputFiles}")
    public void setInputFile(String value) {
        this.inputFiles = value;
    } 
	
	@Value("${usps.inputFilePattern}")
    public void setInputFilePattern(String value) {
        this.inputFilePattern = value;
    } 
	
	@Value("${usps.outputfile}")
    public void setOutputfile(String value) {
        this.outputfile = value;
    } 

	@Value("${usps.savefile}")
    public void setSavefile(String value) {
        this.savefile = value;
    } 
	
	public static void main(String[] args) {
		logger.info("Started the USPS load process...");
		USPSDataLoadProcess uspsload = new USPSDataLoadProcess();

		logger.info("Extracting USPS load data...");
		uspsload.processDataFromFileAndSave();
	}

	public void processDataFromFileAndSave() {

		logger.debug("The input file directory : " + inputFiles);

		// Calling the ConvertCsvToJson::JsonFormatedObjects
		try (Stream<Path> filePathStream=Files.walk(Paths.get(inputFiles))) {
			filePathStream.forEach(filePath -> {
				if (Files.isRegularFile(filePath) && filePath.toString().endsWith(inputFilePattern)) {
					logger.info("The input file directory : " + filePath);

					List<USPSSSDataZIPA> dataSet = null;
					
					try {
						//listObj = (List<USPSSSDataZIPA>)convertCsvToJson.JsonFormatedObjects(filePath.toString());
						
						List<Object> listObj = convertCsvToJson.JsonFormatedObjects(filePath.toString());
						
						listObj.stream().forEach(o -> dataSet.add((USPSSSDataZIPA)o));
						
						this.processData(dataSet);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Exception occoured.." + e.getMessage());
					}
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("IOException occoured.." + e1.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error("Exception occoured.." + ex.getMessage());
		}
		
	}
	
	public void processData(List<USPSSSDataZIPA> formattedData) {

		int recNo = 1;

		JSONObject obj = new JSONObject();

		Set<USPSSSDataZIPA> ssDataSet = new HashSet<>();
		Set<USPSGPDataZIPA> geoDataSet = new HashSet<>();
		USPSGPDataZIPA gpData = null;
		
		for (USPSSSDataZIPA ssData : formattedData) {
			logger.debug("\nList item # " + recNo + ": \n");

			gpData = new USPSGPDataZIPA();
			gpData.setStateAbb(ssData.getStateAbb());
			gpData.setMcdName(ssData.getMcdName());
			gpData.setTownName(ssData.getTownName());

			geoDataSet.add(gpData);
						
			logger.debug("Data from object: " + ssData.toString());
			ssDataSet.add(ssData);

			recNo++;
		}
		
		logger.info("Total records in file : " + recNo);
		
		logger.info("geoSet size : " + geoDataSet.size());
		
		logger.debug("Total geoSet unique records : " + geoDataSet.toString());
	
		logger.info("USPSDataSet size : " + ssDataSet.size());
		
		logger.debug("Final USPSDataSet records: " + ssDataSet.toString());
		
		//Update the geoSet record key and the same matching records update in USPSDataSet. Aslo, save Geo place and USPS street segment data
		this.updateGPKeyAndSaveData(geoDataSet, ssDataSet);
		
	}
	
	public void updateGPKeyAndSaveData(final Set<USPSGPDataZIPA> geoSet, final Set<USPSSSDataZIPA> ssSet){
		
		logger.info("In updateGPKey method---");
		
		int gpKey = 0;
		for (USPSGPDataZIPA geoRec : geoSet) { 
			gpKey = gpKeyAtomicInteger.getAndIncrement();
			geoRec.setGpKey(gpKey);
			geoRec.setTownNo(gpKey);
			logger.debug("Geo Record: " + geoRec.toString());
			
			ssSet.stream().filter(o -> o.getMcdName().equals(geoRec.getMcdName() ) && 
					                        o.getStateAbb().equals(geoRec.getStateAbb() ) && 
					                        o.getTownName().equals(geoRec.getTownName() ) )
							   .forEach( o -> {
								   //logger.info("Match found, Rec no: " + o.getRecNum());
								   o.setGpKey(geoRec.getGpKey());
								   o.setRecNum(recNoAtomicInteger.getAndIncrement());
							   	});
		}
		
		logger.info("USPSgeoSet size after update : " + geoSet.size());
		logger.info("USPSSSDataSet size after update : " + ssSet.size());
		
		logger.info("File saving to file ... " + savefile);
		if(savefile.equalsIgnoreCase("true")) {
			logger.info("The final output file saving in ... " + outputfile);
			try (FileWriter file = new FileWriter(outputfile) ) {
				mapper.writeValue(file, ssSet);
	
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Exception occoured.." + e.getMessage());
	
			}
		}
		
		logger.info("Calling GEO place repo to save the data in batches, each batch 10000 records... see details in property file");
		geoRepo.saveAll(geoSet);
		geoRepo.flush();	

		logger.info("Calling USPS Street repo to save the data in batches, each batch 10000 records... see details in property file");
		uspsDataRepo.saveAll(ssSet);
		uspsDataRepo.flush();
		
	}

}


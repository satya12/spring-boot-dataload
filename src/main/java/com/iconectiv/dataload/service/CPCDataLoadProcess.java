package com.iconectiv.dataload.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
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

import com.iconectiv.dataload.model.CPCStreetSegData;
import com.iconectiv.dataload.model.CPCGeoPlaceData;
import com.iconectiv.dataload.repository.CPCDataRepository;
import com.iconectiv.dataload.repository.CPCGeoPlaceDataRepository;
import com.iconectiv.dataload.util.DataFileReader;

@Service
public class CPCDataLoadProcess {

	public static Logger logger = LoggerFactory.getLogger(CPCDataLoadProcess.class);

	private String inputFilePattern;
	private static String inputFiles;
	private static String propertyFile;
	private static String outputfile;
	private static String savefile;
	ObjectMapper mapper = new ObjectMapper();

	AtomicInteger gpKeyAtomicInteger = new AtomicInteger(1);
	AtomicInteger recNoAtomicInteger = new AtomicInteger(1);

	@Autowired
	CPCDataRepository cpcDataRepo;

	@Autowired
	CPCGeoPlaceDataRepository geoRepo;
	
	@Autowired
	DataFileReader dataFileReader;
	
	@Value("${cpc.inputFiles}")
    public void setInputFile(String value) {
        this.inputFiles = value;
    } 
	
	@Value("${cpc.inputFilePattern}")
    public void setInputFilePattern(String value) {
        this.inputFilePattern = value;
    } 
	
	@Value("${cpc.propertyFile}")
    public void setPropertyFile(String value) {
        this.propertyFile = value;
    } 
	
	@Value("${cpc.outputfile}")
    public void setOutputfile(String value) {
        this.outputfile = value;
    } 

	@Value("${cpc.savefile}")
    public void setSavefile(String value) {
        this.savefile = value;
    } 
	
	public static void main(String[] args) {
		logger.info("Started the CPC load process...");
		CPCDataLoadProcess cpcload = new CPCDataLoadProcess();

		logger.info("Extracting CPC load data...");
		cpcload.processDataFromFileAndSave();
	}

	public void processDataFromFileAndSave() {

		logger.debug("The input file directory : " + inputFiles);
		logger.debug("The property file: " + propertyFile);

		// Calling the DataFileReader::readFixedLengthFile
		try (Stream<Path> filePathStream=Files.walk(Paths.get(inputFiles))) {
			filePathStream.forEach(filePath -> {
				if (Files.isRegularFile(filePath) && filePath.toString().endsWith(inputFilePattern)) {
					logger.info("The input file directory : " + filePath);

					List<Map<String, String>> formattedData = new ArrayList<Map<String, String>>();
					try {
						formattedData = dataFileReader.readFixedLengthFile(filePath.toString(), propertyFile);
						this.processData(formattedData);
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
	
	public void processData(List<Map<String, String>> formattedData) {

		int recNo = 1;

		JSONObject obj = new JSONObject();

		CPCStreetSegData CPCDataVO = null;
		Set<CPCStreetSegData> CPCDataSet = new HashSet<>();
		
		Set<CPCGeoPlaceData> geoSet = new HashSet<>();
		CPCGeoPlaceData CPCGeoPlaceDataVO = null;
		
		for (Map <String, String> hm : formattedData) {
			logger.debug("\nList item # " + recNo + ": \n");

			//obj.put("recNum",recNo);
			for ( Map.Entry<String, String> entry : hm.entrySet() )
			{
				String key = entry.getKey() != null ? entry.getKey().trim() : "";
				String value = entry.getValue() != null ? entry.getValue().trim() : "";
				logger.debug( "Key : <" + key + "> Value : <" + value + ">");
				obj.put(key,value);
			}
			
			logger.debug("List item object: " + obj); 
			try {
				CPCDataVO = mapper.readValue(obj.toString(), CPCStreetSegData.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			CPCGeoPlaceDataVO = new CPCGeoPlaceData();
			CPCGeoPlaceDataVO.setStateAbb(CPCDataVO.getStateAbb());
			CPCGeoPlaceDataVO.setMcdName(CPCDataVO.getMcdName());
			CPCGeoPlaceDataVO.setTownName(CPCDataVO.getTownName());

			geoSet.add(CPCGeoPlaceDataVO);
						
			logger.debug("Data from object: " + CPCDataVO.toString());
			CPCDataSet.add(CPCDataVO);

			recNo++;
		}
		
		logger.info("Total records in file : " + recNo);
		
		logger.info("geoSet size : " + geoSet.size());
		
		logger.debug("Total geoSet unique records : " + geoSet.toString());
	
		logger.info("CPCDataSet size : " + CPCDataSet.size());
		
		logger.debug("Final CPCDataSet records: " + CPCDataSet.toString());
		
		//Update the geoSet record key and the same matching records update in CPCDataSet. Aslo, save Geo place and CPC street segment data
		this.updateGPKeyAndSaveData(geoSet, CPCDataSet);
		
	}
	
	public void updateGPKeyAndSaveData(final Set<CPCGeoPlaceData> geoSet, final Set<CPCStreetSegData> CPCDataSet){
		
		logger.info("In updateGPKey method---");
		
		int gpKey = 0;
		for (CPCGeoPlaceData geoRec : geoSet) { 
			gpKey = gpKeyAtomicInteger.getAndIncrement();
			geoRec.setGpKey(gpKey);
			geoRec.setTownNo(gpKey);
			logger.debug("Geo Record: " + geoRec.toString());
			
			CPCDataSet.stream().filter(o -> o.getMcdName().equals(geoRec.getMcdName() ) && 
					                        o.getStateAbb().equals(geoRec.getStateAbb() ) && 
					                        o.getTownName().equals(geoRec.getTownName() ) )
							   .forEach( o -> {
								   //logger.info("Match found, Rec no: " + o.getRecNum());
								   o.setGpKey(geoRec.getGpKey());
								   o.setRecNum(recNoAtomicInteger.getAndIncrement());
							   	});
			//gpKey++;
		}
		
		logger.info("geoSet size after update : " + geoSet.size());
		logger.info("CPCDataSet size after update : " + CPCDataSet.size());
		
		logger.info("File saving to file ... " + savefile);
		if(savefile.equalsIgnoreCase("true")) {
			logger.info("The final output file saving in ... " + outputfile);
			try (FileWriter file = new FileWriter(outputfile) ) {
				mapper.writeValue(file, CPCDataSet);
	
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Exception occoured.." + e.getMessage());
	
			}
		}
		
		logger.info("Calling GEO place repo to save the data in batches, each batch 10000 records... see details in property file");
		geoRepo.saveAll(geoSet);
		geoRepo.flush();	
//		for (CPCGeoPlaceData gpvo : geoSet) { 
//			logger.debug("Saving CPCGeoPlaceData object : " + gpvo.toString());
//			geoRepo.save(gpvo);
//			geoRepo.flush();		
//		}

		logger.info("Calling CPC Street repo to save the data in batches, each batch 10000 records... see details in property file");
		cpcDataRepo.saveAll(CPCDataSet);
		cpcDataRepo.flush();
//		for (CPCStreetSegData cpcvo : CPCDataSet) { 
//			logger.debug("Saving CPCStreetSegData object : " + cpcvo.toString());
//			cpcDataRepo.save(cpcvo);
//			cpcDataRepo.flush();	
//		}
		
	}

}


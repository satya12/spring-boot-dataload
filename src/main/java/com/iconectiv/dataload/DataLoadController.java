package com.iconectiv.dataload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iconectiv.dataload.service.CPCDataLoadProcess;
import com.iconectiv.dataload.service.USPSDataLoadProcess;

@Component
public class DataLoadController implements CommandLineRunner {

	public static Logger logger = LoggerFactory.getLogger(DataLoadController.class);

	public static final String CPC_PROCESS = "CPC";
	public static final String USPS_PROCESS = "USPS";
	public static final String NAVTEQ_PROCESS = "NAVTEQ";
	public static final String MULTINET_USA_PROCESS = "ML-USA";
	public static final String MULTINET_CANADA_PROCESS = "ML-CANADA";

	@Autowired
	CPCDataLoadProcess cpcService;
	
	@Autowired
	USPSDataLoadProcess uspsService;

	@Value("${dataLoadName}")
	private String processName;
	
	@Override
	public void run(String...args) throws Exception {

		if(processName == null || processName.equalsIgnoreCase("")) {
			logger.info("Missing input - Usage : java -jar dataload-batch-0.0.1-SNAPSHOT.jar --dataLoadName=<CPC|USPS|NAVTEQ|ML-USA|ML-CANADA>");
			System.exit(0);
		} 
		
		logger.info("Data load process started for : " + processName);
		
		if(processName.equalsIgnoreCase(CPC_PROCESS)) {
			cpcService.processDataFromFileAndSave();
		
		} else if(processName.equalsIgnoreCase(USPS_PROCESS)) {
			uspsService.processDataFromFileAndSave();
			
		} else if(processName.equalsIgnoreCase(NAVTEQ_PROCESS)) {
			
		} else if(processName.equalsIgnoreCase(MULTINET_USA_PROCESS)) {
			
		} else if(processName.equalsIgnoreCase(MULTINET_CANADA_PROCESS)) {
			
		} else {
			logger.info("Not a valid Data load process started for : " + processName);
			logger.info("Missing input - Usage : java -jar dataload-batch-0.0.1-SNAPSHOT.jar --dataLoadName=<CPC|USPS|NAVTEQ|ML-USA|ML-CANADA>");
		}
		
		logger.info("Data load process completed for : " + processName);

	}
}

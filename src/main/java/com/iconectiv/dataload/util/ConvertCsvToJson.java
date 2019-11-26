package com.iconectiv.dataload.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Component
public class ConvertCsvToJson {

	public static Logger logger = LoggerFactory.getLogger(ConvertCsvToJson.class);
	
	public static void main(String args[]) throws Exception {

	}

	public List JsonFormatedObjects (String inputFile) {
		List<Object> listObj = null;
		File input = new File(inputFile);
		File output = new File(inputFile + ".json");

		try {
			CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
			CsvMapper csvMapper = new CsvMapper();

			// Read data from CSV file
			listObj = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();

			ObjectMapper mapper = new ObjectMapper();

			// Write JSON formated data to output.json file
			logger.info("The output file saving as " + inputFile + ".json");
			mapper.writerWithDefaultPrettyPrinter().writeValue(output, listObj);
			// Write JSON formated data to stdout
			//logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listObj));		

		} catch (Exception e) {
			logger.error("Exception occoured.." + e.getMessage());
		}
		return listObj;
	}
}

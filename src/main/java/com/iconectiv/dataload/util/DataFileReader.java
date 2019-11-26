package com.iconectiv.dataload.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DataFileReader {

	public static Logger logger = LoggerFactory.getLogger(DataFileReader.class);

	public DataFileReader() {

	}

	public List<Map<String, String>> readFixedLengthFile(String inputFile, String propertyFile) throws Exception {

		// Create subsequent File and FileInputStream objects from the input data file
		File file = new File(inputFile);
		FileInputStream fstream = new FileInputStream(inputFile);
		JSONParser parser = new JSONParser();
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		
		if (file.exists()) {
			logger.debug("File already exists: file is -> " + file.toString());

			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			// Read Input Data File Line By Line
			while ((strLine = br.readLine()) != null)   {

				logger.debug("Reading line -><" + strLine + ">");
				Map<String, String> hm = new HashMap<String, String>();

				try (Reader reader = new FileReader(propertyFile)) {
					JSONObject jsonObject = (JSONObject) parser.parse(reader);

					logger.debug("Json return object: " + jsonObject);

					for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
						String key = (String) iterator.next();
						String value = (String) jsonObject.get(key);
						String[] strArray = value.split(",");
						hm.put(key, strLine.substring(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1])));						
					}

				} catch (IOException e) {
					e.printStackTrace();
					logger.error("IOException occoured.." + e.getMessage());
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error("Exception occoured.." + e.getMessage());
				}

				arrayList.add(hm);

			}
			in.close(); 
		}

		return arrayList;
	}
}

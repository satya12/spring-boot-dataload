package com.iconectiv.dataload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.iconectiv.dataload.repository")
@SpringBootApplication
public class DataloadBatchApplication {

	public static Logger logger = LoggerFactory.getLogger(DataloadBatchApplication.class);

	public static void main(String[] args) {
		
		logger.info("Data load process started ...");
		SpringApplication.run(DataloadBatchApplication.class, args);

	}

}

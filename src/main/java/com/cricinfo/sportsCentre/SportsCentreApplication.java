package com.cricinfo.sportsCentre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.cricinfo.sportsCentre"})
public class SportsCentreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsCentreApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager handlingTransactional(MongoDatabaseFactory dbfactory){
		return new MongoTransactionManager(dbfactory);
	}

}

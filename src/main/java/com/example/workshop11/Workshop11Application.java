package com.example.workshop11;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop11Application {

	//logger to see if things are working
	private static final Logger logger = LoggerFactory.getLogger
	(Workshop11Application.class);
	
	//default port-number
	private static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {
		logger.info("main method started ......");

		//initializ the spring app
		SpringApplication app = new SpringApplication(Workshop11Application.class);
		
		//workshop 11 is asking for something like this mvn spring-boot:run -Dspring-boot.run.arguments=--port=8082

		//read args array and check for "port" paramenter

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		
		//returns a list of all key value pairs from command line
		List opsValues = appArgs.getOptionValues("port");

		String portNumber = null;
		
		//checks if theres an argument, executes code otherwise
			if(opsValues == null || opsValues.get(0) == null){
				
				//reads port number from env variables
				portNumber = System.getenv("PORT");

				//port number is default port otherwise if no env variable
				if(portNumber == null){
					portNumber = DEFAULT_PORT;
				}
			} else{
				//passing port number from commmand line
				portNumber = (String) opsValues.get(0);
			}

			if (portNumber != null){
				//setting port number iin the spring-boot config
				app.setDefaultProperties(Collections.singletonMap("server.port",portNumber));
			}
		app.run(args);
	}

}

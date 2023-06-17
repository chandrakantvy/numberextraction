package com.assigment.numberextraction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.assigment.numberextraction.models.Request;
import com.assigment.numberextraction.models.Response;
import com.assigment.numberextraction.service.ExtractNumberService;


@RestController
@RequestMapping("/api")
public class NumberExtractionController {
	
	private static final Logger logger = LoggerFactory.getLogger(NumberExtractionController.class);
	
	@Autowired
	ExtractNumberService extractNumberService;
	
	@PostMapping("/numbers")
	public Response extractNumbers(@RequestBody Request request) {
		logger.info("Extracting Numbers from text...");
		Response response = extractNumberService.extractNumbers(request);
		return response;
	}
 	
	
}

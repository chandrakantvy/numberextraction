package com.assigment.numberextraction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.assigment.numberextraction.models.ExtractedNumber;
import com.assigment.numberextraction.models.Request;
import com.assigment.numberextraction.models.Response;

@SpringBootTest
public class ExtractNumberServiceTests {
	
	@InjectMocks
	ExtractNumberService extractNumberService;
	
	@Test
	public void extractNumbers1() {
		String text = "My weight is 70kg";
		Request request = new Request("test1", text);
		Response actualResponse = extractNumberService.extractNumbers(request);
		
		ExtractedNumber extractedNumber1 = new ExtractedNumber("70", Long.parseLong("70"), 13, 15);
		List<ExtractedNumber> extractedNumbers = new ArrayList<>();
		extractedNumbers.add(extractedNumber1);
		Response expectedResponse = new Response("test1", extractedNumbers);
		assertEquals(expectedResponse.getId(), actualResponse.getId());
		
		assertEquals("70", actualResponse.getExtractedNumbers().get(0).getExtractedText());
		assertEquals(70, actualResponse.getExtractedNumbers().get(0).getExtractedValue());
		assertEquals(13, actualResponse.getExtractedNumbers().get(0).getStartPosition());
		assertEquals(15, actualResponse.getExtractedNumbers().get(0).getEndPosition());

	}
	
	
	@Test
	public void extractNumbers2() {
		String text = "My weight is in the range 70.5-71.5kg ";
		Request request = new Request("test2", text);
		Response actualResponse = extractNumberService.extractNumbers(request);
		
		ExtractedNumber extractedNumber1 = new ExtractedNumber("70.5", 70.5, 22, 26);
        ExtractedNumber extractedNumber2 = new ExtractedNumber("71.5", 71.5, 27, 31);
        List<ExtractedNumber> extractedNumbers = new ArrayList<>();
        extractedNumbers.add(extractedNumber1);
        extractedNumbers.add(extractedNumber2);
        
		Response expectedResponse = new Response("test2", extractedNumbers);
		assertEquals(expectedResponse.getId(), actualResponse.getId());
		assertEquals(2, actualResponse.getExtractedNumbers().size());

	}
	
	
	@Test
	public void extractNumbers3() {
		String text = "";
		Request request = new Request("test3", text);
		Response actualResponse = extractNumberService.extractNumbers(request);
		
		ExtractedNumber extractedNumber1 = new ExtractedNumber("", 0, 0, 0);
        List<ExtractedNumber> extractedNumbers = new ArrayList<>();
        extractedNumbers.add(extractedNumber1);        
        
		Response expectedResponse = new Response("test3", extractedNumbers);
		assertEquals(expectedResponse.getId(), actualResponse.getId());
		assertEquals(0, actualResponse.getExtractedNumbers().size());

	}
	
	
	@Test
	public void extractNumbers4() {
		String text = "Hey there";
		Request request = new Request("test4", text);
		Response actualResponse = extractNumberService.extractNumbers(request);
		
		ExtractedNumber extractedNumber1 = new ExtractedNumber("", 0, 0, 0);
        List<ExtractedNumber> extractedNumbers = new ArrayList<>();
        extractedNumbers.add(extractedNumber1);
		Response expectedResponse = new Response("test4", extractedNumbers);
		assertEquals(expectedResponse.getId(), actualResponse.getId());
		assertEquals(0, actualResponse.getExtractedNumbers().size());

	}
	
	@Test
	public void extractNumbers5() {
		String text = "Hey there75^ I have $199.8";
		Request request = new Request("test5", text);
		Response actualResponse = extractNumberService.extractNumbers(request);
		
		ExtractedNumber extractedNumber1 = new ExtractedNumber("75",Long.parseLong("70"), 9, 10);
		ExtractedNumber extractedNumber2 = new ExtractedNumber("199.8", 199.8, 21, 25);
        List<ExtractedNumber> extractedNumbers = new ArrayList<>();
        extractedNumbers.add(extractedNumber1);
        extractedNumbers.add(extractedNumber2);
		Response expectedResponse = new Response("test5", extractedNumbers);
		assertEquals(expectedResponse.getId(), actualResponse.getId());
		assertEquals(2, actualResponse.getExtractedNumbers().size());
	}
	
	@Test
	public void extractNumbers6() {
		String text = "my ip address is 192.168.01.01";
		Request request = new Request("test6", text);
		Response actualResponse = extractNumberService.extractNumbers(request);
		
		ExtractedNumber extractedNumber1 = new ExtractedNumber("192",Long.parseLong("192"), 17, 19);
		ExtractedNumber extractedNumber2 = new ExtractedNumber("168", Long.parseLong("168"), 21, 23);
		ExtractedNumber extractedNumber3 = new ExtractedNumber("01",Long.parseLong("01") , 25, 26);
		ExtractedNumber extractedNumber4 = new ExtractedNumber("01", Long.parseLong("01"), 28, 29);
        List<ExtractedNumber> extractedNumbers = new ArrayList<>();
        extractedNumbers.add(extractedNumber1);
        extractedNumbers.add(extractedNumber2);
        extractedNumbers.add(extractedNumber3);
        extractedNumbers.add(extractedNumber4);

        Response expectedResponse = new Response("test6", extractedNumbers);
		assertEquals(expectedResponse.getId(), actualResponse.getId());
		assertEquals(4, actualResponse.getExtractedNumbers().size());
	}
	
	@Test
	public void countPeriodsTest1() {
		int actualPeriodCount = extractNumberService.countChar("123.5.6", '.');
		assertEquals(2, actualPeriodCount);
	}
	
	@Test
	public void countPeriodsTest2() {
		int actualPeriodCount = extractNumberService.countChar("123,6", '.');
		assertEquals(0, actualPeriodCount);
	}

}

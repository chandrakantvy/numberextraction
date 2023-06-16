package com.assigment.numberextraction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.assigment.numberextraction.models.ExtractedNumber;
import com.assigment.numberextraction.models.Request;
import com.assigment.numberextraction.models.Response;


@Service
public class ExtractNumberService {
	
	private final static String firstDigitRegex = "\\d";
	private final static String lastNonDigitRegex = "[^0-9,.]";
	private final static String lastDigitPatternRegex = "\\d(?=[^\\d]*$)";

	public Response extractNumbers(Request request) {
		List<ExtractedNumber> extractedNumbers = new ArrayList<>();
		String text = request.getText();
		
		int len = text.length();
		
		// subsequence will be combination of chars '0'-'9', ',', '.'
		// regex for finding the first digit of a subsequence
        Pattern firstDigitPattern = Pattern.compile(firstDigitRegex);
        Matcher firstDigitMatcher = firstDigitPattern.matcher(text);

        // regex for finding the first char not digit, non comma, non period of a 
        // substring after first digit
        Pattern lastNonDigitPattern = Pattern.compile(lastNonDigitRegex);
        Matcher lastNonDigitMatcher = lastNonDigitPattern.matcher(text);

        // regex for finding the last digit of a subsequence
        Pattern lastDigitPattern = Pattern.compile(lastDigitPatternRegex);
        Matcher lastDigitMatcher;

        // Iterate over each match and extract the number, start and end positions
        int lastNonDigitPosition = 0;
        while (lastNonDigitPosition != -1 && firstDigitMatcher.find(lastNonDigitPosition)) {
            int firstDigitPosition = firstDigitMatcher.start();        // get the start position of digit of subsequence 
            lastNonDigitPosition = -1;
            if (lastNonDigitMatcher.find(firstDigitPosition)) {
                lastNonDigitPosition = lastNonDigitMatcher.start();
            }
            
            int lastDigitPosition = 0;
            lastDigitMatcher = lastDigitPattern.matcher(text.substring(firstDigitPosition, lastNonDigitPosition != -1 ? lastNonDigitPosition:len));
            lastDigitMatcher.find();
            lastDigitPosition = firstDigitPosition + lastDigitMatcher.start(); // get the end position of subsequence

            String subSequence = text.substring(firstDigitPosition, lastDigitPosition+1);
            
            int periodCount = countPeriods(subSequence, '.');
            
            String[] splitedStringsOnPeriod;
            
            if (periodCount > 1) {
            	splitedStringsOnPeriod = subSequence.split("\\."); // for ex: 127.0.0.1 - need to split
            } else {
            	splitedStringsOnPeriod = new String[]{subSequence};     // for ex; 123.60  - no need to split     	
            }
            int startIndex = firstDigitPosition;
            // finding the start and end position
            for (String splitedString: splitedStringsOnPeriod) {

            	splitedString = splitedString.replaceAll("^[,\\.]+|[,\\.]+$", "").trim();
            	if (splitedString.isEmpty()) {
            		continue;
            	}
                int startPosition = text.indexOf(splitedString, startIndex);
                
                String extractedText = splitedString;
                Object extractedValue;
                if (extractedText.contains(".")) {
                	extractedValue = Double.parseDouble(extractedText.replaceAll(",", ""));
                } else {
                	extractedValue = Integer.parseInt(extractedText.replaceAll(",", ""));
                }
                int endPosition = startPosition + splitedString.length();    
                
                ExtractedNumber extractedNumber = new ExtractedNumber(extractedText, extractedValue, startPosition, endPosition);
                extractedNumbers.add(extractedNumber);
                
                startIndex = endPosition;
                  
            }
        }
		
		Response response = new Response(request.getId(), extractedNumbers);
        return response;
	
	}
	
        
    int countPeriods(String input, char target) {
    	
        int periodCount = 0;

        for (char ch : input.toCharArray()) {
            if (ch == target) {
            	periodCount++;
            }
        }

        return periodCount;
    }	
	
}

package com.assigment.numberextraction.models;

import java.util.List;

public class Response {

	private String id;
	private List<ExtractedNumber> extractedNumbers;
	
	public Response(String id, List<ExtractedNumber> extractedNumbers) {
		super();
		this.id = id;
		this.extractedNumbers = extractedNumbers;
	}

	public String getId() {
		return id;
	}
	
	public List<ExtractedNumber> getExtractedNumbers() {
		return extractedNumbers;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setExtractedNumbers(List<ExtractedNumber> extractedNumbers) {
		this.extractedNumbers = extractedNumbers;
	}
	
}

package com.assigment.numberextraction.models;

public class ExtractedNumber {
	
	private String extractedText;
	private Object extractedValue;
	private int startPosition;
	private int endPosition;
	
	public ExtractedNumber(String extractedText, Object extractedValue, int startPosition, int endPosition) {
		super();
		this.extractedText = extractedText;
		this.extractedValue = extractedValue;
		this.startPosition = startPosition;	
		this.endPosition = endPosition;
	}

	public String getExtractedText() {
		return extractedText;
	}

	public void setExtractedText(String extractedText) {
		this.extractedText = extractedText;
	}

	public Object getExtractedValue() {
		return extractedValue;
	}

	public void setExtractedValue(Object extractedValue) {
		this.extractedValue = extractedValue;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}
		
	
}

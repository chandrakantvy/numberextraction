package com.assigment.numberextraction.models;

public class Request {
	
	private String id;
	private String text;
	
	public Request(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getId() {
		return id;
	}
}

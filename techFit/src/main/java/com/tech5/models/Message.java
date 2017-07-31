package com.tech5.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class Message {
	private Integer status;
	private String body;

	
	public Message() {
		
	}
	
	public Message(int status,String body) {
		super();
		this.status= status;
		this.body = body;
	}
	@JsonProperty(value = "message")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	@JsonProperty(value = "status_code")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

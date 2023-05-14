package com.thinkhack.geopolis.mscitizen.to;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.thinkhack.geopolis.mscitizen.enums.MessageType;

public class MessageTO {
	
	private String method;

	private String message;
	
	private MessageType type;
	
	private LocalDateTime date;
	
	
	
	public MessageTO(MessageType type, String method, String message) {
		super();
		this.type = type;
		this.method = method;
		this.message = message;
		this.date = LocalDateTime.now(ZoneId.of("UTC"));
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}

}

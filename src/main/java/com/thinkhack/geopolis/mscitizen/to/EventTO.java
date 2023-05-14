package com.thinkhack.geopolis.mscitizen.to;

import javax.validation.constraints.NotNull;

public class EventTO {
	
	@NotNull
	private String event;
	private String value;	
	private String token;
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


}

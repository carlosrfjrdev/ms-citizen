package com.thinkhack.geopolis.mscitizen.to;

import javax.validation.constraints.NotBlank;

public class NewCitizenTO {
	
	@NotBlank
	private String citizenName;
	
	@NotBlank
	private String citizenMail;
	
	private String citizenPhone;
	
	public String getCitizenName() {
		return citizenName;
	}
	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}
	public String getCitizenMail() {
		return citizenMail;
	}
	public void setCitizenMail(String citizenMail) {
		this.citizenMail = citizenMail;
	}
	public String getCitizenPhone() {
		return citizenPhone;
	}
	public void setCitizenPhone(String citizenPhone) {
		this.citizenPhone = citizenPhone;
	}
	

}

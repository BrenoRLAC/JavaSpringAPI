package com.projetospring.projetoapi.ClienteErrorMessage;

import java.util.Date;

public class ClienteErrorMessage {
	
	private Date currentDate;
	private String message;
	
	public ClienteErrorMessage(Date currentDate, String message) {
		this.currentDate = currentDate;
		this.message = message;
	}
	
	
	public ClienteErrorMessage() {
		
	}


	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

package com.chang.bean;

public class GetMessage {
	private String message;
	
	public void setMessage(String msg) {
		message = msg;
	}

	public void getMessage() {
		System.out.println("the Message is : " + message);
	}
}

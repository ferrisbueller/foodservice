package com.akasa.foodservice.payload;

public class MessageResponse {

	private final String message;
	public MessageResponse(String s) {
		this.message = s;
	}
	public String getMessage() {
		return this.message;
	}


}

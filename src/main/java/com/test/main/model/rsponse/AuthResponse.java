package com.test.main.model.rsponse;

public class AuthResponse {
	private Boolean success;
	private String message;
	
	public AuthResponse() {
		super();
	}

	public AuthResponse(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

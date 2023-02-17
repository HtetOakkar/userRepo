package com.test.main.model.rsponse;

import java.util.Date;

public class JwtAuthResponse {
	private String token;
	private Date expiredAt;

	public JwtAuthResponse(String token, Date expiredAt) {
		super();
		this.token = token;
		this.expiredAt = expiredAt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}

}

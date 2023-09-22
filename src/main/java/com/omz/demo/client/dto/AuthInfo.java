package com.omz.demo.client.dto;

public class AuthInfo {

	private String clientId;
	private String clientPass;
	private String clientName;

	public AuthInfo() {
	}

	public AuthInfo(String clientId, String clientPass) {
		super();
		this.clientId = clientId;
		this.clientPass = clientPass;
	}

	public AuthInfo(String clientId, String clientPass, String cleintName) {
		super();
		this.clientId = clientId;
		this.clientPass = clientPass;
		this.clientName = cleintName;
	}

	public String getClientId() {
		return clientId;
	}
	
	public String getClientPass() {
		return clientPass;
	}

	public String getClientName() {
		return clientName;
	}	

}

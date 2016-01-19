package com.ferrarib.charging.model;

public enum TitleStatus {

	PENDING("Pending"), 
	RECEIVED("Received");

	private String description;

	TitleStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}

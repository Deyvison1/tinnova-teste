package com.tinnova.teste.register.vehicle.exception;

import java.time.Instant;

public class StandarError {

	private Instant timeSpan;
	private Integer status;
	private String error;
	private String message;
	private String url;

	public StandarError() {

	}

	public StandarError(Instant timeSpan, Integer status, String error, String message, String url) {
		super();
		this.timeSpan = timeSpan;
		this.status = status;
		this.error = error;
		this.message = message;
		this.url = url;
	}

	public Instant getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(Instant timeSpan) {
		this.timeSpan = timeSpan;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

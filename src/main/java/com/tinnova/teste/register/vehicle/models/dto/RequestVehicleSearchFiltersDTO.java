package com.tinnova.teste.register.vehicle.models.dto;

import java.io.Serializable;

public class RequestVehicleSearchFiltersDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String brand;
	private int year;
	private String color;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}

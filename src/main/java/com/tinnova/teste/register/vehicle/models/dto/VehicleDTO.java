package com.tinnova.teste.register.vehicle.models.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.tinnova.teste.register.vehicle.models.Vehicle;

public class VehicleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String vehicle;
	private String brand;
	private int year;
	private String description;
	private String color;
	private boolean sold;
	private LocalDateTime created;
	private LocalDateTime updated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public static VehicleDTO entityToDTO(Vehicle entity) {
		if (Objects.nonNull(entity)) {
			VehicleDTO dto = new VehicleDTO();
			dto.setId(entity.getId());
			dto.setBrand(entity.getBrand());
			dto.setCreated(entity.getCreated());
			dto.setDescription(entity.getDescription());
			dto.setSold(entity.isSold());
			dto.setUpdated(entity.getUpdated());
			dto.setVehicle(entity.getVehicle());
			dto.setColor(entity.getColor());
			dto.setYear(entity.getYear());
			return dto;
		}
		return null;
	}

	public static Vehicle dtoToEntity(VehicleDTO dto) {
		if (Objects.nonNull(dto)) {
			Vehicle entity = new Vehicle();
			entity.setId(dto.getId());
			entity.setBrand(dto.getBrand());
			entity.setCreated(dto.getCreated());
			entity.setDescription(dto.getDescription());
			entity.setSold(dto.isSold());
			entity.setUpdated(dto.getUpdated());
			entity.setVehicle(dto.getVehicle());
			entity.setYear(dto.getYear());
			entity.setColor(dto.getColor());
			return entity;
		}
		return null;
	}

	public static List<VehicleDTO> entityToDtoList(List<Vehicle> entities) {
		List<VehicleDTO> dtos = new ArrayList<VehicleDTO>();
		for (Vehicle entity : entities) {
			dtos.add(entityToDTO(entity));
		}
		return dtos;
	}

	public static List<VehicleDTO> dtoToEntityList(List<VehicleDTO> dtos) {
		List<Vehicle> entities = new ArrayList<Vehicle>();
		for (VehicleDTO dto : dtos) {
			entities.add(dtoToEntity(dto));
		}
		return dtos;
	}
	
	public static void preencheObjectoUpdate(Vehicle vehicle, VehicleDTO dto) {
		if(Objects.nonNull(vehicle) && Objects.nonNull(dto)) {
			vehicle.setId(dto.getId());
			vehicle.setBrand(dto.getBrand());
			vehicle.setCreated(dto.getCreated());
			vehicle.setDescription(dto.getDescription());
			vehicle.setSold(dto.isSold());
			vehicle.setUpdated(dto.getUpdated());
			vehicle.setVehicle(dto.getVehicle());
			vehicle.setYear(dto.getYear());
			vehicle.setColor(dto.getColor());
		}
	}
}

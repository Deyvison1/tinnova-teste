package com.tinnova.teste.register.vehicle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tinnova.teste.register.vehicle.models.dto.RequestVehicleSearchFiltersDTO;
import com.tinnova.teste.register.vehicle.models.dto.VehicleDTO;
import com.tinnova.teste.register.vehicle.services.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

	private final VehicleService service;

	public VehicleController(final VehicleService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public void create(@RequestBody VehicleDTO dto) {
		service.created(dto);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<VehicleDTO>> create() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/getAllfilters")
	public ResponseEntity<List<VehicleDTO>> create(
			@RequestBody RequestVehicleSearchFiltersDTO requestVehicleSearchFiltersDTO) {
		return ResponseEntity.ok(service.getAllByFilters(requestVehicleSearchFiltersDTO));
	}

}

package com.tinnova.teste.register.vehicle.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tinnova.teste.register.vehicle.models.Vehicle;
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

	@PutMapping("/update")
	public void update(@RequestBody VehicleDTO dto) {
		service.updated(dto);
	}

	@PatchMapping("/update-simple")
	public void updateSimple(@RequestBody VehicleDTO dto) {
		service.updated(dto);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<VehicleDTO>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("getById/{id}")
	public ResponseEntity<Vehicle> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.getById(id));
	}

	@GetMapping("/getAllfilters")
	public ResponseEntity<List<VehicleDTO>> getAllByFilters(@RequestParam() final String brand,
			@RequestParam() final String color, @RequestParam() final int yearVehicle) {
		return ResponseEntity.ok(service.getAllByFilters(brand, color, yearVehicle));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<List<VehicleDTO>> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok(null);
	}

}

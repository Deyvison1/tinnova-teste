package com.tinnova.teste.register.vehicle.services;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tinnova.teste.register.vehicle.exception.DataBaseException;
import com.tinnova.teste.register.vehicle.exception.ResourceNotFoundException;
import com.tinnova.teste.register.vehicle.models.Vehicle;
import com.tinnova.teste.register.vehicle.models.dto.VehicleDTO;
import com.tinnova.teste.register.vehicle.repository.VehicleRepository;

import jakarta.persistence.EntityManager;

@Service
public class VehicleService {

	private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

	private final VehicleRepository repository;
	private final EntityManager entityManager;

	public VehicleService(final VehicleRepository repository, final EntityManager entityManager) {
		this.repository = repository;
		this.entityManager = entityManager;
	}

	public List<VehicleDTO> getAll() {
		log.info("Started the search without filter");
		return VehicleDTO.entityToDtoList(repository.findAll());
	}

	public void created(VehicleDTO dto) {
		repository.save(VehicleDTO.dtoToEntity(dto));
	}

	public Vehicle getById(Long id) {
		try {
			log.info("Start search by id");
			Vehicle vehicle = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
			return vehicle;
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	public void updated(VehicleDTO dto) {
		log.info("Full update start");
		Vehicle vehicle = getById(dto.getId());
		VehicleDTO.preencheObjectoUpdate(vehicle, dto);

		repository.save(vehicle);
	}

	public void delete(Long id) {
		try {
			getById(id);
			log.info("Start deleting record");
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			log.error(e.getMessage());
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			log.error(e.getMessage());
			throw new DataBaseException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<VehicleDTO> getAllByFilters(final String brand, final String color, final int yearVehicle) {
		StringBuilder nativeSql = new StringBuilder();
		nativeSql.append("SELECT * FROM public.vehicle vehicle where 1 = 1 ");
		if (Strings.isNotEmpty(brand)) {
			nativeSql.append(" and vehicle.brand = " + brand);
		}

		if (Strings.isNotEmpty(color)) {
			nativeSql.append(" and vehicle.color = " + color);
		}

		if (yearVehicle > 0) {
			nativeSql.append(" and vehicle.year_vehicle = " + yearVehicle);
		}
		var result = entityManager.createNativeQuery(nativeSql.toString(), Vehicle.class);

		return result.getResultList();
	}

}

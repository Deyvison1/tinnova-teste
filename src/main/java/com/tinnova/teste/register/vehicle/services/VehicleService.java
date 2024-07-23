package com.tinnova.teste.register.vehicle.services;

import java.time.LocalDateTime;
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
import jakarta.persistence.EntityNotFoundException;

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

	public VehicleDTO created(VehicleDTO dto) {
		Vehicle entity = repository.save(VehicleDTO.dtoToEntity(dto));
		return VehicleDTO.entityToDTO(entity);
	}

	public Vehicle getById(Long id) {
		try {
			log.info("Start search by id");
			Vehicle vehicle = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
			return vehicle;
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new ResourceNotFoundException(id);
		}
	}

	public void updated(VehicleDTO dto, Long id) {
		try {
			log.info("Full update start");
			Vehicle vehicle = getById(id);
			dto.setUpdated(LocalDateTime.now());
			VehicleDTO.preencheObjectoUpdate(vehicle, dto);

			repository.save(vehicle);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
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
		nativeSql.append("SELECT * FROM Vehicle vehicle where 1 = 1 ");
		if (Strings.isNotEmpty(brand)) {
			nativeSql.append(" and UPPER(vehicle.brand) = UPPER('" + brand + "')");
		}

		if (Strings.isNotEmpty(color)) {
			nativeSql.append(" and UPPER(vehicle.color) = UPPER('" + color + "')");
		}

		if (yearVehicle > 0) {
			nativeSql.append(" and vehicle.year_vehicle = " + yearVehicle);
		}

		var result = entityManager.createNativeQuery(nativeSql.toString(), Vehicle.class);

		return result.getResultList();
	}

}

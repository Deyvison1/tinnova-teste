package com.tinnova.teste.register.vehicle.services;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.tinnova.teste.register.vehicle.models.Vehicle;
import com.tinnova.teste.register.vehicle.models.dto.RequestVehicleSearchFiltersDTO;
import com.tinnova.teste.register.vehicle.models.dto.VehicleDTO;
import com.tinnova.teste.register.vehicle.repository.VehicleRepository;

import jakarta.persistence.EntityManager;

@Service
public class VehicleService {

	private final VehicleRepository repository;
	private final EntityManager entityManager;

	public VehicleService(final VehicleRepository repository, final EntityManager entityManager) {
		this.repository = repository;
		this.entityManager = entityManager;
	}

	public List<VehicleDTO> getAll() {
		return VehicleDTO.entityToDtoList(repository.findAll());
	}

	public void created(VehicleDTO dto) {
		repository.save(VehicleDTO.dtoToEntity(dto));
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public void updated(VehicleDTO dto) {
		repository.save(VehicleDTO.dtoToEntity(dto));
	}

	@SuppressWarnings("unchecked")
	public List<VehicleDTO> getAllByFilters(RequestVehicleSearchFiltersDTO filters) {
		StringBuilder nativeSql = new StringBuilder();
		nativeSql.append("SELECT * FROM public.vehicle vehicle where 1 = 1 ");
		if (Strings.isNotBlank(filters.getBrand()) || filters.getBrand() != null) {
			nativeSql.append(" and vehicle.brand = "+filters.getBrand());
		}

		if (Strings.isNotBlank(filters.getColor()) || filters.getColor() != null) {
			nativeSql.append(" and vehicle.color = "+filters.getColor());
		}

		if (filters.getYear() > 0) {
			nativeSql.append(" and vehicle.year = "+filters.getYear());
		}
		var result = entityManager.createNativeQuery(nativeSql.toString(), Vehicle.class);
		
		return result.getResultList();
	}

}

package com.viacep.services.ibge;

import java.io.Serializable;
import java.util.List;

import com.viacep.models.CountyModel;

import jakarta.inject.Inject;

public class CountyService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Ibge ibge;
	
	public List<CountyModel> findByCounty(String federalUnit) {
		return ibge.findByCounty(federalUnit);
	}
	
}

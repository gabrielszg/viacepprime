package com.viacep.services;

import java.io.Serializable;
import java.util.List;

import com.viacep.models.ViaCepModel;

import jakarta.inject.Inject;

public class ViaCepService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ViaCep viaCep;
	
	public ViaCepModel findByZipCode(String zipCode) {
		return viaCep.findByZipCode(zipCode);
	}
	
	public List<ViaCepModel> findByAddress(String federalUnit, String city, String publicPlace) {
		return viaCep.findByAddress(federalUnit, city, publicPlace);
	}
	
}

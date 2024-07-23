package com.viacep.services;

import java.util.List;

import com.viacep.models.ViaCepModel;

public interface ViaCep {
	
	ViaCepModel findByZipCode(String zipCode);
	List<ViaCepModel> findByAddress(String federalUnit, String city, String publicPlace);
}

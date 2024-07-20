package com.viacep.service;

import java.util.List;

import com.viacep.model.ViaCepModel;

public interface ViaCep {
	
	ViaCepModel findByZipCode(String zipCode);
	List<ViaCepModel> findByAddress(String federalUnit, String city, String publicPlace);
}

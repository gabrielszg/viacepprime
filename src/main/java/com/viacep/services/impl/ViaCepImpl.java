package com.viacep.services.impl;

import java.io.Serializable;
import java.util.List;

import com.viacep.configs.PropertiesReader;
import com.viacep.models.ViaCepModel;
import com.viacep.services.ViaCep;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class ViaCepImpl implements ViaCep, Serializable {

	private static final long serialVersionUID = 1L;

	private static final String WEBSERVICE = PropertiesReader.getProperty("webservice.viacep");
	private static final String JSON_RESPONSE_FORMAT = "json";
	
	@Override
	public ViaCepModel findByZipCode(String zipCode) {
		return client()
				.target(WEBSERVICE)
				.path(zipCode)
				.path(JSON_RESPONSE_FORMAT)
				.request(MediaType.APPLICATION_JSON)
				.get(ViaCepModel.class);
	}
	
	@Override
	public List<ViaCepModel> findByAddress(String federalUnit, String city, String publicPlace) {
		return client()
				.target(WEBSERVICE)
				.path(federalUnit)
				.path(city)
				.path(publicPlace)
				.path(JSON_RESPONSE_FORMAT)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<ViaCepModel>>() {});
	}
	
	private Client client() {
		return ClientBuilder.newClient();
	}

}

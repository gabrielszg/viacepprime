package com.viacep.services.ibge.impl;

import java.io.Serializable;
import java.util.List;

import com.viacep.configs.PropertiesReader;
import com.viacep.models.CountyModel;
import com.viacep.models.StateModel;
import com.viacep.services.ibge.Ibge;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class IbgeImpl implements Ibge, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String WEBSERVICE = PropertiesReader.getProperties().getProperty("webservice.ibge");

	@Override
	public List<CountyModel> findByCounty(String federalUnit) {
		return client()
				.target(WEBSERVICE)
				.path(federalUnit)
				.path("municipios")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<CountyModel>>() {});
	}

	@Override
	public List<StateModel> findAllStates() {
		return client()
				.target(WEBSERVICE)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<StateModel>>() {});
	}
	
	private Client client() {
		return ClientBuilder.newClient();
	}

}

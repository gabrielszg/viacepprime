package com.viacep.services.ibge.impl;

import java.io.Serializable;
import java.util.List;

import com.viacep.configs.PropertiesReader;
import com.viacep.models.CountyModel;
import com.viacep.models.StateModel;
import com.viacep.services.ibge.Ibge;
import com.viacep.utils.client.RestClient;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class IbgeImpl implements Ibge, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String WEBSERVICE = PropertiesReader.getProperty("webservice.ibge");

	@Inject
	private RestClient restClient;
	
	@Override
	public List<CountyModel> findByCounty(String federalUnit) {
		return baseUrl()
				.path(federalUnit)
				.path("municipios")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<CountyModel>>() {});
	}

	@Override
	public List<StateModel> findAllStates() {
		return baseUrl()
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<StateModel>>() {});
	}
	
	private WebTarget baseUrl() {
		return restClient.webTarget(WEBSERVICE);
	}

}

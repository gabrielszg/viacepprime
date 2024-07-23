package com.viacep.services.ibge;

import java.io.Serializable;
import java.util.List;

import com.viacep.models.StateModel;

import jakarta.inject.Inject;

public class StateService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Ibge ibge;
	
	public List<StateModel> findAllStates() {
		return ibge.findAllStates();
	}

}

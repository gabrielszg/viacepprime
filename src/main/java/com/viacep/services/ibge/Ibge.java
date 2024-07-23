package com.viacep.services.ibge;

import java.util.List;

import com.viacep.models.CountyModel;
import com.viacep.models.StateModel;

public interface Ibge {

	List<CountyModel> findByCounty(String federalUnit);
	List<StateModel> findAllStates();
}

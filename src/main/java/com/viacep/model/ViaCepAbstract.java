package com.viacep.model;

import java.io.Serializable;

import com.viacep.configs.PropertiesReader;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ViaCepAbstract implements Serializable {

	protected static final long serialVersionUID = 1L;
	
	protected final String WEBSERVICE = PropertiesReader.getProperties().getProperty("viacep");
//	protected final String WEBSERVICE = "http://viacep.com.br/ws/";
	protected Integer zipCode;
	protected String federalUnit;
	protected String city;
	protected String publicPlace;
}

package com.viacep.service.ibge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import com.viacep.configs.PropertiesReader;
import com.viacep.exception.ViaCepException;
import com.viacep.util.JSONConverter;

public class CountyService implements Serializable, ConnectionIbge {

	private static final long serialVersionUID = 1L;

	static String federalUnit;
	private static final String WEBSERVICE = PropertiesReader.getProperties().getProperty("webservice.ibge");

	public static void setFederalUnit(String federalUnitSelected) {
		federalUnit = federalUnitSelected;
	}

	@Override
	public URL connection() {
		try {
			return new URL(WEBSERVICE + federalUnit + "/municipios");
		} catch (MalformedURLException e) {
			throw new ViaCepException(e.getMessage());
		}
	}

	@Override
	public StringBuffer reader() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection().openStream(), "UTF-8"))) {
			return JSONConverter.convertToJson(reader);
		} catch (IOException e) {
			throw new ViaCepException(e.getMessage());
		}
	}
	
}

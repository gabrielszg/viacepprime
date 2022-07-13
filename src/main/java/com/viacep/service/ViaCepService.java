package com.viacep.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.viacep.exception.ViaCepException;
import com.viacep.model.ViaCepAbstract;
import com.viacep.util.JSONConverter;
import com.viacep.util.URLConverter;

public class ViaCepService extends ViaCepAbstract {

	private static final long serialVersionUID = 1L;
	
	// Busca somente pelo CEP 
	
	private URL urlZipCode() {
		try {
			return new URL(WEBSERVICE + zipCode + "/json/");
		} catch (MalformedURLException e) {
			throw new ViaCepException(e.getMessage());
		}
	}

	public StringBuffer connectionZipCode() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlZipCode().openStream(), "UTF-8"))) {
			return JSONConverter.convertToJson(reader);
		} catch (IOException e) {
			throw new ViaCepException(e.getMessage());
		}
	}

	// Busca por Estado, Cidade e Logradouro

	private URL urlAddress() {
		try {
			URL urlEncode = new URL(WEBSERVICE 
					+ federalUnit 
					+ "/" 
					+ city 
					+ "/" 
					+ publicPlace
					+ "/json/");
			return URLConverter.urlConverter(urlEncode);
		} catch (Exception e) {
			throw new ViaCepException(e.getMessage());
		}
	}

	public StringBuffer connectionAddress() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlAddress().openStream(), "UTF-8"))) {
			return JSONConverter.convertToJson(reader);
		} catch (IOException e) {
			throw new ViaCepException(e.getMessage());
		}
	}
	
}

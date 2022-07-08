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

public class ViaCepImpl extends ViaCepAbstract {

	private static final long serialVersionUID = 1L;
	
	// Busca somente pelo CEP 
	
	private URL url() {
		try {
			return new URL(WEBSERVICE + cep + "/json/");
		} catch (MalformedURLException e) {
			throw new ViaCepException(e.getMessage());
		}
	}

	public StringBuffer connection() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url().openStream(), "UTF-8"))) {
			return JSONConverter.convertToJson(reader);
		} catch (IOException e) {
			throw new ViaCepException(e.getMessage());
		}
	}

	// Busca por Estado, Cidade e Logradouro

	private URL url2() {
		try {
			URL urlEncode = new URL(WEBSERVICE 
					+ uf 
					+ "/" 
					+ city 
					+ "/" 
					+ logr
					+ "/json/");
			return URLConverter.urlConverter(urlEncode);
		} catch (Exception e) {
			throw new ViaCepException(e.getMessage());
		}
	}

	public StringBuffer conn() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(url2().openStream(), "UTF-8"))) {
			return JSONConverter.convertToJson(br);
		} catch (IOException e) {
			throw new ViaCepException(e.getMessage());
		}
	}
	
}

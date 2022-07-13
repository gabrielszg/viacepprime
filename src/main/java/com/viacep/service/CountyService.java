package com.viacep.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import com.viacep.exception.ViaCepException;
import com.viacep.util.JSONConverter;

public class CountyService implements Serializable {

	private static final long serialVersionUID = 1L;

	static String id;

	public static String idState(String idOther) {
		id = idOther;
		return id;
	}

	private static URL url() {
		try {
			return new URL("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + id + "/municipios");
		} catch (MalformedURLException e) {
			throw new ViaCepException(e.getMessage());
		}
	}

	public static StringBuffer connection() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url().openStream(), "UTF-8"))) {
			return JSONConverter.convertToJson(reader);
		} catch (IOException e) {
			throw new ViaCepException(e.getMessage());
		}
	}
}

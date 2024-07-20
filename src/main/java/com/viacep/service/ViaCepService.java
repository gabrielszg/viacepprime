package com.viacep.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.viacep.exception.ViaCepException;
import com.viacep.model.ViaCepAbstract;
import com.viacep.util.JSONConverter;
import com.viacep.util.URLConverter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ViaCepService extends ViaCepAbstract implements ConnectionViaCep {

	private static final long serialVersionUID = 1L;
	
	@Override
	public URL zipCodeConnection() {
		try {
			return new URL(WEBSERVICE + zipCode + "/json/");
		} catch (MalformedURLException e) {
			throw new ViaCepException(e.getMessage());
		}
	}

	@Override
	public URL connectionAddress() {
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

	@Override
	public StringBuffer readerZipCode() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(zipCodeConnection().openStream(), StandardCharsets.UTF_8))) {
			return JSONConverter.convertToJson(reader);
		} catch (IOException e) {
			throw new ViaCepException(e.getMessage());
		}
	}

	@Override
	public StringBuffer readerAddress() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(connectionAddress().openStream(), StandardCharsets.UTF_8))) {
			return JSONConverter.convertToJson(reader);
		} catch (IOException e) {
			throw new ViaCepException(e.getMessage());
		}
	}
	
}

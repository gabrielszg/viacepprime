package com.viacep.controller;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viacep.model.County;
import com.viacep.model.State;
import com.viacep.model.ViaCep;
import com.viacep.service.ViaCepService;
import com.viacep.service.ibge.CountyService;
import com.viacep.service.ibge.StateService;
import com.viacep.util.jsf.FacesUtil;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ViaCepController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter private ViaCep viaCep;
	@Getter @Setter private String federalUnit;
	@Getter @Setter private String city;
	@Getter @Setter private String zipCode;
	@Getter @Setter private String publicPlace;
	
	@Getter private List<ViaCep> listViaCep;
	@Getter private List<State> listStates;
	@Getter private List<County> listCounties;
	
	@Inject
	private ViaCepService viaCepService;
	
	@Inject
	private StateService stateService;
	
	@Inject
	private CountyService countyService;
	
	@Getter @Setter private PDFOptions pdfOpt;
	@Getter @Setter private Gson gson;
	@Getter @Setter private StringBuffer json;

	@PostConstruct
	public void init() {
		gson = new Gson();
		searchFederalUnit();
		viaCepService = new ViaCepService();
		customizationOptions();
	}

	public void searchFederalUnit() {
		json = stateService.reader();
		
		Type type = new TypeToken<List<State>>() {}.getType();
		listStates = gson.fromJson(json.toString(), type);
		listStates.sort(Comparator.naturalOrder());
	}

	public void searchCity() {
		CountyService.setFederalUnit(federalUnit);
		json = countyService.reader();

		Type type = new TypeToken<List<County>>() {}.getType();
		listCounties = gson.fromJson(json.toString(), type);
		listCounties.sort(Comparator.naturalOrder());
	}
	
	public void searchZipCode() {
		viaCepService.setZipCode(zipCode);
		json = viaCepService.readerZipCode();

		viaCep = gson.fromJson(json.toString(), ViaCep.class);

		if (viaCep.getCep() == null) {
			FacesUtil.addErrorMesssage("CEP Inválido!");
		}
	}

	public void searchAddress() throws Exception {
		viaCepService.setFederalUnit(federalUnit);
		viaCepService.setCity(city);
		viaCepService.setPublicPlace(publicPlace);
		
		if (viaCepService.getPublicPlace().length() < 3) {
			FacesUtil.addWarnMesssage("Logradouro deve conter ao menos três caracteres");
		} else {
			json = viaCepService.readerAddress();

			Type type = new TypeToken<List<ViaCep>>() {}.getType();
			listViaCep = gson.fromJson(json.toString(), type);
			listViaCep.sort(Comparator.naturalOrder());
			
			if (listViaCep.isEmpty()) {
				FacesUtil.addErrorMesssage("Endereço não encontrado");
			}
		} 
	}
	
	public void customizationOptions() {
		pdfOpt = new PDFOptions();
		pdfOpt.setFacetBgColor("#C0C0C0");
		pdfOpt.setFacetFontStyle("BOLD");
		pdfOpt.setCellFontStyle("12");
		pdfOpt.setFontName("Arial");
		pdfOpt.setOrientation(PDFOrientationType.LANDSCAPE);
	}
	
}

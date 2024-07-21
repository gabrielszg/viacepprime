package com.viacep.controllers;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;

import com.viacep.models.CountyModel;
import com.viacep.models.StateModel;
import com.viacep.models.ViaCepModel;
import com.viacep.services.ViaCepService;
import com.viacep.services.ibge.CountyService;
import com.viacep.services.ibge.StateService;
import com.viacep.utils.jsf.FacesUtil;

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

	@Getter @Setter private ViaCepModel viaCep;
	@Getter @Setter private String federalUnit;
	@Getter @Setter private String city;
	@Getter @Setter private String zipCode;
	@Getter @Setter private String publicPlace;
	
	@Getter private List<ViaCepModel> listViaCep;
	@Getter private List<StateModel> listStates;
	@Getter private List<CountyModel> listCounties;
	
	@Inject
	private ViaCepService viaCepService;
	
	@Inject
	private StateService stateService;
	
	@Inject
	private CountyService countyService;
	
	@Getter @Setter private PDFOptions pdfOpt;

	@PostConstruct
	public void init() {
		searchFederalUnit();
		customizationOptions();
	}

	public void searchFederalUnit() {
		listStates = stateService.findAllStates();
		listStates.sort(Comparator.naturalOrder());
	}

	public void searchCity() {
		listCounties = countyService.findByCounty(federalUnit);
		listCounties.sort(Comparator.naturalOrder());
	}
	
	public void searchZipCode() {
		viaCep = viaCepService.findByZipCode(zipCode);

		if (viaCep.getCep() == null) {
			FacesUtil.addErrorMesssage("CEP Inválido!");
		}
	}
	
	public void searchAddress() {
		if (publicPlace.length() < 3) {
			FacesUtil.addWarnMesssage("Logradouro deve conter ao menos três caracteres");
		} else {
			listViaCep = viaCepService.findByAddress(federalUnit, city, publicPlace);
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

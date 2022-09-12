package com.viacep.controller;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viacep.model.County;
import com.viacep.model.State;
import com.viacep.model.ViaCep;
import com.viacep.service.CountyService;
import com.viacep.service.StateService;
import com.viacep.service.ViaCepService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class ViaCepController implements Serializable {

	private static final long serialVersionUID = 1L;

	private ViaCep viaCep;
	private List<ViaCep> listViaCep;

	private List<State> listStates = new ArrayList<>();
	private String federalUnit;

	private List<County> listCounties;
	private String city;
	private String id;

	private ViaCepService viaCepService;
	
	private PDFOptions pdfOpt;
	private Gson gson;

	@PostConstruct
	public void init() {
		gson = new Gson();
		searchFederalUnit();
		viaCepService = new ViaCepService();
		customizationOptions();
	}

	public void searchFederalUnit() {
		StringBuffer json = StateService.connection();
		
		Type type = new TypeToken<List<State>>() {}.getType();
		listStates = gson.fromJson(json.toString(), type);
		listStates.sort(Comparator.naturalOrder());
	}

	public String getId() {
		for (State es : listStates) {
			if (es.getSigla().contains(federalUnit)) {
				id = es.getId();
			}
		}
		return id;
	}

	public void searchCity() {
		CountyService.idState(getId());
		StringBuffer json = CountyService.connection();

		Type type = new TypeToken<List<County>>() {}.getType();
		listCounties = gson.fromJson(json.toString(), type);
		listCounties.sort(Comparator.naturalOrder());
	}

	public void searchZipCode() {
		StringBuffer json = viaCepService.connectionZipCode();

		viaCep = gson.fromJson(json.toString(), ViaCep.class);

		if (viaCep.getCep() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "CEP Inválido!", "Erro"));
		}
	}

	public void searchAddress() throws Exception {
		viaCepService.setFederalUnit(federalUnit);
		viaCepService.setCity(city);
		
		if (viaCepService.getPublicPlace().length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logradouro deve conter ao menos três caracteres", "Erro"));
		} else {
			StringBuffer json = viaCepService.connectionAddress();

			Type type = new TypeToken<List<ViaCep>>() {}.getType();
			listViaCep = gson.fromJson(json.toString(), type);
			listViaCep.sort(Comparator.naturalOrder());
			
			if (listViaCep.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Endereço não encontrado", "Aviso"));
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

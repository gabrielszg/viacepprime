package com.viacep.controller;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viacep.model.Estado;
import com.viacep.model.Municipio;
import com.viacep.model.ViaCep;
import com.viacep.service.EstadoImpl;
import com.viacep.service.MunicipioImpl;
import com.viacep.service.ViaCepImpl;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class ViaCepController implements Serializable {

	private static final long serialVersionUID = 1L;

	private ViaCep viaCep;
	private List<ViaCep> list;

	private List<Estado> listEstados = new ArrayList<>();
	private String uf;

	private List<Municipio> listMunicipios;
	private String city;
	private String id;

	private ViaCepImpl viaCepInput;

	@PostConstruct
	public void init() {
		searchUf();
		viaCepInput = new ViaCepImpl();
	}

	public void searchUf() {
		StringBuffer json = EstadoImpl.connection();

		Gson gson = new Gson();
		Type type = new TypeToken<List<Estado>>() {}.getType();
		listEstados = gson.fromJson(json.toString(), type);
		listEstados.sort(Comparator.naturalOrder());
	}

	public String getId() {
		for (Estado es : listEstados) {
			if (es.getSigla().contains(uf)) {
				id = es.getId();
			}
		}

		return id;
	}

	public void searchCity() {
		MunicipioImpl.idEstado(getId());
		StringBuffer json = MunicipioImpl.connection();

		Gson gson = new Gson();
		Type type = new TypeToken<List<Municipio>>() {}.getType();
		listMunicipios = gson.fromJson(json.toString(), type);
		listMunicipios.sort(Comparator.naturalOrder());
	}

	public void searchCep() {
		StringBuffer json = viaCepInput.connection();

		Gson gson = new Gson();
		viaCep = gson.fromJson(json.toString(), ViaCep.class);

		if (viaCep.getCep() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "CEP Inválido!", "Erro"));
		}
	}

	public void searchCepPorUf() throws Exception {
		viaCepInput.setUf(uf);
		viaCepInput.setCity(city);
		
		if (viaCepInput.getLogr().length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logradouro deve conter ao menos três caracteres", "Erro"));
		} else {
			StringBuffer json = viaCepInput.conn();

			Gson gson = new Gson();
			Type type = new TypeToken<List<ViaCep>>() {}.getType();
			list = gson.fromJson(json.toString(), type);
			list.sort(Comparator.naturalOrder());
			
			if (list.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Endereço não encontrado", "Aviso"));
			}
		} 
	}
	
}

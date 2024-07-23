package com.viacep.models;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ViaCepModel implements Serializable, Comparable<ViaCepModel> {

	private static final long serialVersionUID = 1L;

	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	private String siafi;
	
	@Override
	public int compareTo(ViaCepModel other) {
		return cep.compareTo(other.getCep());
	}

}

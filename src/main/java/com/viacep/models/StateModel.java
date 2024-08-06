package com.viacep.models;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class StateModel implements Serializable, Comparable<StateModel> {

	private static final long serialVersionUID = 1L;

	private String id;
	private String sigla;
	private String nome;

	@Override
	public String toString() {
		return sigla;
	}

	@Override
	public int compareTo(StateModel other) {
		return sigla.compareTo(other.getSigla());
	}

}

package com.viacep.models;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CountyModel implements Serializable, Comparable<CountyModel> {

	private static final long serialVersionUID = 1L;

	private String nome;

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(CountyModel other) {
		return nome.compareTo(other.getNome());
	}

}

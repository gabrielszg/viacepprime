package com.viacep.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class County implements Serializable, Comparable<County> {

	private static final long serialVersionUID = 1L;

	private String nome;

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(County other) {
		return nome.compareTo(other.getNome());
	}

}

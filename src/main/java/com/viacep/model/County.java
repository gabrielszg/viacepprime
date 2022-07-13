package com.viacep.model;

import java.io.Serializable;

public class County implements Serializable, Comparable<County> {

	private static final long serialVersionUID = 1L;

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(County other) {
		return nome.compareTo(other.getNome());
	}

}

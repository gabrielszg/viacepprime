package com.viacep.model;

import java.io.Serializable;

public class Municipio implements Serializable, Comparable<Municipio> {

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
	public int compareTo(Municipio other) {
		return nome.compareTo(other.getNome());
	}

}

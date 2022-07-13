package com.viacep.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class State implements Serializable, Comparable<State> {

	private static final long serialVersionUID = 1L;

	private String id;
	private String sigla;

	@Override
	public String toString() {
		return sigla;
	}

	@Override
	public int compareTo(State other) {
		return sigla.compareTo(other.getSigla());
	}

}

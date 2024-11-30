package com.api.data;

import com.api.model.Musica;

import java.util.ArrayList;

public class Input {

	public ArrayList<Musica> getMusicas() {

		return leituraDoArquivo();

	}

	private ArrayList<Musica> leituraDoArquivo() {
	
		return new ArrayList<Musica>();
	}

}

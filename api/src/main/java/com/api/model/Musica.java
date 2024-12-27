package com.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class Musica {

	public String titulo;

	public String nomeArtista;

	public String idArtista;

	public List<String> generos;

	public Integer popularidade;


	public Musica(String titulo, String nomeArtista, String idArtista, Integer popularidade) {
		this.titulo = titulo;
		this.nomeArtista = nomeArtista;
		this.idArtista = idArtista;
		this.popularidade = popularidade;
		this.generos = new ArrayList<String>();
	}


	public String getTitulo() {
		return this.titulo;
	}


	public String getIdArtista() {
		return this.idArtista;
	}

	public List<String> getGeneros() {
		return this.generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}


	public String toString() {
		return "( " +
			this.titulo + " - " +
			this.nomeArtista + " - " +
			this.idArtista + " - " +
			this.popularidade + " - " +
			this.generos +
			" )";
	}

}

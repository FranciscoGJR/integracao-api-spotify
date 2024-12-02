package com.api.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Musica {

	public String titulo;

	public String nomeArtista;

	public String idArtista;

	public String genero;

	public Integer popularidade;


	public Musica(String titulo, String nomeArtista, String idArtista, Integer popularidade) {
		this.titulo = titulo;
		this.nomeArtista = nomeArtista;
		this.idArtista = idArtista;
		this.popularidade = popularidade;
		this.genero = new String();
	}


	public String getTitulo() {
		return this.titulo;
	}


	public String getIdArtista() {
		return this.idArtista;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String toString() {
		return "( " +
			this.titulo + " - " +
			this.nomeArtista + " - " +
			this.idArtista + " - " +
			this.popularidade + " - " +
			this.genero +
			" )";
	}

}

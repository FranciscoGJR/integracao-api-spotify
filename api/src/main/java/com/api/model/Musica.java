package com.api.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Musica {

	public String titulo;

	public String nomeArtista;

	public String idArtista;

	public String genero;

	public Integer popularidade;


	public String getTitulo() {
		return this.titulo;
	}


	public Musica(String titulo, String nomeArtista, String idArtista, Integer popularidade) {
		this.titulo = titulo;
		this.nomeArtista = nomeArtista;
		this.idArtista = idArtista;
		this.popularidade = popularidade;
	}


	public String toString() {
		return this.titulo + " - " +
			this.nomeArtista + " - " +
			this.idArtista + " - " +
			this.popularidade;
	}

}

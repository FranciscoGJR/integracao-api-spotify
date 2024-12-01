package com.api.service;

import com.api.model.Musica;

import java.io.IOException;
import java.net.URI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Spotify {

	private static final String BASE_URL = "https://api.spotify.com/v1/artists/";

	private final String accessToken = "";

	private LocalDateTime time;
	private DateTimeFormatter formatador;


	public Spotify() {
		this.time = LocalDateTime.now();
		this.formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	}


	public ArrayList<Musica> getListaDeMusicas() {

		ArrayList<Musica> listaDeMusicas = new ArrayList<>();

		return listaDeMusicas;
	}


	public String getGenero(String idArtista) {

	       	var url = BASE_URL + idArtista;

        	var httpClient = HttpClient.newHttpClient();

        	var request = HttpRequest.newBuilder()
                	.uri(URI.create(url))
                	.header("Authorization", "Bearer " + accessToken)
                	.header("Content-Type", "application/json")
                	.GET()
         		.build();
		
		try {
		var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() != 200) {
			System.err.println( horarioAtual() + " - Error ao solicitar api externa");
			return "Inválido";
		}

			return response.body();

		} catch (Exception e) {
			System.err.println("Erro inesperado: " + e.getMessage());
		}

		return "Não encontrado";
	}


	private String horarioAtual(){
		return time.format(formatador);
	}

}


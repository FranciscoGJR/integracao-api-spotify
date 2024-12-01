package com.api.service;

import static com.api.Constantes.*;

import com.api.model.Musica;

import java.io.IOException;
import java.net.URI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Spotify {

	private LocalDateTime time;
	private DateTimeFormatter formatador;


	public Spotify() {
		this.time = LocalDateTime.now();
		this.formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	}


	public String getListaDeMusicas() {

		List<Musica> listaDeMusicas = new ArrayList<>();

	       	var url = URL_API_SPOTIFY_CHARTS + PATH_CITY;

        	var httpClient = HttpClient.newHttpClient();

        	var request = HttpRequest.newBuilder()
                	.uri(URI.create(url))
                	.header("Authorization", "Bearer " + ACCESS_TOKEN)
                	.header("Content-Type", "application/json")
                	.GET()
         		.build();
		
		try {
			var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


			if (response.statusCode() != 200) {
				System.err.println( horarioAtual() + " - Error ao solicitar api: " + url);
				return "Error";
			}

			return response.body();

		} catch (Exception e) {
			System.err.println("Erro inesperado: " + e.getMessage());
		}

		return "Não encontrado";
	}


	public String getGenero(String idArtista) {

	       	var url = URL_API_SPOTIFY + idArtista;

        	var httpClient = HttpClient.newHttpClient();

        	var request = HttpRequest.newBuilder()
                	.uri(URI.create(url))
                	.header("Authorization", "Bearer " + ACCESS_TOKEN)
                	.header("Content-Type", "application/json")
                	.GET()
         		.build();
		
		try {
			var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				System.err.println( horarioAtual() + " - Error ao solicitar api: " + url);
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


package com.api.request;

import com.api.model.Musica;

import java.io.IOException;
import java.net.URI;

import java.util.ArrayList;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Spotify {

	private static final String BASE_URL = "https://api.spotify.com/v1/artists/";

	private final String accessToken = "";


	public ArrayList<Musica> getMusicas() {

		return new ArrayList<Musica>();
	}


	public String getGenero(String idArtista) {

	        String url = BASE_URL + idArtista;

        	HttpClient httpClient = HttpClient.newHttpClient();

        	HttpRequest request = HttpRequest.newBuilder()
                	.uri(URI.create(url))
                	.header("Authorization", "Bearer " + accessToken)
                	.header("Content-Type", "application/json")
                	.GET()
         	.build();
		
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (Exception e) {
			System.err.println("Erro inesperado: " + e.getMessage());
		}

		return "Gênero não encontrado";
	}

}


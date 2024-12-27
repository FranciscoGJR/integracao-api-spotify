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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Spotify {

	private LocalDateTime time;
	private DateTimeFormatter formatador;


	public Spotify() {
		this.time = LocalDateTime.now();
		this.formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	}


	public List<Musica> getListaDeMusicas() {

	       	var url = URL_API_SPOTIFY_CHARTS + PATH_CITY + WEEKLY;

        	var httpClient = HttpClient.newHttpClient();

        	var request = HttpRequest.newBuilder()
                	.uri(URI.create(url))
                	.header("Authorization", "Bearer " + ACCESS_TOKEN)
                	.header("Content-Type", "application/json")
                	.GET()
         		.build();

		String jsonString = new String();

		try {
			var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				System.err.println( horarioAtual() + " - Error ao solicitar api: " + url);
				return new ArrayList<>();
			}

			List<Musica> listaDeMusicas = preencherLista(response.body());

			return listaDeMusicas;

		} catch (Exception e) {
			System.err.println("Erro inesperado: " + e.getMessage());
		}

		return new ArrayList<>();

	}


	public List<Musica> preencherLista(String jsonString) {

		List<Musica> listaDeMusicas = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();

		try {

			JsonNode root = mapper.readTree(jsonString);
			JsonNode entriesNode = root.path("entries");

			int popularidade = 1;
			for (JsonNode entryNode : entriesNode) {
				
				JsonNode jsonTrackMetadataNode = entryNode.path("trackMetadata");
				JsonNode jsonPrimeiroArtista = jsonTrackMetadataNode.path("artists").get(0);

				Musica musica = new Musica(
					jsonTrackMetadataNode.path("trackName").asText(),
					jsonPrimeiroArtista.path("name").asText(),
					getId(jsonPrimeiroArtista.path("spotifyUri").asText()),
					popularidade
					);

				listaDeMusicas.add(musica);

				popularidade++;
			}

		} catch (Exception e) {
			System.err.println("Música sem gênero registrado");
		}

		return listaDeMusicas;
	}


    	public static String getId(String uri) {
		int lastColonIndex = uri.lastIndexOf(":");

		if (lastColonIndex != -1) {            
			return uri.substring(lastColonIndex + 1);
		}
		
		return ""; 
	}
	

	public List<String> getGeneros(String idArtista) {

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
				return new ArrayList<>();
			}
			
			return serializarGenero(response.body());

		} catch (Exception e) {
			System.err.println("Erro inesperado: " + e.getMessage());
			return new ArrayList<>();
		}

	}


	private List<String> serializarGenero(String jsonString) {

		ObjectMapper mapper = new ObjectMapper();
		List<String> genresList = new ArrayList<>();

		try {

			JsonNode root = mapper.readTree(jsonString);
			JsonNode genresNode = root.path("genres");

			if (genresNode.isArray()) {
				//StringBuilder generos = new StringBuilder();
				for (JsonNode genreNode : genresNode) {
					genresList.add(genreNode.asText());
				}
			}

		} catch (Exception e) {
			return new ArrayList<>();
		}

		return genresList;
	}


	private String formatarGenero(String genero) {
		char primeiraLetra = Character.toUpperCase(genero.charAt(0));
		return primeiraLetra + genero.substring(1);
	}


	private String horarioAtual(){
		return time.format(formatador);
	}

}


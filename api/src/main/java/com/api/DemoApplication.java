package com.api;

import static com.api.Constantes.*;

import com.api.model.Musica;
import com.api.service.Spotify;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	
	private final Spotify spotify;


	public DemoApplication() {
		this.spotify = new Spotify();
	}


	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		List<Musica> listaDeMusicas = getMusicas();

	}


	public static List<Musica> getMusicas() {

		ArrayList<Musica> listaDeMusicas = new ArrayList<>();
		
		preencherListaDeMusicas(listaDeMusicas);

		System.out.println("\nPos preencher lista: " + listaDeMusicas.toString());
		return listaDeMusicas;
	}


	private static void preencherListaDeMusicas(List<Musica> listaDeMusicas) {
		
		Spotify api = new Spotify();

		List<Musica> novasMusicas = api.getListaDeMusicas();

		listaDeMusicas.addAll(novasMusicas);

		preencherGeneroDasMusicas(listaDeMusicas);
	}		


	private static void preencherGeneroDasMusicas(List<Musica> listaDeMusicas){
		
		Spotify api = new Spotify();

		for (Musica musica : listaDeMusicas) {
			
			String genero = api.getGenero(musica.getIdArtista());

			musica.setGenero(genero);
			
			System.out.println(musica.toString());
		}
	}
}

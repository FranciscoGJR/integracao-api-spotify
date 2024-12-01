package com.api;

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

		System.out.print(listaDeMusicas.toString());
	}


	public static List<Musica> getMusicas() {

		ArrayList<Musica> listaDeMusicas = new ArrayList<>();
		
		preencherListaDeMusicas(listaDeMusicas);

		preencherGeneroDasMusicas(listaDeMusicas);

		return listaDeMusicas;
	}


	private static void preencherListaDeMusicas(List<Musica> listaDeMusicas) {
		
		Spotify api = new Spotify();

		System.out.println(api.getListaDeMusicas());

		preencherGeneroDasMusicas(listaDeMusicas);
	}		


	private static void preencherGeneroDasMusicas(List<Musica> listaDeMusicas){
		
		Spotify api = new Spotify();

		String genero = api.getGenero("idArtista");
	}

}

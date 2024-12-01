package com.api;

import com.api.model.Musica;
import com.api.service.Spotify;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		ArrayList<Musica> listaDeMusicas = getMusicas();


		System.out.print(listaDeMusicas.toString());
	}


	public static ArrayList<Musica> getMusicas() {

		ArrayList<Musica> listaDeMusicas = new ArrayList<>();
		
		preencherListaDeMusicas(listaDeMusicas);

		preencherGeneroDasMusicas(listaDeMusicas);

		return listaDeMusicas;
	}


	private static void preencherListaDeMusicas(ArrayList<Musica> listaDeMusicas) {
		
		Spotify api = new Spotify();

		listaDeMusicas = api.getListaDeMusicas();

		preencherGeneroDasMusicas(listaDeMusicas);

	}		

	private static void preencherGeneroDasMusicas(ArrayList<Musica> listaDeMusicas){
		
		Spotify api = new Spotify();

		String genero = api.getGenero("idArtista");
	}


}

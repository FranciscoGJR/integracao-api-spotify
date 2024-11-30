package com.api;

import com.api.model.Musica;
import com.api.data.Input;
import com.api.request.Spotify;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		Input input = new Input();

		ArrayList<Musica> listaDeMusicas = input.getMusicas();

		preencherGenero(listaDeMusicas);

		mostrarRelacaoDeGenero(listaDeMusicas);

		System.out.print(listaDeMusicas.toString());
	}


	private static void preencherGenero(ArrayList<Musica> listaDeMusicas) {
		
		Spotify api = new Spotify();

		System.out.print(api.consultaGenero(""));
	}		


	private static void mostrarRelacaoDeGenero(ArrayList<Musica> listaDeMusicas) {

	}

}

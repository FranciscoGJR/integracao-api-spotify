package com.api;

import static com.api.Constantes.*;

import com.api.model.Musica;
import com.api.service.Spotify;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;

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

		LinkedHashMap<String, Integer> classificacao = montarClassificacao(listaDeMusicas);

		criaCsvClassificacao(classificacao, MES, ANO, CIDADE);

	}

	private static void criaCsvClassificacao(LinkedHashMap<String, Integer> classificacao, String mes, String ano, String cidade) {
		
		String nomeArquivo = cidade + "-" + mes + "-" + ano + ".csv";
		
		try (FileWriter writer = new FileWriter(nomeArquivo)){
			// Escreve o cabeçalho
			writer.append("genre,rank,month,year,city\n");

			// Adiciona as linhas no CSV
			for (Map.Entry<String, Integer> entry : classificacao.entrySet()) {
				writer.append(entry.getKey())
					  .append(",")
					  .append(entry.getValue().toString())
					  .append(",")
					  .append(mes)
					  .append(",")
					  .append(ano)
					  .append(",")
					  .append(cidade)
					  .append("\n");
			}

			System.out.println("Arquivo CSV criado com sucesso: " + nomeArquivo);
		} catch (Exception e) {
			System.err.println("Erro ao criar o arquivo CSV: " + e.getMessage());
		}
	}


	private static LinkedHashMap<String, Integer> montarClassificacao(List<Musica> listaDeMusicas) {
    		LinkedHashMap<String, Integer> placar = new LinkedHashMap<>();

			int classificacaoDoGenero = 0;

		for (Musica musica : listaDeMusicas) {
			
			List<String> generos = musica.getGeneros();
			classificacaoDoGenero++;

			for (String genero : generos) {
				
				if (genero.equals("") || placar.containsKey(genero)){
					continue;
				}

				placar.put(genero, classificacaoDoGenero);
				System.out.println(classificacaoDoGenero + " " + genero);
			}
		}

		return placar; 
	}


	public static List<Musica> getMusicas() {

		ArrayList<Musica> listaDeMusicas = new ArrayList<>();
		
		preencherListaDeMusicas(listaDeMusicas);

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
			
			List<String> generos = api.getGeneros(musica.getIdArtista());

			musica.setGeneros(generos);
			
			System.out.println(musica.toString());
		}
	}
}

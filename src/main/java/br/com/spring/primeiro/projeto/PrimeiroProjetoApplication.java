package br.com.spring.primeiro.projeto;

import br.com.spring.primeiro.projeto.models.EpisodeRecord;
import br.com.spring.primeiro.projeto.models.SeasonRecord;
import br.com.spring.primeiro.projeto.models.SerieRecord;
import br.com.spring.primeiro.projeto.services.ConsumoApi;
import br.com.spring.primeiro.projeto.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PrimeiroProjetoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PrimeiroProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=dexter&apikey=d2555974");
		var conversor = new ConverteDados();
		SerieRecord dadosSerie = conversor.obterDados(json, SerieRecord.class);
		json = consumoApi.obterDados("https://www.omdbapi.com/?t=dexter&season=2&episode=4&apikey=d2555974");
		EpisodeRecord dadosEp = conversor.obterDados(json, EpisodeRecord.class);

		System.out.println(dadosSerie);
		System.out.println(dadosEp);

		List<SeasonRecord> listaTempordas = new ArrayList<>();

		for (int i =1; i <= dadosSerie.totalTemporadas(); i++) {
			json = consumoApi.obterDados("https://www.omdbapi.com/?t=dexter&season="+ i +"&apikey=d2555974");
			SeasonRecord dadosTemporada = conversor.obterDados(json, SeasonRecord.class);
			listaTempordas.add(dadosTemporada);

		}
		listaTempordas.forEach(System.out::println);
	}
}

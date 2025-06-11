package br.com.spring.primeiro.projeto;

import br.com.spring.primeiro.projeto.models.SerieRecord;
import br.com.spring.primeiro.projeto.services.ConsumoApi;
import br.com.spring.primeiro.projeto.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrimeiroProjetoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PrimeiroProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=d2555974");
		System.out.println(json);
		var conversor = new ConverteDados();
		SerieRecord dadosRecord = conversor.obterDados(json, SerieRecord.class);
		System.out.println(dadosRecord);


	}
}

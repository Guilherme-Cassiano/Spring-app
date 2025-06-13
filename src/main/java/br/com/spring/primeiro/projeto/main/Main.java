package br.com.spring.primeiro.projeto.main;

import br.com.spring.primeiro.projeto.models.EpisodeRecord;
import br.com.spring.primeiro.projeto.models.SeasonRecord;
import br.com.spring.primeiro.projeto.models.SerieRecord;
import br.com.spring.primeiro.projeto.services.ConsumoApi;
import br.com.spring.primeiro.projeto.services.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private  ConsumoApi consumoApi = new ConsumoApi();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=d2555974";
    private  ConverteDados conversor = new ConverteDados();
    private List<SeasonRecord> listaTempordas = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    public void exibeMenu() {
        System.out.println("Digite o nome da serie que deseja consultar.");
        try {
            var nomeSerie = scanner.nextLine();
            var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

            SerieRecord dadosSerie = conversor.obterDados(json, SerieRecord.class);
            System.out.println(dadosSerie);

            for (int i =1; i <= dadosSerie.totalTemporadas(); i++) {
                json = consumoApi.obterDados(ENDERECO+ nomeSerie.replace(" ", "+") +"&season="+ i + API_KEY);
                SeasonRecord dadosTemporada = conversor.obterDados(json, SeasonRecord.class);
                listaTempordas.add(dadosTemporada);

            }
            listaTempordas.forEach(System.out::println);

            for (int i = 0; i < dadosSerie.totalTemporadas(); i++) {
                List<EpisodeRecord> listaEpisodios = listaTempordas.get(i).episodio();
                for (int j =0; j < listaEpisodios.size(); j++) {
                    
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }
}

package br.com.spring.primeiro.projeto.main;

import br.com.spring.primeiro.projeto.models.EpisodeRecord;
import br.com.spring.primeiro.projeto.models.Episodio;
import br.com.spring.primeiro.projeto.models.SeasonRecord;
import br.com.spring.primeiro.projeto.models.SerieRecord;
import br.com.spring.primeiro.projeto.services.ConsumoApi;
import br.com.spring.primeiro.projeto.services.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private  final ConsumoApi consumoApi = new ConsumoApi();
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

            listaTempordas.forEach(t -> t.episodio().forEach(e -> System.out.println(e.titulo())));

            List<EpisodeRecord> listaDadosSerie = listaTempordas.stream()
                    .flatMap(t -> t.episodio().stream())
                    .collect(Collectors.toList());

            System.out.println("\n TOP 5 EPS");
            listaDadosSerie.stream()
                    .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                    .sorted(Comparator.comparing(EpisodeRecord::avaliacao).reversed())
                    .limit(5)
                    .forEach(System.out::println);

            List<Episodio> episodios= listaTempordas.stream()
                    .flatMap(t -> t.episodio().stream()
                            .map(d -> new Episodio(t.numero(), d))
                    ).collect(Collectors.toList());

            System.out.println("A parir de qual ano você deseja consultar os episódios ?");
            var ano = scanner.nextInt();
            scanner.nextLine();

            LocalDate dataBusca = LocalDate.of(ano,1,1);
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            episodios.stream()
                    .filter(e -> e.getDataDeLancamento() != null && e.getDataDeLancamento().isAfter(dataBusca))
                    .forEach(e -> System.out.println(
                            "Temporada: " + e.getTemporada() +
                            " Episódio: " + e.getTitulo() +
                            " Data de lançamento: " + e.getDataDeLancamento().format(formatador)

                    ));

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }
}

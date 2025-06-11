package br.com.spring.primeiro.projeto.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// o que não tiver mapeado vai ignorar
@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieRecord(@JsonAlias("Title") String nome,
                          @JsonAlias("Year")String ano,
                          @JsonAlias("imdbVotes")String votos,
                          @JsonAlias("imdbRating")String avaliacao,
                          @JsonAlias("totalSeasons") Integer totalTemporadas) {
}

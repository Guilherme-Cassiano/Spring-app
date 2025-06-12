package br.com.spring.primeiro.projeto.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeRecord(@JsonAlias("Title") String titulo,
                            @JsonAlias("Episode") Integer numeroEp,
                            @JsonAlias("Released") String dataDeLancamento,
                            @JsonAlias("imdbRating") String avaliacao)  {
}

package br.com.spring.primeiro.projeto.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonRecord(@JsonAlias("Season") Integer numero,
                           @JsonAlias("Episodes") List<EpisodeRecord> episodio) {
}

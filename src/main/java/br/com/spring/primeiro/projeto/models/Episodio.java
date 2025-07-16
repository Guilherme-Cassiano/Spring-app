package br.com.spring.primeiro.projeto.models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEp;
    private LocalDate dataDeLancamento;
    private Double avaliacao;


    public Episodio(Integer numeroTemporada, EpisodeRecord d) {
        temporada = numeroTemporada;
        titulo = d.titulo();
        numeroEp = d.numeroEp();
        try {
            dataDeLancamento = LocalDate.parse(d.dataDeLancamento());
        } catch (DateTimeParseException e) {
            dataDeLancamento = null;
        }
        try {
            avaliacao = Double.valueOf(d.avaliacao());
        } catch (NumberFormatException e) {
            avaliacao = 0.0;
        }



    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEp() {
        return numeroEp;
    }

    public void setNumeroEp(Integer numeroEp) {
        this.numeroEp = numeroEp;
    }

    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(LocalDate dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "Temporada:" +
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEp=" + numeroEp +
                ", dataDeLancamento=" + dataDeLancamento +
                ", avaliacao=" + avaliacao;
    }
}

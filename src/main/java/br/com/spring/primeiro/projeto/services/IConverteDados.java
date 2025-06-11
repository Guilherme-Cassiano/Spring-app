package br.com.spring.primeiro.projeto.services;

public interface IConverteDados {
    //generics
    <T> T obterDados(String json, Class<T> classe);
}

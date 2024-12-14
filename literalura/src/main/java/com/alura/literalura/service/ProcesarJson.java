package com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProcesarJson {

    ObjectMapper objectMapper = new ObjectMapper();

    public String procesarJsonGeneral(String json){

        try {
            var resultadoAPI = objectMapper.readTree(json);
            var primerResultado = resultadoAPI.get("results").get(0).toString();
            return  primerResultado;
        } catch (JsonProcessingException e) {
            System.err.println("Error al procesar JSON: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("No se encontraron resultados para el libro.");
        }

        return null;
    }

    public String procesarJsonAutor(String json){

        try {
            var resultadoAPI = objectMapper.readTree(json);
            var primerResultado = resultadoAPI.get("results").get(0);
            var autor = primerResultado.get("authors").get(0).toString();
            return  autor;
        } catch (JsonProcessingException e) {
            System.err.println("Error al procesar JSON: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("No se encontraron resultados para el libro.");
        }

        return null;
    }

}

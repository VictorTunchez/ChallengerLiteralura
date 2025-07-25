package com.challenger.literalura.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorJSON implements IConversorJSON {

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        ObjectMapper objectMapper = new ObjectMapper();

        if (json == null || json.trim().isEmpty()) {
            throw new RuntimeException("El JSON está vacío o es nulo");
        }
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

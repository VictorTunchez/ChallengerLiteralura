package com.challenger.literalura.services;

public interface IConversorJSON {
    public <T> T obtenerDatos(String json, Class<T> clase);
}

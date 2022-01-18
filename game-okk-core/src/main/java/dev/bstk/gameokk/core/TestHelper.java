package dev.bstk.gameokk.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class TestHelper {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private TestHelper() { }

    public static <T> T fixure(final String pathFixture, final Class<T> clazz) {
        try {
            validarCaminhoArquivoFixtureResource(pathFixture);
            final var json = new File("src/test/resources" + pathFixture);
            return JSON_MAPPER.readValue(json, clazz);
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possivél localizar o json de Fixture", ex);
        }
    }

    private static void validarCaminhoArquivoFixtureResource(final String arquivoResourceJson) {
        if (Objects.isNull(arquivoResourceJson)
            || arquivoResourceJson.isEmpty()
            || arquivoResourceJson.isBlank()) {
            throw new IllegalArgumentException("Caminho arquivo json não pode ser nulo ou vazio!");
        }

        if (Boolean.FALSE.equals(arquivoResourceJson.startsWith("/"))) {
            throw new IllegalArgumentException("Caminho arquivo json deve iniciar com: '/'!");
        }

        if (Boolean.FALSE.equals(arquivoResourceJson.endsWith(".json"))) {
            throw new IllegalArgumentException("Caminho arquivo json ter a extensão .json!");
        }
    }

}

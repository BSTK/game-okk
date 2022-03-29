package dev.bstk.gameokkjogoforca.domain.service.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.gameokkjogoforca.domain.model.Dica;
import dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PalavraSecretaFactory {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final String RESOURCES_FORCA_PALAVRAS_SECRETAS = "src/main/resources/forca/palavra-secreta.json";

    private PalavraSecretaFactory() { }

    public static PalavraSecreta palavra() {
        final var palavraSecretas = carregarArquivoJsonComBancoDePalavrasSecretas();
        final var totalPalavras = palavraSecretas.size();
        final var palavraSecretaIndex = SECURE_RANDOM.nextInt(totalPalavras);

        final var palavraSecreta = palavraSecretas.get(palavraSecretaIndex);

        return PalavraSecretaBuilder
            .builder()
            .palavra(palavraSecreta.getPalavra())
            .dicas(palavraSecreta.getDicas())
            .build();
    }

    private static List<PalavraSecreta> carregarArquivoJsonComBancoDePalavrasSecretas() {
        try {
            final var json = new File(RESOURCES_FORCA_PALAVRAS_SECRETAS);
            final var palavrasSecretas = JSON_MAPPER.readValue(json, PalavraSecreta[].class);

            return Arrays.asList(palavrasSecretas);
        } catch (IOException ex) {
            throw new ArquivoNaoEncontradoException("Não foi possivél localizar o json!", ex);
        }
    }

    private static final class PalavraSecretaBuilder {

        private final PalavraSecreta palavra;

        public PalavraSecretaBuilder() {
            this.palavra = new PalavraSecreta();
            this.palavra.setPalavra(Collections.emptyList());
            this.palavra.setDicas(Collections.emptyList());
        }

        public static PalavraSecretaBuilder builder() {
            return new PalavraSecretaBuilder();
        }

        public PalavraSecretaBuilder palavra(final List<String> palavraSercreta) {
            this.palavra.setPalavra(palavraSercreta);
            return this;
        }

        public PalavraSecretaBuilder dicas(final List<Dica> dicas) {
            this.palavra.setDicas(dicas);
            return this;
        }

        public PalavraSecreta build() {
            return this.palavra;
        }
    }
}

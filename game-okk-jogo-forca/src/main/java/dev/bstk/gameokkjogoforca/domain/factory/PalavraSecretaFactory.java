package dev.bstk.gameokkjogoforca.domain.factory;

import dev.bstk.gameokkjogoforca.domain.model.Dica;
import dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta;

import java.util.Collections;
import java.util.List;

public class PalavraSecretaFactory {

    private PalavraSecretaFactory() { }

    public static PalavraSecreta palavra() {
        return PalavraSecretaBuilder.builder().build();
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

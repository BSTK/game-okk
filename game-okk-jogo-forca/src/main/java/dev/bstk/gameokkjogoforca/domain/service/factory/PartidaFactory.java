package dev.bstk.gameokkjogoforca.domain.service.factory;

import dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta;
import dev.bstk.gameokkjogoforca.domain.model.Partida;
import dev.bstk.gameokkjogoforca.domain.model.PartidaSatus;

import java.util.Collections;
import java.util.List;

public class PartidaFactory {

    public static final List<String> ALFABETO = List.of(
        "A","B","C","D","E","F","G","H","I","J","K","L","M",
        "N","O","P","Q","R","S","T","U","V","W","X","Y","Z");

    private PartidaFactory() { }

    public static Partida partida() {
        return PartidaBilder
            .builder()
            .palavraSecreta(PalavraSecretaFactory.palavra())
            .build();
    }

    private static final class PartidaBilder {

        private final Partida partida;

        public PartidaBilder() {
            this.partida = new Partida();
            this.partida.setAlfabeto(ALFABETO);
            this.partida.setPalavraSecreta(PalavraSecretaFactory.palavra());
            this.partida.setLetrasCorretas(Collections.emptyList());
            this.partida.setLetrasIncorretas(Collections.emptyList());
            this.partida.setStatus(PartidaSatus.EM_ANDAMENTO);
        }

        public static PartidaBilder builder() {
            return new PartidaBilder();
        }

        public PartidaBilder alfabeto(final List<String> alfabeto) {
            this.partida.setAlfabeto(alfabeto);
            return this;
        }

        public PartidaBilder palavraSecreta(final PalavraSecreta palavraSecreta) {
            this.partida.setPalavraSecreta(palavraSecreta);
            return this;
        }

        public PartidaBilder letrasCorretas(final List<String> letrasCorretas) {
            this.partida.setLetrasCorretas(letrasCorretas);
            return this;
        }

        public PartidaBilder letrasIncorretas(final List<String> letrasIncorretas) {
            this.partida.setLetrasIncorretas(letrasIncorretas);
            return this;
        }

        public PartidaBilder status(final PartidaSatus status) {
            this.partida.setStatus(status);
            return this;
        }

        public Partida build() {
            return this.partida;
        }
    }
}

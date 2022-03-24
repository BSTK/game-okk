package dev.bstk.gameokkjogoforca.domain.factory;

import dev.bstk.gameokkjogoforca.domain.model.Partida;
import dev.bstk.gameokkjogoforca.domain.model.PartidaSatus;

import java.util.Collections;
import java.util.List;

public class PartidaFactory {

    private static final List<String> ALFABETO = List.of(
        "A","B","C","D","E","F","G","H","I","J","K","L","M",
        "N","O","P","Q","R","S","T","U","V","W","X","Y","Z");

    private PartidaFactory() { }

    public static Partida partida() {
        return Partida
            .builder()
            .alfabeto(ALFABETO)
            .palavraSecreta(PalavraSecretaFactory.palavra())
            .letrasCorretas(Collections.emptyList())
            .letrasIncorretas(Collections.emptyList())
            .dicas(Collections.emptyList())
            .status(PartidaSatus.EM_ANDAMENTO)
            .build();
    }
}

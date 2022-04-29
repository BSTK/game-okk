package dev.bstk.gameokkjogoforca.domain.service;

import dev.bstk.gameokkjogoforca.domain.model.Dica;
import dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta;
import dev.bstk.gameokkjogoforca.domain.model.Partida;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta.MASCARA;

@Component
public class EsconderLetraService {


    public Partida esconder(final Partida partida) {
        final var palavraSecretaMascarada = partida.getPalavraSecreta();

        if (partida.inicioDePartida()) {
            esconderTodasLetras(partida);
        } else {
            esconderTodasLetrasNaoDescobertas(partida);
        }

        final var dicasVisiveis = palavraSecretaMascarada.getDicas()
            .stream()
            .filter(Dica::isVisivel)
            .collect(Collectors.toList());

        palavraSecretaMascarada.setDicas(dicasVisiveis);

        return partida;
    }

    private void esconderTodasLetras(final Partida partida) {
        final var palavraEscondida = partida
            .getPalavraSecreta()
            .getPalavra()
            .stream()
            .map(letra -> MASCARA)
            .collect(Collectors.toList());

        partida
            .getPalavraSecreta()
            .setPalavra(palavraEscondida);
    }

    private void esconderTodasLetrasNaoDescobertas(final Partida partida) {
        final var palavraComLetrasEscondidas = new ArrayList<String>();

        for (String letra : partida.getPalavraSecreta().getPalavra()) {
            final var letraDesmascarada = partida.errouLetra(letra) ? MASCARA : letra;
            palavraComLetrasEscondidas.add(letraDesmascarada);
        }

        partida
            .getPalavraSecreta()
            .setPalavra(palavraComLetrasEscondidas);
    }
}

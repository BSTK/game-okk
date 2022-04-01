package dev.bstk.gameokkjogoforca.domain.service;

import dev.bstk.gameokkjogoforca.domain.model.Dica;
import dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta;
import dev.bstk.gameokkjogoforca.domain.model.Partida;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class EsconderLetraService {


    public Partida esconder(final Partida partida) {
        final var palavraSecretaMascarada = partida.getPalavraSecreta();

        if (inicioDePartida(partida)) {
            esconderTodasLetras(palavraSecretaMascarada);
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

    public void esconderTodasLetras(final PalavraSecreta palavraSecreta) {
        final var palavraEscondida = palavraSecreta.getPalavra()
            .stream()
            .map(letra -> PalavraSecreta.CARACTERE_MASCARA)
            .collect(Collectors.toList());

        palavraSecreta.setPalavra(palavraEscondida);
    }

    public void esconderTodasLetrasNaoDescobertas(final Partida partida) {
        final var palavraComLetrasEscondidas = new ArrayList<String>();

        for (String letra : partida.getPalavraSecreta().getPalavra()) {
            final var letraDesmascarada = partida.acertouLetra(letra)
                ? letra
                : PalavraSecreta.CARACTERE_MASCARA;

            palavraComLetrasEscondidas.add(letraDesmascarada);
        }

        partida.getPalavraSecreta().setPalavra(palavraComLetrasEscondidas);
    }

    private boolean inicioDePartida(final Partida partida) {
        return partida.getLetrasCorretas().isEmpty()
            && partida.getLetrasIncorretas().isEmpty();
    }
}

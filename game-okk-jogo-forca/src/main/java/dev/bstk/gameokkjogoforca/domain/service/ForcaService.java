package dev.bstk.gameokkjogoforca.domain.service;

import dev.bstk.gameokkjogoforca.domain.model.Dica;
import dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta;
import dev.bstk.gameokkjogoforca.domain.model.Partida;
import dev.bstk.gameokkjogoforca.domain.repository.PartidaRepository;
import dev.bstk.gameokkjogoforca.domain.service.factory.PartidaFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForcaService {


    private final PartidaRepository partidaRepository;

    public Partida partida(final UUID partidaId) {
        final var partidaEmAndamento = partidaRepository
            .obterPartidaEmAndamento(partidaId)
            .orElseThrow(() -> new IllegalArgumentException("Partida não encontrada!"));

        return mascararPartida(partidaEmAndamento);
    }

    /// TODO: IMPLEMENTAR
    public Partida jogar(final String letra, final UUID partidaId) {
        log.info("jogar = Partida: [ {} ] | Letra: [ {} ]", partidaId, letra);

        final var partidaEmAndamento = partida(partidaId);
        final var palavraSecreta = partidaEmAndamento.getPalavraSecreta();

        if (palavraSecreta.getPalavra().contains(letra)) {
            partidaEmAndamento.getLetrasCorretas().add(letra);
        } else {
            partidaEmAndamento.getLetrasIncorretas().add(letra);
        }

        return mascararPartida(partidaEmAndamento);
    }

    public Partida novaPartida() {
        final var novaPartidaCriada = PartidaFactory.partida();
        final var novaPartidaCriadaSalva = partidaRepository.save(novaPartidaCriada);

        return mascararPartida(novaPartidaCriadaSalva);
    }

    /// TODO: MOSTAR AS LETRAS JA DESCOBERTAS
    private Partida mascararPartida(final Partida partidaASerMascarada) {
        final var palavraComLetrasDesmascaradas = new ArrayList<String>();
        final var palavraSecretaMascarada = partidaASerMascarada.getPalavraSecreta();

        if (!partidaASerMascarada.getLetrasCorretas().isEmpty()) {
            for (String letraPalavra : palavraSecretaMascarada.getPalavra()) {
                for (String letraCorreta : partidaASerMascarada.getLetrasCorretas()) {
                    final var letraDesmascarada = (!letraPalavra.equalsIgnoreCase(letraCorreta))
                        ? PalavraSecreta.CARACTERE_MASCARA
                        : letraPalavra;

                    palavraComLetrasDesmascaradas.add(letraDesmascarada);
                }
            }
        } else {
            palavraComLetrasDesmascaradas.addAll(palavraSecretaMascarada.getPalavra());
        }

        final var dicasVisiveis = palavraSecretaMascarada.getDicas()
            .stream()
            .filter(Dica::isVisivel)
            .collect(Collectors.toList());

        palavraSecretaMascarada.setDicas(dicasVisiveis);
        palavraSecretaMascarada.setPalavra(palavraComLetrasDesmascaradas);

        partidaASerMascarada.setPalavraSecreta(palavraSecretaMascarada);

        return partidaASerMascarada;
    }
}

package dev.bstk.gameokkjogoforca.domain.service;

import dev.bstk.gameokkjogoforca.domain.model.Partida;
import dev.bstk.gameokkjogoforca.domain.repository.PartidaRepository;
import dev.bstk.gameokkjogoforca.domain.service.factory.PartidaFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForcaService {

    private final PartidaRepository partidaRepository;
    private final EsconderLetraService esconderLetraService;


    public Partida partida(final UUID partidaId) {
        log.info("partida() =  [ {} ]", partidaId);
        final var partidaEmAndamento = partidaEmAndamento(partidaId);

        return esconderLetraService.esconder(partidaEmAndamento);
    }

    public Partida jogar(final String letra, final UUID partidaId) {
        log.info("jogar = Partida: [ {} ] | Letra: [ {} ]", partidaId, letra);

        final var partidaEmAndamento = partidaEmAndamento(partidaId);
        final var palavraSecreta = partidaEmAndamento.getPalavraSecreta();

        if (palavraSecreta.getPalavra().contains(letra)) {
            partidaEmAndamento.getLetrasCorretas().add(letra);
        } else {
            partidaEmAndamento.getLetrasIncorretas().add(letra);
        }

        final var partidaEmAndamentoSalva = partidaRepository.save(partidaEmAndamento);
        
        return esconderLetraService.esconder(partidaEmAndamentoSalva);
    }

    public Partida novaPartida() {
        log.info("novaPartida()");

        final var novaPartidaCriada = PartidaFactory.partida();
        final var novaPartidaCriadaSalva = partidaRepository.save(novaPartidaCriada);

        return esconderLetraService.esconder(novaPartidaCriadaSalva);
    }

    private Partida partidaEmAndamento(final UUID partidaId) {
        return partidaRepository
            .obterPartidaEmAndamento(partidaId)
            .orElseThrow(() -> new IllegalArgumentException("Partida não encontrada!"));
    }
}

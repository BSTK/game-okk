package dev.bstk.gameokkjogoforca.domain.service;

import dev.bstk.gameokkjogoforca.domain.model.Partida;
import dev.bstk.gameokkjogoforca.domain.repository.PartidaRepository;
import dev.bstk.gameokkjogoforca.domain.service.factory.PartidaFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta.MASCARA;
import static dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta.TOTAL_ERROS;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForcaService {

    private final PartidaRepository partidaRepository;
    private final EsconderLetraService esconderLetraService;


    public Partida partida(final UUID partidaId) {
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
            partidaEmAndamento.incrementarTotalErros();
        }

        final var partidaValidaGanhouPerdeu = esconderLetraService.esconder(partidaEmAndamento);

        final var terminouPartidaPerdeu = partidaValidaGanhouPerdeu.getTotalErros() == TOTAL_ERROS;
        final var terminouPartidaGanhou = partidaValidaGanhouPerdeu
            .getPalavraSecreta()
            .getPalavra()
            .contains(MASCARA);

        partidaEmAndamento.setTerminouPartidaPerdeu(terminouPartidaPerdeu);
        partidaEmAndamento.setTerminouPartidaGanhou(terminouPartidaGanhou);

        final var partidaEmAndamentoSalva = partidaRepository.save(partidaEmAndamento);
        return esconderLetraService.esconder(partidaEmAndamentoSalva);
    }

    public Partida novaPartida() {
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

package dev.bstk.gameokkjogoforca.domain;

import dev.bstk.gameokkjogoforca.domain.factory.PartidaFactory;
import dev.bstk.gameokkjogoforca.domain.model.Dica;
import dev.bstk.gameokkjogoforca.domain.model.Partida;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForcaService {


    private final PartidaRepository partidaRepository;


    public Partida novaPartida() {
        final var novaPartidaCriada = PartidaFactory.partida();
        final var novaPartidaCriadaSalva = partidaRepository.save(novaPartidaCriada);

        final var palavraSecretaMascarada = novaPartidaCriadaSalva.getPalavraSecreta();
        final var palavraMascarada = palavraSecretaMascarada.getPalavra()
            .stream()
            .map(letra -> "▬")
            .collect(Collectors.toList());

        final var dicasVisiveis = palavraSecretaMascarada.getDicas()
            .stream()
            .filter(Dica::isVisivel)
            .collect(Collectors.toList());

        palavraSecretaMascarada.setPalavra(palavraMascarada);
        palavraSecretaMascarada.setDicas(dicasVisiveis);

        novaPartidaCriadaSalva.setPalavraSecreta(palavraSecretaMascarada);

        return novaPartidaCriadaSalva;
    }


    /// TODO: IMPLEMENTAR
    public Partida partida(final Long partidaId) {
        return PartidaFactory.partida();
    }

    /// TODO: IMPLEMENTAR
    public Partida jogar(final String letra, final Long partidaId) {
        return PartidaFactory.partida();
    }
}

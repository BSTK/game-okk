package dev.bstk.gameokkjogoforca.domain;

import dev.bstk.gameokkjogoforca.domain.factory.PartidaFactory;
import dev.bstk.gameokkjogoforca.domain.model.Partida;
import org.springframework.stereotype.Service;

@Service
public class ForcaService {

    /// TODO: IMPLEMENTAR
    public Partida partida(final Long partidaId) {
        return PartidaFactory.partida();
    }

    /// TODO: IMPLEMENTAR
    public Partida novaPartida() {
        return PartidaFactory.partida();
    }

    /// TODO: IMPLEMENTAR
    public Partida jogar(final String letra, final Long partidaId) {
        return PartidaFactory.partida();
    }
}

package dev.bstk.gameokkjogomemoria.domain;

import dev.bstk.gameokkjogomemoria.domain.model.Partida;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemoriaService {


    public Partida partida(final UUID partidaId) {
        return new Partida();
    }

    public Object jogar(final Integer numeroCarta, final UUID partidaId) {
        return new Object();
    }

    public Partida novaPartida() {
        return new Partida();
    }
}

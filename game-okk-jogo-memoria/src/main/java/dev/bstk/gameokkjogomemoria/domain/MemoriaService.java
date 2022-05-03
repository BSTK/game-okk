package dev.bstk.gameokkjogomemoria.domain;

import dev.bstk.gameokkjogomemoria.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MemoriaService {


    public Partida partida(final UUID partidaId) {
        return new Partida();
    }

    public Object jogar(final Integer numeroCarta, final UUID partidaId) {
        return new Object();
    }

    public List<Nivel> niveis() {
        return Arrays.asList(Nivel.values());
    }

    public Partida novaPartida() {
        List<Carta> cartas = Arrays.asList(
            new Carta(1, "imagem-1.png", Carta.Status.INVISIVEL),
            new Carta(2, "imagem-2.png", Carta.Status.VISIVEL),
            new Carta(3, "imagem-2.png", Carta.Status.VISIVEL),
            new Carta(4, "imagem-4.png", Carta.Status.INVISIVEL));

        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.setNivel(Nivel.MEDIO);
        tabuleiro.setCartas(cartas);

        Partida partida = new Partida();
        partida.setUuid(UUID.randomUUID());
        partida.setDataInicio(new Date());
        partida.setDataFim(new Date());
        partida.setStatus(Status.EM_ANDAMENTO);
        partida.setTabuleiro(tabuleiro);

        return partida;
    }
}

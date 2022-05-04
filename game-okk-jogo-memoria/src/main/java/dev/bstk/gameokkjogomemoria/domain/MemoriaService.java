package dev.bstk.gameokkjogomemoria.domain;

import dev.bstk.gameokkjogomemoria.api.request.NovaPartidaRequest;
import dev.bstk.gameokkjogomemoria.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemoriaService {

    private final Random random = new Random();

    public Partida partida(final UUID partidaId) {
        return new Partida();
    }

    public Object jogar(final Integer numeroCarta, final UUID partidaId) {
        return new Object();
    }

    public List<Nivel> niveis() {
        return Arrays.asList(Nivel.values());
    }

    public Partida novaPartida(final NovaPartidaRequest request) {
        Nivel nivel = Nivel.valueOf(request.getNivel());

        final var cartas = new ArrayList<Carta>();

        for (int numeroCarta = 0; numeroCarta < (nivel.getQuantidadePares() * 2); numeroCarta += 2) {
            final var urlImagem = String.format("imagem-%s.png", numeroCarta);
            final var cartaA = new Carta(numeroCarta, urlImagem, Carta.Status.INVISIVEL);
            final var cartaB = new Carta((numeroCarta + 1), urlImagem, Carta.Status.INVISIVEL);

            cartas.add(cartaA);
            cartas.add(cartaB);
        }

        embaralhar(cartas);

        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.setNivel(nivel);
        tabuleiro.setCartas(cartas);

        Partida partida = new Partida();
        partida.setUuid(UUID.randomUUID());
        partida.setDataInicio(new Date());
        partida.setDataFim(new Date());
        partida.setStatus(Status.EM_ANDAMENTO);
        partida.setTabuleiro(tabuleiro);

        return partida;
    }

    private void embaralhar(List<Carta> sequenciasPares) {
        Collections.shuffle(sequenciasPares);
        Collections.reverse(sequenciasPares);
        Collections.shuffle(sequenciasPares);
        Collections.reverse(sequenciasPares);
    }
}

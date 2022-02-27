package dev.bstk.gameokkmatematica.domain.service;

import dev.bstk.gameokkmatematica.domain.Desafio;
import dev.bstk.gameokkmatematica.domain.DesafioOperacao;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class DesafioService {

    private static final int FATOR_MINIMO = 11;
    private static final int FATOR_MAXIMO = 100;

    private static final SecureRandom RANDOM = new SecureRandom();

    public Desafio gerarDesafioAleatorio() {
        final int fatorA = fator();
        final int fatorB = fator();
        final String operacao = operacao();
        final int[] alternativas = alternativas(fatorA, fatorB, operacao);

        return new Desafio(fatorA, fatorB, alternativas, operacao);
    }

    private String operacao() {
        return DesafioOperacao
            .values()[RANDOM.nextInt(DesafioOperacao.values().length)]
            .getOperador();
    }

    private int[] alternativas(final int fatorA, final int fatorB, final String operacao) {
        final int alternativaCorreta = DesafioOperacao.of(operacao).execute(fatorA, fatorB);
        final int[] alternativas = new int[] { fator(), fator(), fator(), alternativaCorreta };

        return embaralhar(alternativas);
    }

    private int[] embaralhar(final int[] alternativas) {
        for (int i = 0; i < alternativas.length; i++) {
            int indiceSwap = RANDOM.nextInt(alternativas.length);
            int indexTemporario = alternativas[indiceSwap];
            alternativas[indiceSwap] = alternativas[i];
            alternativas[i] = indexTemporario;
        }

        return alternativas;
    }

    private int fator() {
        return RANDOM.nextInt(FATOR_MAXIMO - FATOR_MINIMO) + FATOR_MINIMO;
    }
}

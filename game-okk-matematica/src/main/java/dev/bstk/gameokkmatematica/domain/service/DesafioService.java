package dev.bstk.gameokkmatematica.domain.service;

import dev.bstk.gameokkmatematica.api.request.DesafioTentativaRespostaRequest;
import dev.bstk.gameokkmatematica.domain.Desafio;
import dev.bstk.gameokkmatematica.domain.DesafioTentativaResposta;
import dev.bstk.gameokkmatematica.domain.Operacao;
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
        final String operacao = Operacao.operacaoAleatoria();
        final int[] alternativas = alternativas(fatorA, fatorB, operacao);

        return new Desafio(fatorA, fatorB, alternativas, operacao);
    }

    public DesafioTentativaResposta tentativaResposta(DesafioTentativaRespostaRequest request) {
        return null;
    }

    private int[] alternativas(final int fatorA, final int fatorB, final String operacao) {
        final int alternativaCorreta = Operacao.of(operacao).execute(fatorA, fatorB);
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

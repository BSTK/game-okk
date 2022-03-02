package dev.bstk.gameokkmatematica.domain;

import java.security.SecureRandom;
import java.util.stream.Stream;

public enum Operacao {

    ADICAO("+", "Adição", (fatorA, fatorB) -> fatorA + fatorB),
    DIVISAO("/", "Divisão", (fatorA, fatorB) -> fatorA / fatorB),
    SUBTRACAO("-", "Subtração", (fatorA, fatorB) -> fatorA - fatorB),
    MULTIPLICACAO("*", "Multiplicação", (fatorA, fatorB) -> fatorA * fatorB);

    private static final SecureRandom RANDOM = new SecureRandom();

    private final String operador;
    private final String descricao;
    private final ExecutaOperacao executar;

    Operacao(final String operador,
             final String descricao,
             final ExecutaOperacao executar) {
        this.operador = operador;
        this.descricao = descricao;
        this.executar = executar;
    }

    public static Operacao of(final String operador) {
        return Stream
            .of(values())
            .filter(operacao -> operador.equals(operacao.getOperador()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(String.format("Operador inválido ( %s ).", operador)));
    }

    public static String operacaoAleatoria() {
        return Operacao
            .values()[RANDOM.nextInt(Operacao.values().length)]
            .getOperador();
    }

    public int execute(final int fatorA, final int fatorB) {
        return executar.execute(fatorA, fatorB);
    }

    public String getOperador() {
        return operador;
    }

    public String getDescricao() {
        return descricao;
    }

    public interface ExecutaOperacao {
        int execute(final int fatorA, final int fatorB);
    }
}

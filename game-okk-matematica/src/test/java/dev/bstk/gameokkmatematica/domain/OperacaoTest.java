package dev.bstk.gameokkmatematica.domain;

import dev.bstk.gameokkmatematica.domain.model.Operacao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class OperacaoTest {

    @Test
    @DisplayName("Deve retornar uma operacao aleatória valida")
    void deveRetornarUmaOperacaoAleatoriaValida() {
        final var operacao = Operacao.operacaoAleatoria();

        Assertions.assertThat(operacao)
            .isNotNull()
            .isNotBlank()
            .isNotEmpty();

        String[] opedoresValidos = new String[] { "*","/","+","-" };

        Assertions
            .assertThat(opedoresValidos)
            .contains(operacao);
    }

    @ParameterizedTest
    @ValueSource(strings = { "+","-","*","/" })
    @DisplayName("Deve retornar uma operacao valida")
    void deveRetornarUmaOperacaoValida(final String operador) {
        final var operacao = Operacao.of(operador);

        Assertions.assertThat(operacao).isNotNull();
        Assertions.assertThat(operacao.getOperador()).isEqualTo(operador);
    }

    @Test
    @DisplayName("Deve retornar uma operacao valida")
    void deveRetornarUmaOperacaoValida() {
        Assertions.assertThat(Operacao.of("+")).isEqualTo(Operacao.ADICAO);
        Assertions.assertThat(Operacao.of("/")).isEqualTo(Operacao.DIVISAO);
        Assertions.assertThat(Operacao.of("-")).isEqualTo(Operacao.SUBTRACAO);
        Assertions.assertThat(Operacao.of("*")).isEqualTo(Operacao.MULTIPLICACAO);
    }

    @ParameterizedTest
    @ValueSource(strings = {"&","@","#","$","%","(",")","'","{","}","º","ª","¬"})
    @DisplayName("Deve lancar excessao para operacao inválida")
    void deveLancarExcessaoParaOperacaoInvalida(final String operador) {
        Assertions
            .assertThatThrownBy(() -> Operacao.of(operador))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format("Operador inválido ( %s ).", operador));
    }

    @ParameterizedTest
    @MethodSource("deveRetornarResultadoValidoDeUmaOperacaoParametros")
    @DisplayName("Deve retornar uma operacao valida")
    void deveRetornarResultadoValidoDeUmaOperacao(final int fatorA,
                                                  final int fatorB,
                                                  final int resultado,
                                                  final String operador) {
        int resultadoOperacao = Operacao.of(operador).execute(fatorA, fatorB);
        Assertions.assertThat(resultadoOperacao).isEqualTo(resultado);
    }

    static Stream<Arguments> deveRetornarResultadoValidoDeUmaOperacaoParametros() {
        return Stream.of(
            Arguments.arguments("10", "10", "20", "+"),
            Arguments.arguments("10", "10", "0", "-"),
            Arguments.arguments("10", "10", "100", "*"),
            Arguments.arguments("10", "10", "1", "/")
        );
    }

}

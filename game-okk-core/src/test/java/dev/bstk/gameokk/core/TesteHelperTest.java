package dev.bstk.gameokk.core;

import dev.bstk.gameokk.core.exceptions.ArquivoFixtureNaoEncontradoException;
import dev.bstk.gameokk.dto.DadoTeste;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TesteHelperTest {

    @Test
    void deveRetornarUmObjetoParseadoDeUmJsonValido() {
        final var fixure = TesteHelper.fixure("/dados-teste.json", DadoTeste.class);

        Assertions
            .assertThat(fixure)
            .isNotNull();

        Assertions
            .assertThat(fixure.valorA())
            .isNotBlank()
            .isNotEmpty()
            .isEqualTo("Valor de A");

        Assertions
            .assertThat(fixure.valorB())
            .isNotNull()
            .isNotNegative()
            .isPositive()
            .isEqualTo(100);

        Assertions
            .assertThat(fixure.valores())
            .isNotNull()
            .isNotEmpty()
            .contains(
                "Item A",
                "Item B",
                "Item C",
                "Item D"
            );
    }

    @Test
    void deveLancarExcecaoComCaminhoJsonInvalidoNulo() {
        Assertions
            .assertThatThrownBy(() -> TesteHelper.fixure("", DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json não pode ser nulo ou vazio!");
    }

    @Test
    void deveLancarExcecaoComCaminhoJsonInvalidoVazio() {
        Assertions
            .assertThatThrownBy(() -> TesteHelper.fixure("", DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json não pode ser nulo ou vazio!");
    }

    @Test
    void deveLancarExcecaoComCaminhoJsonInvalidoEspacoEmBranco() {
        Assertions
            .assertThatThrownBy(() -> TesteHelper.fixure("       ", DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json não pode ser nulo ou vazio!");
    }

    @Test
    void deveLancarExcecaoDeArquivoFixtureNaoEncontrado() {
        Assertions
            .assertThatThrownBy(() -> TesteHelper.fixure("/arquivo-nao-existe.json", DadoTeste.class))
            .isInstanceOf(ArquivoFixtureNaoEncontradoException.class)
            .hasMessage("Não foi possivél localizar o json de Fixture!");
    }

    @ParameterizedTest
    @ValueSource(strings = { "dados-teste.json", "\\dados-teste.json", "@dados-teste.json" })
    void deveLancarExcecaoComCaminhoJsonInvalidoNaoComecandoComBarra(final String fixture) {
        Assertions
            .assertThatThrownBy(() -> TesteHelper.fixure(fixture, DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json deve iniciar com: '/'!");
    }

    @ParameterizedTest
    @ValueSource(strings = { "/dados-teste.pdf", "/dados-teste.txt", "/dados-teste" })
    void deveLancarExcecaoComCaminhoJsonComExtensaoInvalida(final String fixture) {
        Assertions
            .assertThatThrownBy(() -> TesteHelper.fixure(fixture, DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json ter a extensão .json!");
    }

}

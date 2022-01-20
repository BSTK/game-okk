package dev.bstk.gameokk.core;

import dev.bstk.gameokk.core.exceptions.ArquivoFixtureNaoEncontradoException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

public class TesteHelperTest {

    @Test
    public void deveRetornarUmObjetoParseadoDeUmJsonValido() {
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
    public void deveLancarExcecaoComCaminhoJsonInvalidoNulo() {
        Assertions
            .assertThatThrownBy(() -> TesteHelper.fixure("", DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json não pode ser nulo ou vazio!");
    }

    @Test
    public void deveLancarExcecaoComCaminhoJsonInvalidoVazio() {
        Assertions
            .assertThatThrownBy(() -> TesteHelper.fixure("", DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json não pode ser nulo ou vazio!");
    }

    @Test
    public void deveLancarExcecaoComCaminhoJsonInvalidoEspacoEmBranco() {
        Assertions
            .assertThatThrownBy(() -> TesteHelper.fixure("       ", DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json não pode ser nulo ou vazio!");
    }

    @Test
    public void deveLancarExcecaoComCaminhoJsonInvalidoNaoComecandoComBarra() {
        Arrays.asList(
            "dados-teste.json",
            "\\dados-teste.json",
            "@dados-teste.json")
            .forEach(fixture ->
                Assertions
                .assertThatThrownBy(() -> TesteHelper.fixure(fixture, DadoTeste.class))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Caminho arquivo json deve iniciar com: '/'!"));
    }

    @Test
    public void deveLancarExcecaoComCaminhoJsonComExtensaoInvalida() {
        Arrays.asList(
                "/dados-teste.pdf",
                "/dados-teste.txt",
                "/dados-teste")
            .forEach(fixture ->
                Assertions
                    .assertThatThrownBy(() -> TesteHelper.fixure(fixture, DadoTeste.class))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Caminho arquivo json ter a extensão .json!"));
    }

    @Test
    public void deveLancarExcecaoDeArquivoFixtureNaoEncontrado() {
        Assertions
            .assertThatThrownBy(() -> TesteHelper.fixure("/arquivo-nao-existe.json", DadoTeste.class))
            .isInstanceOf(ArquivoFixtureNaoEncontradoException.class)
            .hasMessage("Não foi possivél localizar o json de Fixture!");
    }

    record DadoTeste(
        String valorA,
        Integer valorB,
        String[] valores) {
    }

}

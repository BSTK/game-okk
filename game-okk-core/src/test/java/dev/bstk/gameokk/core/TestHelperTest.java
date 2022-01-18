package dev.bstk.gameokk.core;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

public class TestHelperTest {

    @Test
    public void deveRetornarUmObjetoParseadoDeUmJsonValido() {
        final var fixure = TestHelper.fixure("/dados-teste.json", DadoTeste.class);

        Assertions.assertThat(fixure).isNotNull();
        Assertions.assertThat(fixure.valorA())
            .isNotBlank()
            .isNotEmpty()
            .isEqualTo("Valor de A");

        Assertions.assertThat(fixure.valorB())
            .isNotNull()
            .isNotNegative()
            .isPositive()
            .isEqualTo(100);

        Assertions.assertThat(fixure.valores())
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
    public void deveLancarExcecaoComCaminhoJasonInvalidoNulo() {
        Assertions
            .assertThatThrownBy(() -> TestHelper.fixure("", DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json não pode ser nulo ou vazio!");
    }

    @Test
    public void deveLancarExcecaoComCaminhoJasonInvalidoVazio() {
        Assertions
            .assertThatThrownBy(() -> TestHelper.fixure("", DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json não pode ser nulo ou vazio!");
    }

    @Test
    public void deveLancarExcecaoComCaminhoJasonInvalidoEspacoEmBranco() {
        Assertions
            .assertThatThrownBy(() -> TestHelper.fixure("       ", DadoTeste.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Caminho arquivo json não pode ser nulo ou vazio!");
    }

    @Test
    public void deveLancarExcecaoComCaminhoJasonInvalidoNaoComecandoComBarra() {
        Arrays.asList(
            "dados-teste.json",
            "\\dados-teste.json",
            "@dados-teste.json")
            .forEach(fixture ->
                Assertions
                .assertThatThrownBy(() -> TestHelper.fixure(fixture, DadoTeste.class))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Caminho arquivo json deve iniciar com: '/'!"));
    }

    public record DadoTeste(
        String valorA,
        Integer valorB,
        String[] valores) {
    }

}

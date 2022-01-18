package dev.bstk.gameokk.core;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestHelperTest {

    private ExpectedException exception = ExpectedException.none();

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
    

    public record DadoTeste(
        String valorA,
        Integer valorB,
        String[] valores) {
    }

}

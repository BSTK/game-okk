package dev.bstk.gameokk.core;

import dev.bstk.gameokk.dto.DadoRequest;
import dev.bstk.gameokk.dto.DadoResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MapperTest {

    @Test
    void deveRetornarUmObjetoMapeadoDadoObjeto() {
        final var dadoRequest = new DadoRequest();
        dadoRequest.setValorA("VALOR_AAA");
        dadoRequest.setValorB("VALOR_BBB");

        final var dadoResponse = Mapper.to(dadoRequest, DadoResponse.class);

        Assertions
            .assertThat(dadoResponse)
            .isInstanceOf(DadoResponse.class)
            .isNotNull();

        Assertions
            .assertThat(dadoResponse.getValorA())
            .isEqualTo(dadoRequest.getValorA());

        Assertions
            .assertThat(dadoResponse.getValorB())
            .isEqualTo(dadoRequest.getValorB());
    }

    @Test
    void deveRetornarUmaListaDeObjetoMapeadosDadoUmaClasse() {
        final var dadoRequestA = new DadoRequest();
        dadoRequestA.setValorA("RequestA_VALOR_AAA");
        dadoRequestA.setValorA("RequestA_VALOR_BBB");

        final var dadoRequestB = new DadoRequest();
        dadoRequestB.setValorA("RequestB_VALOR_AAA");
        dadoRequestB.setValorB("RequestB_VALOR_BBB");

        final var dadoRequestC = new DadoRequest();
        dadoRequestC.setValorA("RequestC_VALOR_AAA");
        dadoRequestC.setValorB("RequestC_VALOR_BBB");

        final var dadosRequests = List.of(dadoRequestA, dadoRequestB, dadoRequestC);

        final var dadosResponses = Mapper.list(dadosRequests, DadoResponse.class);

        Assertions
            .assertThat(dadosResponses)
            .isNotNull()
            .isNotEmpty()
            .hasOnlyElementsOfType(DadoResponse.class)
            .hasSize(dadosRequests.size());
    }
}

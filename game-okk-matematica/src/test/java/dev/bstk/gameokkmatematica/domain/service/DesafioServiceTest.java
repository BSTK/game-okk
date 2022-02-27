package dev.bstk.gameokkmatematica.domain.service;

import dev.bstk.gameokkmatematica.domain.DesafioOperacao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DesafioServiceTest {

    @Spy
    private DesafioService desafioService;

    @Test
    @DisplayName("Deve gerar um desafio aleatorio com dados validos")
    void deveGerarUmDesafioAleatorioComDadosValidos() {
        final var desafio = desafioService.gerarDesafioAleatorio();

        final var resultadoCorreto = DesafioOperacao
            .of(desafio.getOperacao())
            .execute(desafio.getFatorA(), desafio.getFatorB());

        Assertions.assertThat(desafio).isNotNull();
        Assertions.assertThat(desafio.getAlternativas()).hasSize(4);
        Assertions.assertThat(desafio.getAlternativas()).contains(resultadoCorreto);
    }
}

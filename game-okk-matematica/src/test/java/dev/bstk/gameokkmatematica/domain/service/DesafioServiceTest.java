package dev.bstk.gameokkmatematica.domain.service;

import dev.bstk.gameokkmatematica.api.request.DesafioTentativaRespostaRequest;
import dev.bstk.gameokkmatematica.domain.Operacao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

        final var resultadoCorreto = Operacao
            .of(desafio.getOperacao())
            .execute(desafio.getFatorA(), desafio.getFatorB());

        Assertions.assertThat(desafio).isNotNull();
        Assertions.assertThat(desafio.getAlternativas()).hasSize(4);
        Assertions.assertThat(desafio.getAlternativas()).contains(resultadoCorreto);
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    @DisplayName("Deve enviar uma tentativa de resposta")
    void deveEnviarUmaTentativaDeResposta(final boolean tentivaCorreta) {
        final var request = new DesafioTentativaRespostaRequest(100, 100,
            tentivaCorreta ? 200 : 2000,
            Operacao.ADICAO.getOperador(), "usuario-mock");

        final var desafioTentativaResposta = desafioService.tentativaResposta(request);

        Assertions.assertThat(desafioTentativaResposta).isNotNull();
        Assertions.assertThat(desafioTentativaResposta.isCorreta()).isEqualTo(tentivaCorreta);
        Assertions.assertThat(desafioTentativaResposta.getFatorA()).isEqualTo(request.getFatorA());
        Assertions.assertThat(desafioTentativaResposta.getFatorB()).isEqualTo(request.getFatorB());
        Assertions.assertThat(desafioTentativaResposta.getOperacao()).isEqualTo(request.getOperacao());
    }

}

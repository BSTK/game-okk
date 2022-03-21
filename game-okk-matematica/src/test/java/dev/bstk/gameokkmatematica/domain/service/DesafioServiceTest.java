package dev.bstk.gameokkmatematica.domain.service;

import dev.bstk.gameokk.core.TesteHelper;
import dev.bstk.gameokkmatematica.api.request.DesafioTentativaRespostaRequest;
import dev.bstk.gameokkmatematica.domain.DesafioRepository;
import dev.bstk.gameokkmatematica.domain.DesafioService;
import dev.bstk.gameokkmatematica.domain.model.DesafioTentativaResposta;
import dev.bstk.gameokkmatematica.domain.model.Operacao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DesafioServiceTest {

    @InjectMocks
    private DesafioService desafioService;

    @Mock
    private DesafioRepository desafioRepository;

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
        final var request = new DesafioTentativaRespostaRequest(10, 10,
            tentivaCorreta ? 20 : 5,
            Operacao.ADICAO.getOperador());

        final var desafioTentativaResposta = tentivaCorreta ? "correta" : "incorreta";
        final var desafioTentativaRespostaSalvo = TesteHelper.fixure(
            String.format("/fixture/desafios/desafio-tentativa-resposta-%s.json", desafioTentativaResposta),
            DesafioTentativaResposta.class);

        when(desafioRepository.save(any(DesafioTentativaResposta.class))).thenReturn(desafioTentativaRespostaSalvo);

        final var desafioTentativaRespostaSalva = desafioService.tentativaResposta(request);

        Assertions.assertThat(desafioTentativaResposta).isNotNull();
        Assertions.assertThat(desafioTentativaRespostaSalva.isCorreta()).isEqualTo(tentivaCorreta);
        Assertions.assertThat(desafioTentativaRespostaSalva.getFatorA()).isEqualTo(request.getFatorA());
        Assertions.assertThat(desafioTentativaRespostaSalva.getFatorB()).isEqualTo(request.getFatorB());
        Assertions.assertThat(desafioTentativaRespostaSalva.getOperacao()).isEqualTo(request.getOperacao());
    }
}

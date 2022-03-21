package dev.bstk.gameokkmatematica.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.gameokk.core.Mapper;
import dev.bstk.gameokkmatematica.api.request.DesafioTentativaRespostaRequest;
import dev.bstk.gameokkmatematica.api.response.DesafioTentativaRespostaResponse;
import dev.bstk.gameokkmatematica.domain.model.Desafio;
import dev.bstk.gameokkmatematica.domain.model.DesafioTentativaResposta;
import dev.bstk.gameokkmatematica.domain.model.Operacao;
import dev.bstk.gameokkmatematica.domain.DesafioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DesafioController.class)
class DesafioControllerTest {

    private static final String ENDPOINT_API_V1_DESAFIOS = "/api/v1/matematica/desafios";
    private static final String ENDPOINT_API_V1_TENTATIVA_RESPOSTA = "/api/v1/matematica/verificar-resposta";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DesafioService desafioService;


    @Test
    @DisplayName("Deve retornar um resafio aleatorio")
    void deveRetornarUmResafioAleatorio() throws Exception {
        final var desafio = new Desafio(10, 10, new int[] {1, 2, 3, 20}, "+");
        when(desafioService.gerarDesafioAleatorio()).thenReturn(desafio);

        mockMvc.perform(
                get(ENDPOINT_API_V1_DESAFIOS))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.fatorA").value(desafio.getFatorA()))
            .andExpect(jsonPath("$.fatorB").value(desafio.getFatorB()))
            .andExpect(jsonPath("$.alternativas").isArray())
            .andExpect(jsonPath("$.alternativas.length()")
                .value(desafio.getAlternativas().length))
            .andExpect(jsonPath("$.operacao").value(desafio.getOperacao()));
    }

    @Test
    @DisplayName("Deve postar uma tentativa com resultado correto")
    void devePostarUmaTentativaComResultadoCorreto() throws Exception {
        final Long tentativaId = 1L;

        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            50, 50, 100, Operacao.ADICAO.getOperador());

        final DesafioTentativaResposta respostaEsperada = new DesafioTentativaResposta(
            tentativaId, 50, 50, 100, true, Operacao.ADICAO.getOperador());

        final DesafioTentativaRespostaResponse respostaEsperadaResponse =
            Mapper.to(respostaEsperada, DesafioTentativaRespostaResponse.class);

        given(desafioService
            .tentativaResposta(request))
            .willReturn(respostaEsperada);

        this.mockMvc.perform(
                post(ENDPOINT_API_V1_TENTATIVA_RESPOSTA)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.fatorA").value(respostaEsperadaResponse.getFatorA()))
            .andExpect(jsonPath("$.fatorB").value(respostaEsperadaResponse.getFatorB()))
            .andExpect(jsonPath("$.resultado").value(respostaEsperadaResponse.getResultado()))
            .andExpect(jsonPath("$.correta").value(respostaEsperadaResponse.isCorreta()))
            .andExpect(jsonPath("$.operacao").value(respostaEsperadaResponse.getOperacao()));
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Operação Inválida)")
    void devePostarUmaTentativaComRequestInvalido_OperacaoInvalida() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            50, 50, 100, "#");

        this.mockMvc.perform(
                post(ENDPOINT_API_V1_TENTATIVA_RESPOSTA)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorA negativo)")
    void devePostarUmaTentativaComRequestInvalido_FatorANegativo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            -1, 50, 100, Operacao.SUBTRACAO.getOperador());

        this.mockMvc.perform(
                post(ENDPOINT_API_V1_TENTATIVA_RESPOSTA)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorA maior que 99)")
    void devePostarUmaTentativaComRequestInvalido_FatorAMaiorQue99() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            999, 50, 100, Operacao.SUBTRACAO.getOperador());

        this.mockMvc.perform(
                post(ENDPOINT_API_V1_TENTATIVA_RESPOSTA)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorB negativo)")
    void devePostarUmaTentativaComRequestInvalido_FatorBNegativo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, -1, 100, Operacao.SUBTRACAO.getOperador());

        this.mockMvc.perform(
                post(ENDPOINT_API_V1_TENTATIVA_RESPOSTA)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorB maior que 99)")
    void devePostarUmaTentativaComRequestInvalido_FatorBMaiorQue99() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 999, 100, Operacao.SUBTRACAO.getOperador());

        this.mockMvc.perform(
                post(ENDPOINT_API_V1_TENTATIVA_RESPOSTA)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Resposta negativo)")
    void devePostarUmaTentativaComRequestInvalido_RespostaNegativo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 999, -1, Operacao.SUBTRACAO.getOperador());

        this.mockMvc.perform(
                post(ENDPOINT_API_V1_TENTATIVA_RESPOSTA)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }
}

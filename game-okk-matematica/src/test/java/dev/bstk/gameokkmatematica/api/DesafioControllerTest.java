package dev.bstk.gameokkmatematica.api;

import dev.bstk.gameokkmatematica.domain.Desafio;
import dev.bstk.gameokkmatematica.domain.service.DesafioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DesafioController.class)
class DesafioControllerTest {

    private static final String ENDPOINT_API_V1_DESAFIOS = "/api/v1/matematica/desafios";

    @Autowired
    private MockMvc mockMvc;

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
}

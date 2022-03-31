package dev.bstk.gameokkjogoforca.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.gameokk.core.TesteHelper;
import dev.bstk.gameokkjogoforca.domain.model.Partida;
import dev.bstk.gameokkjogoforca.domain.service.ForcaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ForcaController.class)
class ForcaControllerTest {

    private static final String ENDPOINT_API_V1_NOVA_PARTIDA = "/api/v1/forca/nova-partida";
    private static final String ENDPOINT_API_V1_JOGAR = "/api/v1/forca/jogar/{partidaId}/{letra}";
    private static final String ENDPOINT_API_V1_PARTIDA_POR_ID = "/api/v1/forca/partida/{partidaId}";

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForcaService forcaService;


    @Test
    @DisplayName("Deve retornar uma partida por id valida")
    void deveRetornarUmaPartidaPorIdValida() throws Exception {
        final var partidaPorId = TesteHelper.fixure("/fixture/forca/partida-em-andamento.json", Partida.class);
        when(forcaService.partida(any(UUID.class))).thenReturn(partidaPorId);

        mockMvc.perform(
                get(ENDPOINT_API_V1_PARTIDA_POR_ID, UUID.randomUUID()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.alfabeto").value(partidaPorId.getAlfabeto()))
            .andExpect(jsonPath("$.palavraSecreta").value(partidaPorId.getPalavraSecreta()))
            .andExpect(jsonPath("$.letrasCorretas").value(partidaPorId.getLetrasCorretas()))
            .andExpect(jsonPath("$.letrasIncorretas").value(partidaPorId.getLetrasIncorretas()));
    }

    @Test
    @DisplayName("Deve criar uma nova partida")
    void deveCriarUmaNovaPartida() throws Exception {
        final var novaPartida = TesteHelper.fixure("/fixture/forca/nova-partida.json", Partida.class);
        when(forcaService.novaPartida()).thenReturn(novaPartida);

        mockMvc.perform(
                post(ENDPOINT_API_V1_NOVA_PARTIDA)
                    .content(OBJECT_MAPPER.writeValueAsString(novaPartida))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Deve efetuar uma jogada")
    void deveEfetuarUmaJogada() throws Exception {
        final var partidaresultadoJogada = TesteHelper.fixure("/fixture/forca/partida-em-andamento.json", Partida.class);
        when(forcaService.jogar(anyString(), anyLong())).thenReturn(partidaresultadoJogada);

        mockMvc.perform(
                post(ENDPOINT_API_V1_JOGAR, 1L, "A")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }
}

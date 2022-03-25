package dev.bstk.gameokkjogoforca.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.gameokk.core.TesteHelper;
import dev.bstk.gameokkjogoforca.domain.ForcaService;
import dev.bstk.gameokkjogoforca.domain.model.Partida;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ForcaController.class)
class ForcaControllerTest {

    private static final String ENDPOINT_API_V1_NOVA_PARTIDA = "/api/v1/forca/nova-partida";
    private static final String ENDPOINT_API_V1_JOGAR = "/api/v1/forca/jogar/{partidaId}/{letra}";
    private static final String ENDPOINT_API_V1_PARTIDA_POR_ID = "/api/v1/forca/partida/{partidaId}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ForcaService forcaService;


    @Test
    @DisplayName("Deve retornar uma partida valida por id")
    void deveRetornarUmaPartidaValidaPorId() throws Exception {
        final var partidaPorId = TesteHelper.fixure("/fixture/forca/partida-em-andamento.json", Partida.class);
        when(forcaService.partida(anyLong())).thenReturn(partidaPorId);

        mockMvc.perform(
                get(ENDPOINT_API_V1_PARTIDA_POR_ID, 1L))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.alfabeto").value(partidaPorId.getAlfabeto()))
            .andExpect(jsonPath("$.palavraSecreta").value(partidaPorId.getPalavraSecreta()))
            .andExpect(jsonPath("$.letrasCorretas").value(partidaPorId.getLetrasCorretas()))
            .andExpect(jsonPath("$.letrasIncorretas").value(partidaPorId.getLetrasIncorretas()))
            .andExpect(jsonPath("$.dicas").value(partidaPorId.getDicas()));
    }
}

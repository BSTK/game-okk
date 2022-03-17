package dev.bstk.gameokk.plataforma.jogos.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.gameokk.core.TesteHelper;
import dev.bstk.gameokk.plataforma.jogos.domain.Jogo;
import dev.bstk.gameokk.plataforma.jogos.domain.JogoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JogoController.class)
@ExtendWith(SpringExtension.class)
class JogoControllerTest {

    private static final String URL_API_V1_USUARIOS = "/api/v1/jogos";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JogoService jogoService;

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    @DisplayName("Deve retonar uma lista de jogos")
    void deveRetornarUmaListaDeJogos() throws Exception {
        final var jogos = TesteHelper.fixure("/fixture/jogos/jogos.json", Jogo[].class);

        when(jogoService.jogos())
            .thenReturn(Arrays.asList(jogos));

        mockMvc.perform(
                get(URL_API_V1_USUARIOS)
                    .content(OBJECT_MAPPER.writeValueAsString(jogos))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(jogos.length))
            .andExpect(status().isOk());
    }

}

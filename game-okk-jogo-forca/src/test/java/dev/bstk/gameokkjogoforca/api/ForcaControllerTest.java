package dev.bstk.gameokkjogoforca.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.gameokkjogoforca.domain.ForcaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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
    void deveRetornarUmaPartidaValidaPorId() {

    }
}

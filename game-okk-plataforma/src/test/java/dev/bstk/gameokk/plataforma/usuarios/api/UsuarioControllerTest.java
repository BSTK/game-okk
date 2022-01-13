package dev.bstk.gameokk.plataforma.usuarios.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    private static final String URL_API_V1_USUARIOS = "/api/v1/usuarios";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve cadastrar um novo usuário")
    void deveCadastrarUmNovoUsuario() throws Exception {
        mockMvc.perform(
            post(URL_API_V1_USUARIOS)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.nome").isString())
            .andExpect(jsonPath("$.nome").isNotEmpty())
            .andExpect(jsonPath("$.apelido").isString())
            .andExpect(jsonPath("$.apelido").isNotEmpty())
            .andExpect(jsonPath("$.email").isString())
            .andExpect(jsonPath("$.email").isNotEmpty())
            .andExpect(jsonPath("$.urlAvatar").isString())
            .andExpect(jsonPath("$.urlAvatar").isNotEmpty())
            .andExpect(status().isOk());
    }
}

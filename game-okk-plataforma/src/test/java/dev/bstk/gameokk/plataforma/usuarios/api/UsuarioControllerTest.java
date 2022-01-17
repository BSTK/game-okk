package dev.bstk.gameokk.plataforma.usuarios.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.gameokk.plataforma.usuarios.domain.Usuario;
import dev.bstk.gameokk.plataforma.usuarios.domain.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    private static final String URL_API_V1_USUARIOS = "/api/v1/usuarios";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    @DisplayName("Deve cadastrar um novo usuário")
    void deveCadastrarUmNovoUsuario() throws Exception {
        final var usuarioA = new Usuario(
            "Usuario-A",
            "usuario-a",
            "usuario.a@gmail.com",
            "https://cdn.iconscout.com/icon/free/png-256/avatar-373-456325.png");

        when(usuarioService.cadastraNovoUsuario(any(Usuario.class)))
            .thenReturn(usuarioA);

        mockMvc.perform(
            post(URL_API_V1_USUARIOS)
                .content(OBJECT_MAPPER.writeValueAsString(usuarioA))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Deve atualizar dados de um usuário")
    void deveAtualizarDadosDeUmUsuario() throws Exception {
        mockMvc.perform(
            put(URL_API_V1_USUARIOS)
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

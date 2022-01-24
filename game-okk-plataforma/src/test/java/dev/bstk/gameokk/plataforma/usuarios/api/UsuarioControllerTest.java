package dev.bstk.gameokk.plataforma.usuarios.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.gameokk.core.TesteHelper;
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

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        final var usuarioAA = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);

        when(usuarioService.cadastraNovoUsuario(any(Usuario.class)))
            .thenReturn(usuarioAA);

        mockMvc.perform(
            post(URL_API_V1_USUARIOS)
                .content(OBJECT_MAPPER.writeValueAsString(usuarioAA))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Deve retonar uma lista de usuarios")
    void deveRetornarUmaListaDeUsuarios() throws Exception {
        final var usuariosCadastrados = TesteHelper.fixure("/fixture/usuarios/lista-usuarios-cadastrados.json", Usuario[].class);

        when(usuarioService.usuarios())
            .thenReturn(Arrays.asList(usuariosCadastrados));

        mockMvc.perform(
                get(URL_API_V1_USUARIOS)
                    .content(OBJECT_MAPPER.writeValueAsString(usuariosCadastrados))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(usuariosCadastrados.length))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve atualizar dados de um usuário")
    void deveAtualizarDadosDeUmUsuario() throws Exception {
        final var usuarioAA = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);

        when(usuarioService.atualizarUsuario(any(Usuario.class)))
            .thenReturn(usuarioAA);

        mockMvc.perform(
            put(URL_API_V1_USUARIOS)
            .content(OBJECT_MAPPER.writeValueAsString(usuarioAA))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}

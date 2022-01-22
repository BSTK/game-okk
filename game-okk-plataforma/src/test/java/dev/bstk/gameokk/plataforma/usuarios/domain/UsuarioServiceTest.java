package dev.bstk.gameokk.plataforma.usuarios.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {


    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve cadastrar um novo usuario")
    void deveCadastrarUmNovoUsuario() {
        final var usuario = new Usuario(
            "Usuario-A",
            "usuarioA",
            "usuarioa.se@gmail.com",
            "https://via.placeholder.com/300.png");

        final var usuarioCadastrado = usuarioService.cadastraNovoUsuario(usuario);

        Assertions
            .assertThat(usuarioCadastrado)
            .isNotNull()
            .isEqualTo(usuario);
    }
}

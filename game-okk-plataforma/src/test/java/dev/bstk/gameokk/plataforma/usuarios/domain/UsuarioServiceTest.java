package dev.bstk.gameokk.plataforma.usuarios.domain;

import dev.bstk.gameokk.core.TesteHelper;
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
        final var usuario = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);
        final var usuarioCadastrado = usuarioService.cadastraNovoUsuario(usuario);

        Assertions
            .assertThat(usuarioCadastrado)
            .isNotNull()
            .isEqualTo(usuario);
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar um novo usuario com email ja cadastrado")
    void deveLancarExcecaoAoCadastrarUmNovoUsuarioComEmailJaCadastrado() {

    }

}

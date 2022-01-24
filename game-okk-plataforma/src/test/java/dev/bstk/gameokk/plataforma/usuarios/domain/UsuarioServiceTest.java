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
        final var usuarioAA = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);
        final var usuarioBB = TesteHelper.fixure("/fixture/usuarios/novo-usuario-B.json", Usuario.class);
        final var usuarioCC = TesteHelper.fixure("/fixture/usuarios/novo-usuario-C.json", Usuario.class);

        usuarioService.cadastraNovoUsuario(usuarioAA);
        usuarioService.cadastraNovoUsuario(usuarioBB);
        usuarioService.cadastraNovoUsuario(usuarioCC);

        final var usuarioAAComEmailrepetido = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);

        Assertions
            .assertThatThrownBy(() -> usuarioService.cadastraNovoUsuario(usuarioAAComEmailrepetido))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Usuário ja castrado para o email: " + usuarioAA.email());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar um novo usuario com apelido já existente")
    void deveLancarExcecaoAoCadastrarUmNovoUsuarioComApelidoJaExistente() {
        final var usuarioAA = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);
        final var usuarioBB = TesteHelper.fixure("/fixture/usuarios/novo-usuario-B.json", Usuario.class);
        final var usuarioCC = TesteHelper.fixure("/fixture/usuarios/novo-usuario-C.json", Usuario.class);

        usuarioService.cadastraNovoUsuario(usuarioAA);
        usuarioService.cadastraNovoUsuario(usuarioBB);
        usuarioService.cadastraNovoUsuario(usuarioCC);

        final var usuarioAAComApelidorepetido = new Usuario(
            "usuario-DD",
            usuarioAA.apelido(),
            "usuario-dd@gmail.com",
            usuarioAA.urlAvatar()
        );

        Assertions
            .assertThatThrownBy(() -> usuarioService.cadastraNovoUsuario(usuarioAAComApelidorepetido))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Apelido já existe: " + usuarioAA.apelido());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar um novo usuario passando um usuário nulo")
    void deveLancarExcecaoAoCadastrarUmNovoUsuarioPassandoUmUsuarioNulo() {
        Assertions
            .assertThatThrownBy(() -> usuarioService.cadastraNovoUsuario(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Dados do usuário não podem ser nulos!");
    }

}

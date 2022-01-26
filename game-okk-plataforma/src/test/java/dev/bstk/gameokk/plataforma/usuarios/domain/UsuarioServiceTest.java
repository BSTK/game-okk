package dev.bstk.gameokk.plataforma.usuarios.domain;

import dev.bstk.gameokk.core.TesteHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {


    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

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
    @DisplayName("Deve retonar uma lista de usuarios")
    void deveRetornarUmaListaDeUsuarios() {
        final var usuariosCadastrados = TesteHelper.fixure("/fixture/usuarios/lista-usuarios-cadastrados.json", Usuario[].class);

        when(usuarioRepository.findAll())
            .thenReturn(Arrays.asList(usuariosCadastrados));

        final var usuarios = usuarioService.usuarios();

        Assertions
            .assertThat(usuarios)
            .isNotNull()
            .isNotEmpty()
            .hasSize(usuariosCadastrados.length)
            .containsExactly(usuariosCadastrados);
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar um novo usuario com email ja cadastrado")
    void deveLancarExcecaoAoCadastrarUmNovoUsuarioComEmailJaCadastrado() {
        final var usuarioAAComEmailrepetido = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);

        when(usuarioRepository.existeUsuarioComEmailJaCadastrado(anyString()))
            .thenReturn(Boolean.TRUE);

        Assertions
            .assertThatThrownBy(() -> usuarioService.cadastraNovoUsuario(usuarioAAComEmailrepetido))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Usuário ja castrado para o email: " + usuarioAAComEmailrepetido.getEmail());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar um novo usuario com apelido já existente")
    void deveLancarExcecaoAoCadastrarUmNovoUsuarioComApelidoJaExistente() {
        final var usuarioAAComApelidorepetido = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);

        when(usuarioRepository.existeUsuarioComApelidoJaCadastrado(anyString()))
            .thenReturn(Boolean.TRUE);

        Assertions
            .assertThatThrownBy(() -> usuarioService.cadastraNovoUsuario(usuarioAAComApelidorepetido))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Apelido já existe: " + usuarioAAComApelidorepetido.getApelido());
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

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
    @DisplayName("Deve retonar um usuario por apelido")
    void deveRetornarUmUsuarioPorApelido() {
        final var usuario = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);

        when(usuarioRepository.usuarioPorApelido(anyString()))
            .thenReturn(usuario);

        final var usuarioPorApelido = usuarioService.usuarioPorApelido("usuario-A");

        Assertions
            .assertThat(usuarioPorApelido)
            .isNotNull()
            .isSameAs(usuario);

        Assertions
            .assertThat(usuarioPorApelido.getApelido())
            .isNotNull()
            .isNotEmpty()
            .isNotBlank()
            .isEqualTo(usuario.getApelido());
    }

    @Test
    @DisplayName("Deve lan??ar exce????o ao cadastrar um novo usuario com email ja cadastrado")
    void deveLancarExcecaoAoCadastrarUmNovoUsuarioComEmailJaCadastrado() {
        final var usuarioAAComEmailrepetido = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);

        when(usuarioRepository.existeUsuarioComEmailJaCadastrado(anyString()))
            .thenReturn(Boolean.TRUE);

        Assertions
            .assertThatThrownBy(() -> usuarioService.cadastraNovoUsuario(usuarioAAComEmailrepetido))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Usu??rio ja castrado para o email: " + usuarioAAComEmailrepetido.getEmail());
    }

    @Test
    @DisplayName("Deve lan??ar exce????o ao cadastrar um novo usuario com apelido j?? existente")
    void deveLancarExcecaoAoCadastrarUmNovoUsuarioComApelidoJaExistente() {
        final var usuarioAAComApelidorepetido = TesteHelper.fixure("/fixture/usuarios/novo-usuario.json", Usuario.class);

        when(usuarioRepository.existeUsuarioComApelidoJaCadastrado(anyString()))
            .thenReturn(Boolean.TRUE);

        Assertions
            .assertThatThrownBy(() -> usuarioService.cadastraNovoUsuario(usuarioAAComApelidorepetido))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Apelido j?? existe: " + usuarioAAComApelidorepetido.getApelido());
    }

    @Test
    @DisplayName("Deve lan??ar exce????o ao cadastrar um novo usuario passando um usu??rio nulo")
    void deveLancarExcecaoAoCadastrarUmNovoUsuarioPassandoUmUsuarioNulo() {
        Assertions
            .assertThatThrownBy(() -> usuarioService.cadastraNovoUsuario(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Dados do usu??rio n??o podem ser nulos!");
    }

}

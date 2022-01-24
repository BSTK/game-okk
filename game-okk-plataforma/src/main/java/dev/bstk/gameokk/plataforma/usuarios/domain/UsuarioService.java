package dev.bstk.gameokk.plataforma.usuarios.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario cadastraNovoUsuario(final Usuario usuario) {
        validarDadosUsuario(usuario);
        usuarioRepository.save(usuario);

        return usuario;
    }

    public Usuario atualizarUsuario(final Usuario usuario) {
        validarDadosUsuario(usuario);
        usuarioRepository.save(usuario);

        return usuario;
    }

    public List<Usuario> usuarios() {
        return usuarioRepository.findAll();
    }

    private void validarDadosUsuario(final Usuario usuario) {
        Objects.requireNonNull(usuario, "Dados do usuário não podem ser nulos!");

        final boolean existeUsuarioComEmailJaCadastrado = usuarioRepository.existeUsuarioComEmailJaCadastrado(usuario.email());
        if (existeUsuarioComEmailJaCadastrado) {
            throw new IllegalArgumentException("Usuário ja castrado para o email: " + usuario.email());
        }

        final boolean existeUsuarioComApelidoJaCadastrado = usuarioRepository.existeUsuarioComApelidoJaCadastrado(usuario.apelido());
        if (existeUsuarioComApelidoJaCadastrado) {
            throw new IllegalArgumentException("Apelido já existe: " + usuario.apelido());
        }
    }
}

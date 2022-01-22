package dev.bstk.gameokk.plataforma.usuarios.domain;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioService {

    private static final Map<String, Usuario> USUARIOS = new HashMap<>();

    public Usuario cadastraNovoUsuario(final Usuario usuario) {
        validarDadosUsuario(usuario);

        USUARIOS.put(usuario.email(), usuario);

        return usuario;
    }

    public Usuario atualizarUsuario(final Usuario usuario) {
        validarDadosUsuario(usuario);

        USUARIOS.remove(usuario.email());
        USUARIOS.put(usuario.email(), usuario);

        return usuario;
    }

    private void validarDadosUsuario(final Usuario usuario) {
        Objects.requireNonNull(usuario, "Dados do usuário não podem ser nulos!");

        for (Map.Entry<String, Usuario> usuarioEntry : USUARIOS.entrySet()) {
            if (usuarioEntry.getKey().equalsIgnoreCase(usuario.email())) {
                throw new IllegalArgumentException("Usuário ja castrado para o email: " + usuario.email());
            }

            if (usuarioEntry.getValue().apelido().equalsIgnoreCase(usuario.apelido())) {
                throw new IllegalArgumentException("Apelido já existe: " + usuario.apelido());
            }
        }
    }
}

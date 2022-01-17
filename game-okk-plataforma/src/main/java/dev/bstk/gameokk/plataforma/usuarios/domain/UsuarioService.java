package dev.bstk.gameokk.plataforma.usuarios.domain;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioService {

    private static final Map<String, Usuario> USUARIOS = new HashMap<>();

    public Usuario cadastraNovoUsuario(final Usuario usuario) {
        Objects.requireNonNull(usuario, "Dados do usuário não podem ser nulos!");
        USUARIOS.put(usuario.email(), usuario);

        return usuario;
    }
}

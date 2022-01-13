package dev.bstk.gameokk.plataforma.usuarios.domain;

import dev.bstk.gameokk.plataforma.usuarios.api.UsuarioRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioService {

    private static final Map<String, Usuario> USUARIOS = new HashMap<>();

    public Usuario cadastraNovoUsuario(final UsuarioRequest request) {
        return new Usuario(
            "Usuario A",
            "usuario-a",
            "usuario-a@gmail.com",
            "https://icon-library.com/images/22224-tiger-icon_5825.png");
    }
}

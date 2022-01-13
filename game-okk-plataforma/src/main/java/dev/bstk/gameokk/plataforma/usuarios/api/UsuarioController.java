package dev.bstk.gameokk.plataforma.usuarios.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastraNovoUsuario() {
        final var usuarioA = new UsuarioResponse(
            "Usuario A",
            "usuario-a",
            "usuario-a@gmail.com",
            "https://icon-library.com/images/22224-tiger-icon_5825.png");

        log.info("Cadastrando novo usuário: {}", usuarioA);

        return ResponseEntity.ok(usuarioA);
    }

    @PutMapping
    public ResponseEntity<UsuarioResponse> atualizarUsuario() {
        final var usuarioA = new UsuarioResponse(
            "Usuario A",
            "usuario-a",
            "usuario-a@gmail.com",
            "https://icon-library.com/images/22224-tiger-icon_5825.png");

        log.info("Atualizando usuário: {}", usuarioA);

        return ResponseEntity.ok(usuarioA);
    }

}

package dev.bstk.gameokk.plataforma.usuarios.api;

import dev.bstk.gameokk.core.Mapper;
import dev.bstk.gameokk.plataforma.usuarios.domain.Usuario;
import dev.bstk.gameokk.plataforma.usuarios.domain.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> cadastraNovoUsuario(@RequestBody @Valid final UsuarioRequest request) {
        final var novoUsuario = Mapper.to(request, Usuario.class);
        final var novoUsuarioCadastrado = usuarioService.cadastraNovoUsuario(novoUsuario);
        final var novoUsuarioCadastradoResponse = Mapper.to(novoUsuarioCadastrado, UsuarioResponse.class);

        log.info("Cadastrando novo usuário: {}", novoUsuarioCadastradoResponse);

        final var uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(novoUsuarioCadastradoResponse.apelido())
            .toUri();

        return ResponseEntity.created(uri).build();
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

package dev.bstk.gameokk.plataforma.usuarios.api;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UsuarioRequest(
    @NotNull
    @NotBlank
    String nome,

    @NotNull
    @NotBlank
    String apelido,

    @Email
    @NotNull
    @NotBlank
    String email,

    @URL
    @NotNull
    @NotBlank
    String urlAvatar) {
}

package dev.bstk.gameokk.plataforma.usuarios.api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String apelido;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @URL
    @NotNull
    @NotBlank
    private String urlAvatar;
}

package dev.bstk.gameokk.plataforma.jogos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {

    @NotNull
    @NotBlank
    private String contexto;

    @NotNull
    @NotBlank
    private String titulo;

    @NotNull
    @NotBlank
    private String descricao;

    @NotNull
    @NotBlank
    private String urlIcone;
}

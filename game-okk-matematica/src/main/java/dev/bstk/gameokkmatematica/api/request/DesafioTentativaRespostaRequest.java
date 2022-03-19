package dev.bstk.gameokkmatematica.api.request;

import dev.bstk.gameokkmatematica.domain.validation.Contains;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesafioTentativaRespostaRequest {

    @Min(1)
    @Max(99)
    int fatorA;

    @Min(1)
    @Max(99)
    int fatorB;

    @NotNull
    int resposta;

    @Contains(range = { "+", "-", "*", "/" })
    String operacao;
}

package dev.bstk.gameokkjogoforca.domain.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class PalavraSecreta implements Serializable {

    @NotNull
    @NotEmpty
    private List<String> palavra;

    @NotNull
    @NotEmpty
    private List<Dica> dicas;
}

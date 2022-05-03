package dev.bstk.gameokkjogomemoria.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Carta implements Serializable {

    private final Integer numero;
    private final String imagem;
    private final Status status;

    public enum Status {
        VISIVEL,
        INVISIVEL
    }
}

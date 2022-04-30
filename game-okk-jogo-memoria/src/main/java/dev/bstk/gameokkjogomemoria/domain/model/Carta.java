package dev.bstk.gameokkjogomemoria.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Carta implements Serializable {

    private Integer numero;
    private String imagem;
    private Status status;

    public enum Status {
        VISIVEL,
        INVISIVEL
    }
}

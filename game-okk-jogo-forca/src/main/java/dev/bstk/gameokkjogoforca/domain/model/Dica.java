package dev.bstk.gameokkjogoforca.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dica implements Serializable {

    private int numero;
    private String descircao;
    private boolean visivel;
}

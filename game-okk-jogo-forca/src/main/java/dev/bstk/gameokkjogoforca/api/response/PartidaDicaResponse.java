package dev.bstk.gameokkjogoforca.api.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class PartidaDicaResponse implements Serializable {
    int numero;
    String descircao;
    boolean visivel;
}

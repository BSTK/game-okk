package dev.bstk.gameokkjogomemoria.api.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartaResponse implements Serializable {

    private Integer numero;
    private String imagem;
    private String status;
}

package dev.bstk.gameokkjogomemoria.api.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class NivelResponse implements Serializable {

    private String nivel;
    private String descricao;
    private Integer quantidadePares;
}

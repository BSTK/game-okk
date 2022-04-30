package dev.bstk.gameokkjogomemoria.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TabuleiroResponse implements Serializable {

    private NivelResponse nivel;
    private List<CartaResponse> cartas;
}

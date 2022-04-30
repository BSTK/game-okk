package dev.bstk.gameokkjogomemoria.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class PartidaResponse implements Serializable {

    private UUID uuid;
    private Date dataInicio;
    private Date dataFim;
    private String status;
    private TabuleiroResponse tabuleiro;
}

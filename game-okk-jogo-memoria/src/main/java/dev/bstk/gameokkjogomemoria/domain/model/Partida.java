package dev.bstk.gameokkjogomemoria.domain.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class Partida implements Serializable {

    private Long id;
    private UUID uuid;
    private Date dataInsert;
    private Date dataUpdate;
    private Date dataInicio;
    private Date dataFim;
    private Status status;
    private Tabuleiro tabuleiro;
}

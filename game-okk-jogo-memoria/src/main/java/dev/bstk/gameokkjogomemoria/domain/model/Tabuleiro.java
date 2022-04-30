package dev.bstk.gameokkjogomemoria.domain.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Tabuleiro implements Serializable {

    private Nivel nivel;
    private List<Carta> cartas;
}

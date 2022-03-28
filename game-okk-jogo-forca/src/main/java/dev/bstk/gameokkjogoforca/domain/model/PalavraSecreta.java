package dev.bstk.gameokkjogoforca.domain.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PalavraSecreta implements Serializable {

    private List<String> palavra;
    private List<Dica> dicas;
}

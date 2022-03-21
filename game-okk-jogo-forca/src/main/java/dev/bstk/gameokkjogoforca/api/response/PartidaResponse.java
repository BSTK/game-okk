package dev.bstk.gameokkjogoforca.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PartidaResponse implements Serializable {
    char[] alfabeto;
    char[] palavraSecreta;
    char[] letrarCorretas;
    List<PartidaDicaResponse> dicas;
}

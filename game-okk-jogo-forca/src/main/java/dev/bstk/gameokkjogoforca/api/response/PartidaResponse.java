package dev.bstk.gameokkjogoforca.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PartidaResponse implements Serializable {
    private List<String> alfabeto;
    private List<String> palavraSecreta;
    private List<String> letrasCorretas;
    private List<String> letrasIncorretas;
    private List<PartidaDicaResponse> dicas;
}

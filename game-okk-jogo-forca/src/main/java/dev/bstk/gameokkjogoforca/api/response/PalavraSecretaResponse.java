package dev.bstk.gameokkjogoforca.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PalavraSecretaResponse implements Serializable {

    private List<String> palavra;
    private List<DicaResponse> dicas;
}

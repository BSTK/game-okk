package dev.bstk.gameokkjogoforca.api.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class PartidaLetraInformadaResponse implements Serializable {
    char letra;
    boolean acertou;
}

package dev.bstk.gameokkmatematica.api.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class DesafioResponse implements Serializable {
    int fatorA;
    int fatorB;
    int[] alternativas;
    String operacao;
}

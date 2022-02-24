package dev.bstk.gameokkmatematica.api;

import lombok.Data;

@Data
public class DesafioResponse {

    int fatorA;
    int fatorB;
    int[] alternativas;
    String operacao;

}

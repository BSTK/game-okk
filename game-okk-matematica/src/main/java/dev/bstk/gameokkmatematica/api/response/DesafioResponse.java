package dev.bstk.gameokkmatematica.api.response;

import lombok.Data;

@Data
public class DesafioResponse {

    int fatorA;
    int fatorB;
    int[] alternativas;
    String operacao;

}

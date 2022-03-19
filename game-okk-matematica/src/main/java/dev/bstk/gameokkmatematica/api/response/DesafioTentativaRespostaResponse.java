package dev.bstk.gameokkmatematica.api.response;

import lombok.Data;

@Data
public class DesafioTentativaRespostaResponse {
    private int fatorA;
    private int fatorB;
    private int resultado;
    private boolean correta;
    private String operacao;
}

package dev.bstk.gameokkjogomemoria.domain.model;

public enum Nivel {

    FACIO("Nível Fácil"),
    MEDIO("Nível Médio"),
    DIFICIL("Nível Difícil"),
    MUITO_DIFICIO("Nível Muito Difícil");

    private final String descricao;

    Nivel(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

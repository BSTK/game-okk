package dev.bstk.gameokkjogoforca.domain.model;

public enum PartidaSatus {

    EM_ANDAMENTO("Em Andamento"),
    FINALIZADA("Finalizada"),
    CANCELADA("Cancelada");

    private final String descricao;

    PartidaSatus(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

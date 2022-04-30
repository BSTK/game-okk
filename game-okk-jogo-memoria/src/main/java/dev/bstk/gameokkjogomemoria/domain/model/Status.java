package dev.bstk.gameokkjogomemoria.domain.model;

public enum Status {

    EM_ANDAMENTO("Em Andamento"),
    FINALIZADA("Finalizada"),
    CANCELADA("Cancelada");

    private final String descricao;

    Status(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

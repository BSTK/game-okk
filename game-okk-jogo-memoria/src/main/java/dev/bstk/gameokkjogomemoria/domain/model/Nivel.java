package dev.bstk.gameokkjogomemoria.domain.model;

public enum Nivel {

    FACIL( "Nível Fácil", 12),
    MEDIO("Nível Médio", 16),
    DIFICIL("Nível Difícil", 18),
    MUITO_DIFICIL("Nível Muito Difícil", 20);

    private final String descricao;
    private final Integer quantidadePares;

    Nivel(final String descricao, final Integer quantidadePares) {
        this.descricao = descricao;
        this.quantidadePares = quantidadePares;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidadePares() {
        return quantidadePares;
    }

    public String getNivel() {
        return this.name();
    }
}

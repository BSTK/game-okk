package dev.bstk.gameokk.plataforma.usuarios.api;

public record UsuarioRequest(
    String nome,
    String apelido,
    String email,
    String urlAvatar) {
}

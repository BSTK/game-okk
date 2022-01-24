package dev.bstk.gameokk.plataforma.usuarios.domain;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "DESAFIO_TENTATIVA_RESPOSTA")
public record Usuario(

    @Id
    @GeneratedValue
    Long id,

    @Column(name = "NOME")
    String nome,

    @Column(name = "APELIDO")
    String apelido,

    @Email
    @Column(name = "EMAIL")
    String email,

    @URL
    @Column(name = "URL_AVATAR")
    String urlAvatar) {

    public Usuario() {
        this(null, "", "", "", "");
    }
}

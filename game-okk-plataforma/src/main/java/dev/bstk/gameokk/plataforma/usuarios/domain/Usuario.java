package dev.bstk.gameokk.plataforma.usuarios.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "USUARIO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @NotEmpty
    @Column(name = "APELIDO")
    private String apelido;

    @Email
    @NotNull
    @NotEmpty
    @Column(name = "EMAIL")
    private String email;

    @URL
    @NotNull
    @NotEmpty
    @Column(name = "URL_AVATAR")
    private String urlAvatar;

}

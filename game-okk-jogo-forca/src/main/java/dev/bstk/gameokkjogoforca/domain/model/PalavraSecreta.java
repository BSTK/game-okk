package dev.bstk.gameokkjogoforca.domain.model;

import dev.bstk.gameokkjogoforca.domain.model.converter.ListDicaConverter;
import dev.bstk.gameokkjogoforca.domain.model.converter.ListStringConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "PALAVRA_SECRETA")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PalavraSecreta implements Serializable {

    public static final String MASCARA = "▬";
    public static final Integer TOTAL_ERROS = 6;

    @Id
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "PALAVRA")
    @Convert(converter = ListStringConverter.class)
    private List<String> palavra;

    @NotNull
    @Convert(converter = ListDicaConverter.class)
    private List<Dica> dicas;
}

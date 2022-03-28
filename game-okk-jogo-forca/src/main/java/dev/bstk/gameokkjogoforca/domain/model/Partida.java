package dev.bstk.gameokkjogoforca.domain.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "PARTIDA")
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Partida implements Serializable {

    @Id
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "UUID")
    private UUID uuid;

    @NotNull
    @Column(name = "DATA_INSERT")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataInsert;

    @NotNull
    @Column(name = "DATA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataUpdate;

    @NotNull
    @NotEmpty
    @Type(type = "json")
    @Column(name = "ALFABETO")
    private List<String> alfabeto;

    @NotNull
    @NotEmpty
    @Type(type = "json")
    @Column(name = "LETRAS_CORRETAS")
    private List<String> letrasCorretas;

    @NotNull
    @NotEmpty
    @Type(type = "json")
    @Column(name = "LETRAS_INCORRETAS")
    private List<String> letrasIncorretas;

    @NotNull
    @Type(type = "json")
    @Column(name = "PALAVRA_SECRETA")
    private PalavraSecreta palavraSecreta;

    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private PartidaSatus status;

    @PrePersist
    public void dataInsert() {
        setUuid(UUID.randomUUID());
        setDataInsert(LocalDateTime.now());
        setDataUpdate(LocalDateTime.now());
    }

    @PreUpdate
    public void dataUpdate() {
        setDataUpdate(LocalDateTime.now());
    }
}

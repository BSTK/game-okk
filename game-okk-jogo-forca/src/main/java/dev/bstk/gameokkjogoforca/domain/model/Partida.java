package dev.bstk.gameokkjogoforca.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "PARTIDA")
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
    private Date dataInsert;

    @NotNull
    @Column(name = "DATA_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUpdate;

    @NotNull
    @NotEmpty
    @Column(name = "ALFABETO")
    private List<String> alfabeto;

    @NotNull
    @NotEmpty
    @Column(name = "LETRAS_CORRETAS")
    private List<String> letrasCorretas;

    @NotNull
    @NotEmpty
    @Column(name = "LETRAS_INCORRETAS")
    private List<String> letrasIncorretas;

    @NotNull
    @OneToOne
    @JoinColumn(name = "PARTIDA_ID", referencedColumnName = "ID")
    private PalavraSecreta palavraSecreta;

    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private PartidaSatus status;

    @PrePersist
    public void dataInsert() {
        setUuid(UUID.randomUUID());
        setDataInsert(Date.from(Instant.now()));
        setDataUpdate(Date.from(Instant.now()));
    }

    @PreUpdate
    public void dataUpdate() {
        setDataUpdate(Date.from(Instant.now()));
    }
}

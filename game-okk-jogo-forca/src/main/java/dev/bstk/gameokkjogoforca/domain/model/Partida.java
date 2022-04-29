package dev.bstk.gameokkjogoforca.domain.model;

import dev.bstk.gameokkjogoforca.domain.model.converter.ListStringConverter;
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

import static dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta.TOTAL_ERROS;

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
    @Convert(converter = ListStringConverter.class)
    private List<String> alfabeto;

    @NotNull
    @Column(name = "LETRAS_CORRETAS")
    @Convert(converter = ListStringConverter.class)
    private List<String> letrasCorretas;

    @NotNull
    @Column(name = "LETRAS_INCORRETAS")
    @Convert(converter = ListStringConverter.class)
    private List<String> letrasIncorretas;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "PARTIDA_ID", referencedColumnName = "ID")
    private PalavraSecreta palavraSecreta;

    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private PartidaSatus status;

    @NotNull
    @Column(name = "TOTAL_ERROS")
    private int totalErros;

    @NotNull
    @Column(name = "TERMINOU_PARTIDA_GANHOU")
    private boolean terminouPartidaGanhou;

    @NotNull
    @Column(name = "TERMINOU_PARTIDA_PERDEU")
    private boolean terminouPartidaPerdeu;

    @PrePersist
    public void dataInsert() {
        setTotalErros(0);
        setUuid(UUID.randomUUID());
        setTerminouPartidaGanhou(false);
        setTerminouPartidaPerdeu(false);
        setDataInsert(Date.from(Instant.now()));
        setDataUpdate(Date.from(Instant.now()));
    }

    @PreUpdate
    public void dataUpdate() {
        validaTerminoPartida();
        setDataUpdate(Date.from(Instant.now()));
    }

    public boolean acertouLetra(final String letra) {
        return getLetrasCorretas().contains(letra);
    }

    public boolean errouLetra(final String letra) {
        return !acertouLetra(letra);
    }

    public void incrementarTotalErros() {
        this.totalErros += 1;
    }

    public boolean inicioDePartida() {
        return getLetrasCorretas().isEmpty() && getLetrasIncorretas().isEmpty();
    }

    private void validaTerminoPartida() {
        final var perdeuPartida = getTotalErros() == TOTAL_ERROS;
        final var ganhouPartida = getPalavraSecreta()
            .getPalavra()
            .stream()
            .distinct()
            .count() == getLetrasCorretas().size();

        setTerminouPartidaPerdeu(perdeuPartida);
        setTerminouPartidaGanhou(ganhouPartida);

        if (ganhouPartida) {
            setStatus(PartidaSatus.FINALIZADA);
        }
    }
}

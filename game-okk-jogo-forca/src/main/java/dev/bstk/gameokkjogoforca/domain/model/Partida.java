package dev.bstk.gameokkjogoforca.domain.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Partida implements Serializable {

    private Long id;
    private Object uuid;
    private LocalDateTime dataInsert;
    private LocalDateTime dataUpdate;
    private List<String> alfabeto;
    private List<String> palavraSecreta;
    private List<String> letrasCorretas;
    private List<String> letrasIncorretas;
    private List<Dica> dicas;
    private PartidaSatus status;

    public void dataInsert() {
        setUuid(UUID.randomUUID());
        setDataInsert(LocalDateTime.now());
        setDataUpdate(LocalDateTime.now());
    }

    public void dataUpdate() {
        setDataUpdate(LocalDateTime.now());
    }
}

package dev.bstk.gameokkjogoforca.domain.repository;

import dev.bstk.gameokkjogoforca.domain.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

    @Query("SELECT p FROM Partida p WHERE p.uuid = :uuid")
    Optional<Partida> obterPartidaPorUuid(@Param("uuid") final UUID uuid);

}

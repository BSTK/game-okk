package dev.bstk.gameokkmatematica.domain;

import dev.bstk.gameokkmatematica.domain.model.DesafioTentativaResposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesafioRepository extends JpaRepository<DesafioTentativaResposta, Long> { }

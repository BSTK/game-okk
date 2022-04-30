package dev.bstk.gameokkjogomemoria.api;

import dev.bstk.gameokk.core.Mapper;
import dev.bstk.gameokkjogomemoria.api.response.PartidaResponse;
import dev.bstk.gameokkjogomemoria.domain.MemoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/memoria")
public class MemoriaController {


    private final MemoriaService memoriaService;

    @GetMapping("/partida/{partidaId}")
    public ResponseEntity<PartidaResponse> partida(@PathVariable("partidaId") final UUID partidaId) {
        final var partida = memoriaService.partida(partidaId);
        final var partidaResponse = Mapper.to(partida, PartidaResponse.class);

        return ResponseEntity.ok(partidaResponse);
    }

    @PostMapping("/jogar/{partidaId}/{numeroCarta}")
    public ResponseEntity<PartidaResponse> jogar(@PathVariable("numeroCarta") final Integer numeroCarta,
                                                 @PathVariable("partidaId") final UUID partidaId) {
        final var partidaJogada = memoriaService.jogar(numeroCarta, partidaId);
        final var partidaJogadaResponse = Mapper.to(partidaJogada, PartidaResponse.class);

        return ResponseEntity.ok(partidaJogadaResponse);
    }

    @PostMapping("/nova-partida")
    public ResponseEntity<PartidaResponse> novaPartida() {
        final var partida = memoriaService.novaPartida();
        final var partidaResponse = Mapper.to(partida, PartidaResponse.class);

        return ResponseEntity.ok(partidaResponse);
    }
}

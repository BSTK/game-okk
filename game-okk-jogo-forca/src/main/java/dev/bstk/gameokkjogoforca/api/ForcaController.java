package dev.bstk.gameokkjogoforca.api;

import dev.bstk.gameokk.core.Mapper;
import dev.bstk.gameokkjogoforca.api.response.PartidaResponse;
import dev.bstk.gameokkjogoforca.domain.service.ForcaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/forca")
public class ForcaController {


    private final ForcaService forcaService;

    @GetMapping("/partida/{partidaId}")
    public ResponseEntity<PartidaResponse> partida(@PathVariable("partidaId") final Long partidaId) {
        final var partida = forcaService.partida(partidaId);
        final var partidaResponse = Mapper.to(partida, PartidaResponse.class);

        return ResponseEntity.ok(partidaResponse);
    }

    @PostMapping("/jogar/{partidaId}/{letra}")
    public ResponseEntity<PartidaResponse> jogar(@PathVariable("letra") final String letra,
                                                 @PathVariable("partidaId") final Long partidaId) {
        final var partidaJogada = forcaService.jogar(letra, partidaId);
        final var partidaJogadaResponse = Mapper.to(partidaJogada, PartidaResponse.class);

        return ResponseEntity.ok(partidaJogadaResponse);
    }

    @PostMapping("/nova-partida")
    public ResponseEntity<PartidaResponse> novaPartida() {
        final var partida = forcaService.novaPartida();
        final var partidaResponse = Mapper.to(partida, PartidaResponse.class);

        return ResponseEntity.ok(partidaResponse);
    }
}

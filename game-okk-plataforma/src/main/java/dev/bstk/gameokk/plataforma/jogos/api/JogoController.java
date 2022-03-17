package dev.bstk.gameokk.plataforma.jogos.api;

import dev.bstk.gameokk.core.Mapper;
import dev.bstk.gameokk.plataforma.jogos.domain.JogoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/jogos")
public class JogoController {

    private final JogoService jogoService;

    @GetMapping
    public ResponseEntity<List<JogoResponse>> jogos() {
        final var jogos = jogoService.jogos();
        final var jogosResponse = Mapper.list(jogos, JogoResponse.class);

        return ResponseEntity.ok(jogosResponse);
    }

}

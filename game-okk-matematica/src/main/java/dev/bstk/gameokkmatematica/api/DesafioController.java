package dev.bstk.gameokkmatematica.api;

import dev.bstk.gameokk.core.Mapper;
import dev.bstk.gameokkmatematica.domain.service.DesafioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/matematica/desafios")
public class DesafioController {

    private final DesafioService desafioService;

    @GetMapping
    public ResponseEntity<DesafioResponse> desafioAleatorio() {
        final var desafio = desafioService.gerarDesafioAleatorio();
        final var desafioResponse = Mapper.to(desafio, DesafioResponse.class);
        return ResponseEntity.ok(desafioResponse);
    }

}

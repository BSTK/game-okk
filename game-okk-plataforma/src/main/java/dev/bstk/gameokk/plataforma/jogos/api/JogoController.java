package dev.bstk.gameokk.plataforma.jogos.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/plataforma/jogos")
public class JogosController {

    @GetMapping
    public ResponseEntity<List<JogosResponse>> jogos() {
        return ResponseEntity.ok(Collections.emptyList());
    }

}

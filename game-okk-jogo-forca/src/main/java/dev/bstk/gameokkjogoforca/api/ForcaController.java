package dev.bstk.gameokkjogoforca.api;

import dev.bstk.gameokkjogoforca.api.response.PartidaDicaResponse;
import dev.bstk.gameokkjogoforca.api.response.PartidaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/forca")
public class ForcaController {


    @GetMapping("/partida")
    public ResponseEntity<PartidaResponse> partida() {
        final var dicaResponse1 = new PartidaDicaResponse();
        dicaResponse1.setNumero(1);
        dicaResponse1.setDescircao("Uma fruta amarelada");
        dicaResponse1.setVisivel(false);

        final var dicaResponse2 = new PartidaDicaResponse();
        dicaResponse2.setNumero(2);
        dicaResponse2.setDescircao("Uma fruta que pode ser descascada");
        dicaResponse2.setVisivel(false);

        final var dicaResponse3 = new PartidaDicaResponse();
        dicaResponse3.setNumero(3);
        dicaResponse3.setDescircao("As folhas do pé são grandes e verdes");
        dicaResponse3.setVisivel(false);


        final var partidaResponse = new PartidaResponse();
        partidaResponse.setAlfabeto("abcdefghijklmnopqrstuvwxyz".toCharArray());
        partidaResponse.setPalavraSecreta(new char[]{'B', 'A', 'N', 'A', 'N', 'A'});
        partidaResponse.setDicas(List.of(dicaResponse1, dicaResponse2, dicaResponse3));

        return ResponseEntity.ok(partidaResponse);
    }
}

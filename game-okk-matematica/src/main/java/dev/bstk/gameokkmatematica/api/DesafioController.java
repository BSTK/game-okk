package dev.bstk.gameokkmatematica.api;

import dev.bstk.gameokk.core.Mapper;
import dev.bstk.gameokkmatematica.api.request.DesafioTentativaRespostaRequest;
import dev.bstk.gameokkmatematica.api.response.DesafioResponse;
import dev.bstk.gameokkmatematica.api.response.DesafioTentativaRespostaResponse;
import dev.bstk.gameokkmatematica.domain.DesafioTentativaResposta;
import dev.bstk.gameokkmatematica.domain.service.DesafioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/matematica")
public class DesafioController {

    private final DesafioService desafioService;

    @GetMapping("/desafios")
    public ResponseEntity<DesafioResponse> desafioAleatorio() {
        final var desafio = desafioService.gerarDesafioAleatorio();
        final var desafioResponse = Mapper.to(desafio, DesafioResponse.class);

        return ResponseEntity.ok(desafioResponse);
    }

    @PostMapping("/tentativa-resposta")
    public ResponseEntity<DesafioTentativaRespostaResponse> tentativaResposta(
        @RequestBody @Valid final DesafioTentativaRespostaRequest request) {
        log.info("Recebido uma nova tentativa do usuário: [ {} ]", request.getApelido());

        final DesafioTentativaResposta tentativaResposta = desafioService.tentativaResposta(request);
        final DesafioTentativaRespostaResponse tentativaRespostaResponse = Mapper
            .to(tentativaResposta, DesafioTentativaRespostaResponse.class);

        return ResponseEntity.ok(tentativaRespostaResponse);
    }

}

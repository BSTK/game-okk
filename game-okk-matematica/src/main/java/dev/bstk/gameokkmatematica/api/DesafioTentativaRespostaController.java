package dev.bstk.gameokkmatematica.api;

import dev.bstk.gameokk.core.Mapper;
import dev.bstk.gameokkmatematica.api.request.DesafioTentativaRespostaRequest;
import dev.bstk.gameokkmatematica.api.response.DesafioTentativaRespostaResponse;
import dev.bstk.gameokkmatematica.domain.DesafioTentativaResposta;
import dev.bstk.gameokkmatematica.domain.service.DesafioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/matematica/tentativa-resposta")
public class DesafioTentativaRespostaController {

    private final DesafioService desafioService;

    @PostMapping
    public ResponseEntity<DesafioTentativaRespostaResponse> verificarResposta(
        @RequestBody @Valid final DesafioTentativaRespostaRequest request) {
        log.info("Recebido uma nova tentativa do usuário: [ {} ]", request.getApelido());

        final DesafioTentativaResposta tentativaResposta = desafioService.verificarResposta(request);
        final DesafioTentativaRespostaResponse tentativaRespostaResponse = Mapper
            .to(tentativaResposta, DesafioTentativaRespostaResponse.class);

        return ResponseEntity.ok(tentativaRespostaResponse);
    }

}

package dev.bstk.gameokkjogoforca.domain;

import dev.bstk.gameokk.core.TesteHelper;
import dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta;
import dev.bstk.gameokkjogoforca.domain.model.Partida;
import dev.bstk.gameokkjogoforca.domain.service.EsconderLetraService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class EsconderLetraServiceTest {

    @InjectMocks
    private EsconderLetraService esconderLetraService;

    @Test
    @DisplayName("Deve escoder todas letras de uma nova partida")
    void deveEscoderTodasLetrasDeUmaNovaPartida() {
        final var novaPartidaCriada = TesteHelper.fixure("/fixture/forca/nova-partida.json", Partida.class);

        Partida novaPartidaCriadaComLetrasEscondidas = esconderLetraService.esconder(novaPartidaCriada);

        Assertions.assertThat(novaPartidaCriadaComLetrasEscondidas)
            .isNotNull();

        PalavraSecreta palavraSecreta = novaPartidaCriadaComLetrasEscondidas.getPalavraSecreta();

        Assertions.assertThat(palavraSecreta)
            .isNotNull();

        Assertions.assertThat(palavraSecreta.getPalavra())
            .isNotNull()
            .isNotEmpty()
            .containsExactlyElementsOf(Collections.nCopies(palavraSecreta.getPalavra().size(), PalavraSecreta.CARACTERE_MASCARA));

        Assertions.assertThat(palavraSecreta.getDicas())
            .isNotNull()
            .isEmpty();
    }
}

package dev.bstk.gameokkjogoforca.domain;

import dev.bstk.gameokk.core.TesteHelper;
import dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta;
import dev.bstk.gameokkjogoforca.domain.model.Partida;
import dev.bstk.gameokkjogoforca.domain.repository.PartidaRepository;
import dev.bstk.gameokkjogoforca.domain.service.ForcaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForcaServiceTest {

    @InjectMocks
    private ForcaService forcaService;

    @Mock
    private PartidaRepository partidaRepository;

    @Test
    @DisplayName("Deve criar uma nova partida")
    void deveCriarUmaNovaPartida() {
        final var novaPartidaCriada = TesteHelper.fixure("/fixture/forca/nova-partida.json", Partida.class);
        when(partidaRepository.save(any(Partida.class))).thenReturn(novaPartidaCriada);

        final var novaPartida = forcaService.novaPartida();

        Assertions.assertThat(novaPartida)
            .isNotNull();

        Assertions.assertThat(novaPartida.getAlfabeto())
            .isNotNull()
            .isNotEmpty();

        PalavraSecreta palavraSecreta = novaPartida.getPalavraSecreta();

        Assertions.assertThat(palavraSecreta)
            .isNotNull();

        Assertions.assertThat(palavraSecreta.getPalavra())
            .isNotNull()
            .isNotEmpty()
            .containsExactlyElementsOf(Collections.nCopies(palavraSecreta.getPalavra().size(), PalavraSecreta.MASCARA));

        Assertions.assertThat(palavraSecreta.getDicas())
            .isNotNull()
            .isEmpty();
    }
}

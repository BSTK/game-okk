package dev.bstk.gameokk.plataforma.jogos.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JogoServiceTest {

    @InjectMocks
    private JogoService jogoService;

    @Test
    @DisplayName("Deve retonar uma lista de jogos")
    void deveRetonarUmaListaDeJogos() {
        final var jogos = jogoService.jogos();

        Assertions.assertThat(jogos)
            .isNotNull()
            .isNotEmpty()
            .hasOnlyElementsOfType(Jogo.class);
    }
}

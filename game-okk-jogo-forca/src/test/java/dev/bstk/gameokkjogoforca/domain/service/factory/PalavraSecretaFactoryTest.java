package dev.bstk.gameokkjogoforca.domain.service.factory;

import dev.bstk.gameokkjogoforca.domain.model.PalavraSecreta;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PalavraSecretaFactoryTest {

    @Test
    @DisplayName("Deve retornar uma palavra secreta valida")
    void deveRetornarUmaPalavraSecretaValida() {
        PalavraSecreta palavra = PalavraSecretaFactory.palavra();

        Assertions.assertThat(palavra).isNotNull();
        Assertions.assertThat(palavra.getPalavra())
            .isNotNull()
            .isNotEmpty();

        Assertions.assertThat(palavra.getDicas())
            .isNotNull()
            .isNotEmpty();
    }
}

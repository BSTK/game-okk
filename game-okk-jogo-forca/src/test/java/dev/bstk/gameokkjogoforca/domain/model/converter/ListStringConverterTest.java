package dev.bstk.gameokkjogoforca.domain.model.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ListStringConverterTest {

    private final ListStringConverter converter = new ListStringConverter();

    @Test
    @DisplayName("Deve converter uma lista de strings em uma string JSON valida")
    void deveConverterUmaListaDeStringsEmUmaStringJSONValida() {
        final var listaDeStringSalvar = converter.convertToDatabaseColumn(List.of("C", "A", "R"));

        Assertions.assertThat(listaDeStringSalvar)
            .isNotNull()
            .isNotEmpty()
            .isNotBlank()
            .contains("C", "A", "R")
            .contains(",")
            .contains("[")
            .contains("]");
    }

    @Test
    @DisplayName("Deve converter uma string JSON em uma lista de Dicas valida")
    void deveConverterUmaStringJSONListaDicasEmUmaListaDeDicasValida() {
        List<String> listaDeStringBanco = converter.convertToEntityAttribute("[\"C\",\"A\",\"R\"]");

        Assertions.assertThat(listaDeStringBanco)
            .isNotNull()
            .isNotEmpty()
            .contains("C", "A", "R");
    }
}

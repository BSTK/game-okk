package dev.bstk.gameokkjogoforca.domain.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.gameokk.core.TesteHelper;
import dev.bstk.gameokkjogoforca.domain.model.Dica;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ListDicaConverterTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ListDicaConverter converter = new ListDicaConverter();

    @Test
    @DisplayName("Deve converter uma lista de dicas em uma string JSON valida")
    void deveConverterUmaListaDeDicasEmUmaStringJSONValida() throws JsonProcessingException {
        final var dicas = TesteHelper.fixure("/fixture/forca/palavra-secreta-dicas.json", Dica[].class);
        final var dicasJsonEsperado = objectMapper.writeValueAsString(dicas);

        final var dicasJson = converter.convertToDatabaseColumn(Arrays.asList(dicas));

        Assertions.assertThat(dicasJson)
            .isNotNull()
            .isNotEmpty()
            .isNotBlank()
            .isEqualTo(dicasJsonEsperado);
    }

    @Test
    @DisplayName("Deve retonar uma string vazia para uma lista nula")
    void deveRetonarUmaStringVaziaParaUmaListaNula() {
        final var listaDeStringSalvar = converter.convertToDatabaseColumn(null);

        Assertions.assertThat(listaDeStringSalvar)
            .isNotNull()
            .isEmpty();
    }

    @Test
    @DisplayName("Deve retonar uma string vazia para uma lista vazia")
    void deveRetonarUmaStringVaziaParaUmaListaVazia() {
        final var listaDeStringSalvar = converter.convertToDatabaseColumn(Collections.emptyList());

        Assertions.assertThat(listaDeStringSalvar)
            .isNotNull()
            .isEmpty();
    }

    @Test
    @DisplayName("Deve converter uma string JSON em uma lista de Dicas valida")
    void deveConverterUmaStringJSONListaDicasEmUmaListaDeDicasValida() throws JsonProcessingException {
        final var dicas = TesteHelper.fixure("/fixture/forca/palavra-secreta-dicas.json", Dica[].class);
        final var dicasJson = objectMapper.writeValueAsString(dicas);

        List<Dica> dicasParseada = converter.convertToEntityAttribute(dicasJson);

        Assertions.assertThat(dicasParseada)
            .isNotNull()
            .isNotEmpty()
            .hasSize(3)
            .hasOnlyElementsOfType(Dica.class);
    }

    @Test
    @DisplayName("Deve retonar uma lista vazia uma string nula")
    void deveRetonarUmaListaVaziaUmaStringNula() {
        final var listaDeStringSalvar = converter.convertToEntityAttribute(null);

        Assertions.assertThat(listaDeStringSalvar)
            .isNotNull()
            .isEmpty();
    }

    @Test
    @DisplayName("Deve retonar uma lista vazia uma string vazia")
    void deveRetonarUmaListaVaziaUmaStringVazia() {
        final var listaDeStringSalvar = converter.convertToEntityAttribute("");

        Assertions.assertThat(listaDeStringSalvar)
            .isNotNull()
            .isEmpty();
    }
}

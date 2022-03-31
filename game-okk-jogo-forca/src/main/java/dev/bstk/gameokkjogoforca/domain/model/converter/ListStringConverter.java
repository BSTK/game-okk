package dev.bstk.gameokkjogoforca.domain.model.converter;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStringConverter implements AttributeConverter<List<String>, String> {

    private static final String STRING_EMPTY = "";
    private static final String STRING_ASPAS_DUPLAS = "\"";
    private static final String STRING_COLCHETE_FIM = "]";
    private static final String STRING_COLCHETE_INICIO = "[";
    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(final List<String> attribute) {
        if (CollectionUtils.isEmpty(attribute)) {
            return STRING_EMPTY;
        }

        final var dadoConvertido = new StringBuilder(STRING_COLCHETE_INICIO);

        for (String dado : attribute) {
            dadoConvertido
                .append(String.format("\"%s\"", dado))
                .append(DELIMITER);
        }

        return dadoConvertido
            .append(STRING_COLCHETE_FIM)
            .toString();
    }

    @Override
    public List<String> convertToEntityAttribute(final String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return new ArrayList<>();
        }

        String dbDataLimpo = dbData
            .replace(STRING_COLCHETE_INICIO, STRING_EMPTY)
            .replace(STRING_COLCHETE_FIM, STRING_EMPTY)
            .replace(STRING_ASPAS_DUPLAS, STRING_EMPTY);

        return Arrays.asList(dbDataLimpo.split(DELIMITER));
    }
}

package dev.bstk.gameokkjogoforca.domain.model.converter;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListStringConverter implements AttributeConverter<List<String>, String> {

    private static final String STRING_EMPTY = "";
    private static final String STRING_FORMAT = "[%s]";
    private static final String STRING_COLCHETE_FIM = "]";
    private static final String STRING_COLCHETE_INICIO = "[";
    private static final String DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(final List<String> attribute) {
        return notEmpty(attribute)
            ? String.format(STRING_FORMAT, String.join(DELIMITER, attribute))
            : STRING_EMPTY;
    }

    @Override
    public List<String> convertToEntityAttribute(final String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return Collections.emptyList();
        }

        String dbDataLimpo = dbData
            .replace(STRING_COLCHETE_INICIO, STRING_EMPTY)
            .replace(STRING_COLCHETE_FIM, STRING_EMPTY);

        return Arrays.asList(dbDataLimpo.split(DELIMITER));
    }

    private boolean notEmpty(@Nullable Collection<?> collection) {
        return !CollectionUtils.isEmpty(collection);
    }
}

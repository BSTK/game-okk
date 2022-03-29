package dev.bstk.gameokkjogoforca.domain.model.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bstk.gameokkjogoforca.domain.model.Dica;
import lombok.SneakyThrows;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListDicaConverter implements AttributeConverter<List<Dica>, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(final List<Dica> attribute) {
        return !CollectionUtils.isEmpty(attribute)
            ? OBJECT_MAPPER.writeValueAsString(attribute)
            : "";
    }

    @Override
    @SneakyThrows
    public List<Dica> convertToEntityAttribute(final String dbData) {
        return StringUtils.hasLength(dbData)
            ? Arrays.asList(OBJECT_MAPPER.readValue(dbData, Dica[].class))
            : Collections.emptyList();
    }
}

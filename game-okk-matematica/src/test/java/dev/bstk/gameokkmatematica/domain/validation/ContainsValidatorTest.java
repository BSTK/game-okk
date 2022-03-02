package dev.bstk.gameokkmatematica.domain.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Payload;
import java.lang.annotation.Annotation;


class ContainsValidatorTest {

    private final ContainsValidator validator = new ContainsValidator();

    @Test
    @DisplayName("Deve retonar TRUE para validacao invalida")
    void deveRetonarTrueParaValidacaoInvalida() {
        validator.initialize(annotationImpl(new String[] { "+", "@" }));

        Assertions.assertThat(validator.isValid("+", null)).isTrue();
        Assertions.assertThat(validator.isValid("@", null)).isTrue();
    }

    @Test
    @DisplayName("Deve retonar FALSE para validacao valida")
    void deveRetonarFalseParaValidacaoValida() {
        validator.initialize(annotationImpl(new String[] { "+", "@" }));

        Assertions.assertThat(validator.isValid("%", null)).isFalse();
        Assertions.assertThat(validator.isValid("$", null)).isFalse();
        Assertions.assertThat(validator.isValid("", null)).isFalse();
        Assertions.assertThat(validator.isValid(null, null)).isFalse();
    }

    @Test
    @DisplayName("Deve retonar FALSE para validacao valida - Range não definido (nulo)")
    void deveRetonarFalseParaValidacaoValida_RangeNaoDefinidoNulo() {
        validator.initialize(annotationImpl(null));
        Assertions.assertThat(validator.isValid("@", null)).isFalse();
    }

    @Test
    @DisplayName("Deve retonar FALSE para validacao valida - Range não definido (vazio)")
    void deveRetonarFalseParaValidacaoValida_RangeNaoDefinidoVazio() {
        validator.initialize(annotationImpl(new String[] {}));
        Assertions.assertThat(validator.isValid("@", null)).isFalse();
    }

    private Contains annotationImpl(final String[] range) {
        return new Contains() {
            @Override
            public String message() { return ""; }

            @Override
            public String[] range() { return range; }

            @Override
            public Class<?>[] groups() { return new Class[0]; }

            @Override
            public Class<? extends Payload>[] payload() { return new Class[0]; }

            @Override
            public Class<? extends Annotation> annotationType() { return Contains.class; }
        };
    }
}

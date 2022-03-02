package dev.bstk.gameokkmatematica.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasLength;

public class ContainsValidator implements ConstraintValidator<Contains, String> {

    private String[] range;

    @Override
    public void initialize(Contains annotation) {
        range = annotation.range();
    }

    @Override
    public boolean isValid(final String valor,
                           final ConstraintValidatorContext context) {
        if (!hasLength(valor)) {
            return false;
        }

        if (isNull(range) || range.length == 0) {
            return false;
        }

        for (String item : range) {
            if (item.equals(valor)) {
                return true;
            }
        }

        return false;
    }
}

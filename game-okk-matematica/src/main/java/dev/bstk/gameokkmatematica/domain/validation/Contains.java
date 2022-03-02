package dev.bstk.gameokkmatematica.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContainsValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
public @interface Contains {

    String[] range() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "O valor deve ser está contido no range informado";

}

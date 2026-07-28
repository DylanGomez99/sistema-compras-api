package com.compras.sistemacomprasapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CedulaValidator.class)
@Documented
public @interface CedulaValida {
    String message() default "La cédula ingresada no es válida en República Dominicana";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

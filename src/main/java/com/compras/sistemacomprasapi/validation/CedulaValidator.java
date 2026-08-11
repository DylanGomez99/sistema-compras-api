package com.compras.sistemacomprasapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CedulaValidator implements ConstraintValidator<CedulaValida, String> {

    @Override
    public boolean isValid(String cedula, ConstraintValidatorContext context) {

        if (cedula == null || cedula.trim().isEmpty()) {
            return false;
        }

        String cedulaLimpia = cedula.replaceAll("[^0-9]", "");

        if (cedulaLimpia.length() != 11) {
            return false;
        }

        int suma = 0;
        int[] multiplicadores = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2};

        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cedulaLimpia.charAt(i));
            int producto = digito * multiplicadores[i];

            if (producto >= 10) {
                producto = (producto / 10) + (producto % 10);
            }
            suma += producto;
        }

        int digitoVerificadorCalculado = (10 - (suma % 10)) % 10;
        int digitoVerificadorReal = Character.getNumericValue(cedulaLimpia.charAt(10));

        return digitoVerificadorCalculado == digitoVerificadorReal;
    }
}

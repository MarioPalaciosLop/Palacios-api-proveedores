package com.provedores.api.validator;

import com.provedores.api.entity.GrupoProveedor;

public class GrupoProveedorValidator {

    public static void save(GrupoProveedor grupo) {
        // Validar longitud del código del grupo
        validateStringLength(grupo.getCodigoGrupo(), "Código del grupo", 1, 10);

        // Validar longitud de la descripción del grupo
        validateStringLength(grupo.getGrupoDescripcion(), "Descripción del grupo", 1, 255);

        // Puedes agregar más validaciones de longitud según tus necesidades

        // Validar que la empresa no sea nula
        if (grupo.getEmpresa() == null) {
            throw new RuntimeException("La empresa no puede ser nula");
        }

        // Validar que la sucursal no sea nula
        if (grupo.getSucursal() == null) {
            throw new RuntimeException("La sucursal no puede ser nula");
        }

        // Puedes agregar más validaciones según tus requisitos

    }

    private static void validateStringLength(String value, String fieldName, int minLength, int maxLength) {
        if (value == null) {
            throw new RuntimeException(fieldName + " no puede ser nulo");
        }

        int length = value.trim().length();
        if (length < minLength || length > maxLength) {
            throw new RuntimeException(fieldName + " debe tener entre " + minLength + " y " + maxLength + " caracteres");
        }
    }
}

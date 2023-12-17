package com.provedores.api.validator;

import com.provedores.api.entity.GrupoProveedor;

public class GrupoProveedorValidator {

    public static void save(GrupoProveedor grupos) {

        if (grupos == null) {
            throw new IllegalArgumentException("El grupo proveedor no puede ser nulo");
        }

        if (grupos.getCodigoGrupo().isEmpty() || grupos.getGrupoDescripcion().isEmpty()) {
            throw new IllegalArgumentException("Código de grupo y descripción no pueden estar vacíos");
        }

        if (grupos.getEmpresa() == null || grupos.getSucursal() == null) {
            throw new IllegalArgumentException("La empresa y la sucursal son obligatorias");
        }

        if (grupos.getCreatedAt() == null || grupos.getUpdatedAt() == null) {
            throw new IllegalArgumentException("Las fechas de auditoría no pueden estar vacías");
        }

        if (grupos.getCodigoGrupo().length() > 10) {
            throw new IllegalArgumentException("El código de grupo no puede tener más de 10 caracteres");
        }
    }
}

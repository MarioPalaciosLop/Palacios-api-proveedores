package com.provedores.api.converter;

import org.springframework.stereotype.Component;

import com.provedores.api.dto.EmpresaDTO;
import com.provedores.api.dto.GruposProveedorDTO;
import com.provedores.api.entity.Empresa;
import com.provedores.api.entity.GrupoProveedor;
import com.provedores.api.entity.Sucursal;

@Component
public class ProveedorConverter extends AbstractConverter<GrupoProveedor, GruposProveedorDTO> {

    // private int id;
    // private String codigoGrupo;
    // private String grupoDescripcion;
    // private Empresa empresa;
    // private Sucursal sucursal;
    /*
     * Cuerpo del API GruposProveedor:
     * {
     * "id": 93,
     * "codigoGrupo": "1234556",
     * "grupoDescripcion": "ABC",
     * "empresa": {
     * "id": 1,
     * "nombre": "PRUEBA ACTUALIZACION 1",
     * "nroDocumento": "EXAMPLE1",
     * "razonSocial": "EXAMPLE1",
     * "direccion": "EXAMPLE1",
     * "activo": false,
     * "createdAt": "2023-12-16T22:52:55.000+00:00",
     * "updatedAt": "2023-12-16T23:05:01.000+00:00"
     * },
     * "activo": true,
     * "sucursal": {
     * "id": 10,
     * "nombre": "PRUEBA",
     * "empresa": {
     * "id": 1,
     * "nombre": "PRUEBA ACTUALIZACION 1",
     * "nroDocumento": "EXAMPLE1",
     * "razonSocial": "EXAMPLE1",
     * "direccion": "EXAMPLE1",
     * "activo": false,
     * "createdAt": "2023-12-16T22:52:55.000+00:00",
     * "updatedAt": "2023-12-16T23:05:01.000+00:00"
     * },
     * "direccion": "PRUEBA",
     * "createdAt": "2023-12-17T03:00:43.000+00:00",
     * "updatedAt": null
     * },
     * "createdAt": "2023-12-17T03:07:15.000+00:00",
     * "updatedAt": null
     * }
     */

    @Override
    public GruposProveedorDTO fromEntity(GrupoProveedor entity) {
        if (entity == null)
            return null;
        return GruposProveedorDTO.builder()
                .id(entity.getId())
                .codigoGrupo(entity.getCodigoGrupo())
                .grupoDescripcion(entity.getGrupoDescripcion())
                .empresa(entity.getEmpresa())
                .sucursal(entity.getSucursal())
                .build();
    }

    @Override
    public GrupoProveedor fromDTO(GruposProveedorDTO dto) {
        if (dto == null)
            return null;

        Empresa empresaDTO = dto.getEmpresa();
        Sucursal sucursalDTO = dto.getSucursal();

        if (empresaDTO == null || sucursalDTO == null) {
            return null;
        }

        Empresa empresa = Empresa.builder()
                .id(empresaDTO.getId())
                .nombre(empresaDTO.getNombre())
                .nroDocumento(empresaDTO.getNroDocumento())
                .razonSocial(empresaDTO.getRazonSocial())
                .direccion(empresaDTO.getDireccion())
                .build();

        Sucursal sucursal = Sucursal.builder()
                .id(sucursalDTO.getId())
                .nombre(sucursalDTO.getNombre())
                .direccion(sucursalDTO.getDireccion())
                .build();

        return GrupoProveedor.builder()
                .id(dto.getId())
                .codigoGrupo(dto.getCodigoGrupo())
                .grupoDescripcion(dto.getGrupoDescripcion())
                .empresa(empresa)
                .sucursal(sucursal)
                .build();
    }

}

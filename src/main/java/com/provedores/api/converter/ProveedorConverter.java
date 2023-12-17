package com.provedores.api.converter;

import org.springframework.stereotype.Component;

import com.provedores.api.dto.GruposProveedorDTO;
import com.provedores.api.entity.Empresa;
import com.provedores.api.entity.GrupoProveedor;
import com.provedores.api.entity.Sucursal;

@Component
public class ProveedorConverter extends AbstractConverter<GrupoProveedor, GruposProveedorDTO> {

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

package com.provedores.api.dto;

import com.provedores.api.entity.Empresa;
import com.provedores.api.entity.Sucursal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GruposProveedorDTO {
    
    private int id;
    private String codigoGrupo;
    private String grupoDescripcion;
    private Empresa empresa;
    private Sucursal sucursal;
    
}

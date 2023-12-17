package com.provedores.api.dto;

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
public class EmpresaDTO {
    
    private int id;
    private String nombre;
    private String nroDocumento;
    private String razonSocial;
    private String direccion;
    private Boolean activo;

}

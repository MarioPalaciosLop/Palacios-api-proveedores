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
public class SucursalDTO {
    
    private int id;
    private String nombre;
    private String nroDocumento;
    private String direccion;

}

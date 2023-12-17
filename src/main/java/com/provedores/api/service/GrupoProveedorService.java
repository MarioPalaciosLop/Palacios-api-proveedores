package com.provedores.api.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.provedores.api.entity.GrupoProveedor;

public interface GrupoProveedorService {
    
    public List<GrupoProveedor> findAll(Pageable page);
    public List<GrupoProveedor> findByGrupoDescripcion(String nombre, Pageable page);
    public GrupoProveedor findById(int id);
    public GrupoProveedor save(GrupoProveedor grupo);
    public GrupoProveedor update(GrupoProveedor grupo);
    public void delete(int id);

}

package com.provedores.api.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.provedores.api.entity.Sucursal;

public interface SucursalService {
    
    public List<Sucursal> findAll(Pageable page);
    public List<Sucursal> findByNombre(String nombre, Pageable page);
    public Sucursal findById(int id);
    public Sucursal save(Sucursal sucursal);
    public Sucursal update(Sucursal sucursal);
    public void delete(int id);
    
}

package com.provedores.api.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.provedores.api.entity.Empresa;

public interface EmpresaService {

    public List<Empresa> findAll(Pageable page);
    public List<Empresa> findByNombre(String nombre, Pageable page);
    public Empresa findById(int id);
    public Empresa save(Empresa empresa);
    public Empresa update(Empresa empresa);
    public void delete(int id);

}

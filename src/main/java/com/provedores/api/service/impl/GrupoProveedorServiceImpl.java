package com.provedores.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provedores.api.entity.GrupoProveedor;
import com.provedores.api.repository.GrupoProveedorRepository;
import com.provedores.api.service.GrupoProveedorService;
import com.provedores.api.validator.GrupoProveedorValidator;

@Service
public class GrupoProveedorServiceImpl implements GrupoProveedorService {

    @Autowired
    private GrupoProveedorRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<GrupoProveedor> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<GrupoProveedor> findByGrupoDescripcion(String nombre, Pageable page) {
        try {
            return repository.findByGrupoDescripcionContaining(nombre, page);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GrupoProveedor findById(int id) {
        try {
            GrupoProveedor registro = repository.findById(id).orElseThrow();
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public GrupoProveedor save(GrupoProveedor grupo) {
        try {
            //GrupoProveedorValidator.save(grupo);
            grupo.setActivo(true);
            GrupoProveedor registro = repository.save(grupo);
            return registro; 
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public GrupoProveedor update(GrupoProveedor grupo) {
       try {
        //GrupoProveedorValidator.save(grupo);
        GrupoProveedor registro = repository.findById(grupo.getId()).orElseThrow();
        registro.setGrupoDescripcion(grupo.getGrupoDescripcion());
        registro.setEmpresa(grupo.getEmpresa());
        registro.setCodigoGrupo(grupo.getCodigoGrupo());
        registro.setSucursal(grupo.getSucursal());
        repository.save(registro);
        return registro;
       } catch (Exception e) {
        return null;
       }
    }

    @Override
    public void delete(int id) {
        try{
            GrupoProveedor registro = repository.findById(id).orElseThrow();
            repository.delete(registro);
        } catch (Exception e){

        }
    }
    
}

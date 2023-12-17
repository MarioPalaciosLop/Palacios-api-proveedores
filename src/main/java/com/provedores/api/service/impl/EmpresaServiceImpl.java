package com.provedores.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provedores.api.entity.Empresa;
import com.provedores.api.repository.EmpresaRepository;
import com.provedores.api.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Empresa> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empresa> findByNombre(String nombre, Pageable page) {
        try {
            return repository.findByNombreContaining(nombre, page);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Empresa findById(int id) {
        try {
            Empresa registro = repository.findById(id).orElseThrow();
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Empresa save(Empresa empresa) {
        try {
            empresa.setActivo(true);
            Empresa registro = repository.save(empresa);
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Empresa update(Empresa empresa) {
        try {
            Empresa registro = repository.findById(empresa.getId()).orElseThrow();
            registro.setNombre(empresa.getNombre());
            registro.setDireccion(empresa.getDireccion());
            registro.setNroDocumento(empresa.getNroDocumento());
            registro.setRazonSocial(empresa.getRazonSocial());
            repository.save(registro);
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try {
            Empresa registro = repository.findById(id).orElseThrow();
            repository.delete(registro);
        } catch (Exception e) {

        }
    }

}

package com.provedores.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provedores.api.entity.Sucursal;
import com.provedores.api.repository.SucursalRepository;
import com.provedores.api.service.SucursalService;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Sucursal> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sucursal> findByNombre(String nombre, Pageable page) {
        try {
            return repository.findByNombreContaining(nombre, page);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Sucursal findById(int id) {
        try {
            Sucursal registro = repository.findById(id).orElseThrow();
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Sucursal save(Sucursal sucursal) {
        try {
            Sucursal registro = repository.save(sucursal);
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Sucursal update(Sucursal sucursal) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(int id) {
        try {
            Sucursal registro = repository.findById(id).orElseThrow();
            repository.delete(registro);
        } catch (Exception e) {

        }
    }

}

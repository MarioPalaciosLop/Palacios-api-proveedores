package com.provedores.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.provedores.api.entity.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal,Integer>{
    
    List<Sucursal> findByNombreContaining(String nombre, Pageable page);

}

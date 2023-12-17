package com.provedores.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.provedores.api.entity.GrupoProveedor;

public interface GrupoProveedorRepository extends JpaRepository<GrupoProveedor,Integer>{
    
    List<GrupoProveedor> findByGrupoDescripcionContaining(String grupoDescripcion, Pageable page);

}

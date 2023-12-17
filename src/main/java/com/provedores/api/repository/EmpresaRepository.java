package com.provedores.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.provedores.api.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Integer>{

    List<Empresa> findByNombreContaining(String nombre, Pageable page);

}

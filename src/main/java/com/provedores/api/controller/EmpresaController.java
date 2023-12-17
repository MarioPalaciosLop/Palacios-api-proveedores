package com.provedores.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.provedores.api.service.EmpresaService;

import com.provedores.api.entity.Empresa;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @GetMapping()
    public ResponseEntity<List<Empresa>> findAll(
            @RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Empresa> empresas;
        if (nombre == null) {
            empresas = service.findAll(page);
        } else {
            empresas = service.findByNombre(nombre, page);
        }

        if (empresas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(empresas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Empresa> findById(@PathVariable("id") int id) {
        Empresa empresa = service.findById(id);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

    @PostMapping()
    public ResponseEntity<Empresa> create(@RequestBody Empresa empresa) {
        Empresa registro = service.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Empresa> update(@PathVariable("id") int id, @RequestBody Empresa empresa) {
        Empresa registro = service.update(empresa);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Empresa> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }

}

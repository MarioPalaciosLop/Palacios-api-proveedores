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
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.provedores.api.entity.Sucursal;
import com.provedores.api.service.SucursalService;


@RestController
@RequestMapping("/sucursales")
public class SucursalController {
    
    @Autowired
    private SucursalService service;

    @GetMapping()
    public ResponseEntity<List<Sucursal>> findAll(
            @RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Sucursal> sucursales;
        if (nombre == null) {
            sucursales = service.findAll(page);
        } else {
            sucursales = service.findByNombre(nombre, page);
        }

        if (sucursales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sucursales);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sucursal> findById(@PathVariable("id") int id) {
        Sucursal sucursal = service.findById(id);
        if (sucursal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursal);
    }

    @PostMapping()
    public ResponseEntity<Sucursal> create(@RequestBody Sucursal sucursal) {
        Sucursal registro = service.save(sucursal);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Sucursal> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }
    




}

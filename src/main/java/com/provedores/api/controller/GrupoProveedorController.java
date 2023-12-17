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

import com.provedores.api.entity.GrupoProveedor;
import com.provedores.api.service.GrupoProveedorService;

@RestController
@RequestMapping("/grupos-proveedores")
public class GrupoProveedorController {

    @Autowired
    private GrupoProveedorService service;

    @GetMapping()
    public ResponseEntity<List<GrupoProveedor>> findAll(
            @RequestParam(value = "grupoDescripcion", required = false, defaultValue = "") String descripcion,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<GrupoProveedor> grupos;
        if (descripcion == null) {
            grupos = service.findAll(page);
        } else {
            grupos = service.findByGrupoDescripcion(descripcion, page);
        }

        if (grupos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(grupos);
    }

    @PostMapping()
    public ResponseEntity<GrupoProveedor> create(@RequestBody GrupoProveedor grupos) {
        GrupoProveedor registro = service.save(grupos);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GrupoProveedor> update(@PathVariable("id") int id, @RequestBody GrupoProveedor grupos) {
        GrupoProveedor registro = service.update(grupos);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grupos);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GrupoProveedor> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }

}

package com.provedores.api.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.provedores.api.converter.ProveedorConverter;
import com.provedores.api.dto.GruposProveedorDTO;
import com.provedores.api.entity.GrupoProveedor;
import com.provedores.api.service.GrupoProveedorService;
import com.provedores.api.utils.WrapperResponse;

import org.slf4j.Logger;

@RestController
@RequestMapping("/grupos-proveedores")
@CrossOrigin(origins = "http://localhost:9001")
public class GrupoProveedorController {

    private final Logger logger = LoggerFactory.getLogger(GrupoProveedorController.class);

    @Autowired
    private GrupoProveedorService service;

    @Autowired
    private ProveedorConverter converter;

    @GetMapping()
    public ResponseEntity<List<GruposProveedorDTO>> findAll(
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
        List<GruposProveedorDTO> gruposDTO = converter.fromEntity(grupos);
        return new WrapperResponse(true, "success", gruposDTO).createResponse(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WrapperResponse<GruposProveedorDTO>> findById(@PathVariable("id") int id) {
        GrupoProveedor grupos = service.findById(id);
        if (grupos == null) {
            return ResponseEntity.notFound().build();
        }
        GruposProveedorDTO gruposDTO = converter.fromEntity(grupos);
        return new WrapperResponse(true, "success", gruposDTO).createResponse(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<WrapperResponse<GruposProveedorDTO>> create(@RequestBody GruposProveedorDTO gruposDTO) {
        GrupoProveedor registro = service.save(converter.fromDTO(gruposDTO));
        GruposProveedorDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse(true, "success", registroDTO).createResponse(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GruposProveedorDTO> update(@PathVariable("id") int id,
        @RequestBody GruposProveedorDTO gruposDTO) {
        GrupoProveedor registro = service.update(converter.fromDTO(gruposDTO));
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        GruposProveedorDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse(true, "success", registroDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GruposProveedorDTO> delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

}

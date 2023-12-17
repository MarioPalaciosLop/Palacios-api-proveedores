package com.provedores.api.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
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

import com.provedores.api.converter.ProveedorConverter;
import com.provedores.api.dto.GruposProveedorDTO;
import com.provedores.api.entity.GrupoProveedor;
import com.provedores.api.service.GrupoProveedorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Ejemplo del Post : 
// {
//   "id": 3,
//   "codigoGrupo": "pruebadto",
//   "grupoDescripcion": "pruebadto",
//   "empresa": {
//     "id": 1
//   },
//   "sucursal": {
//     "id": 37
//   }
// }

@RestController
@RequestMapping("/grupos-proveedores")
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
        return ResponseEntity.ok(gruposDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GruposProveedorDTO> findById(@PathVariable("id") int id) {
        GrupoProveedor grupos = service.findById(id);
        if (grupos == null) {
            return ResponseEntity.notFound().build();
        }
        GruposProveedorDTO gruposDTO = converter.fromEntity(grupos);
        return ResponseEntity.ok(gruposDTO);
    }

    @PostMapping()
    public ResponseEntity<GruposProveedorDTO> create(@RequestBody GruposProveedorDTO gruposDTO) {
        try {
            logger.info("Recibida solicitud para crear un nuevo GrupoProveedor: {}", gruposDTO);

            GrupoProveedor registro = service.save(converter.fromDTO(gruposDTO));

            if (registro != null) {
                GruposProveedorDTO registroDTO = converter.fromEntity(registro);
                logger.info("GrupoProveedor creado con éxito: {}", registroDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(registroDTO);
            } else {
                logger.error("Error al crear el GrupoProveedor. El servicio devolvió null.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de creación del GrupoProveedor.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GruposProveedorDTO> update(@PathVariable("id") int id,
            @RequestBody GruposProveedorDTO gruposDTO) {
        GrupoProveedor registro = service.update(converter.fromDTO(gruposDTO));
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        GruposProveedorDTO registroDTO = converter.fromEntity(registro);
        return ResponseEntity.ok(registroDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GruposProveedorDTO> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }

}

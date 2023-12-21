package com.provedores.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provedores.api.entity.GrupoProveedor;
import com.provedores.api.exceptions.GeneralServiceException;
import com.provedores.api.exceptions.NoDataFoundException;
import com.provedores.api.exceptions.ValidateServiceException;
import com.provedores.api.repository.GrupoProveedorRepository;
import com.provedores.api.service.GrupoProveedorService;
import com.provedores.api.utils.WrapperResponse;
import com.provedores.api.validator.GrupoProveedorValidator;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GrupoProveedorServiceImpl implements GrupoProveedorService {

    @Autowired
    private GrupoProveedorRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<GrupoProveedor> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<GrupoProveedor> findByGrupoDescripcion(String nombre, Pageable page) {
        try {
            return repository.findByGrupoDescripcionContaining(nombre, page);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GrupoProveedor findById(int id) {
        try {
            GrupoProveedor registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe el registro con ese ID"));
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public GrupoProveedor save(GrupoProveedor grupo) {
        try {
            GrupoProveedorValidator.save(grupo);
            if(repository.findByCodigoGrupo(grupo.getCodigoGrupo())!= null){
                String mensajeErrorcodigo = "Ya existe un registro con ese codigo "+grupo.getCodigoGrupo();
                throw new ValidateServiceException(mensajeErrorcodigo);
            }
            if(grupo.getCodigoGrupo().length()>10){
                String mensajeError = "Ya existe un registro con ese codigo "+grupo.getCodigoGrupo();
                throw new ValidateServiceException(mensajeError);
            }
            grupo.setActivo(true);
            GrupoProveedor registro = repository.save(grupo);
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public GrupoProveedor update(GrupoProveedor grupo) {
        try {
            GrupoProveedorValidator.save(grupo);
            GrupoProveedor registro = repository.findById(grupo.getId()).orElseThrow(() -> new NoDataFoundException("No existe el registro con ese ID"));
            registro.setGrupoDescripcion(grupo.getGrupoDescripcion());
            registro.setEmpresa(grupo.getEmpresa());
            registro.setCodigoGrupo(grupo.getCodigoGrupo());
            registro.setSucursal(grupo.getSucursal());
            repository.save(registro);
            return registro;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            GrupoProveedor registro = repository.findById(id).orElseThrow();
            repository.delete(registro);
        } catch (Exception e) {

        }
    }

}

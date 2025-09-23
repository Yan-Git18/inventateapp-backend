package com.cubasquispe.service.implementacion;

import org.springframework.stereotype.Service;

import com.cubasquispe.model.Detalle_Ingreso;
import com.cubasquispe.repository.IDetalleIngresoRepository;
import com.cubasquispe.repository.IGenericRepository;
import com.cubasquispe.service.IDetalleIngresoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public  class DetalleIngresoService extends GenericService<Detalle_Ingreso, Integer> implements IDetalleIngresoService {

    private final IDetalleIngresoRepository repo;

    @Override
    protected IGenericRepository<Detalle_Ingreso, Integer> getRepo() {
        return repo;
    }
 
    
}

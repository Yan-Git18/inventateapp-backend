package com.cubasquispe.service.implementacion;

import org.springframework.stereotype.Service;

import com.cubasquispe.model.Ingreso;
import com.cubasquispe.repository.IGenericRepository;
import com.cubasquispe.repository.IIngresoRepository;
import com.cubasquispe.service.IIngresoService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class IngresoService extends GenericService<Ingreso, Integer> implements IIngresoService {
    

    private final IIngresoRepository repo;

    @Override
    protected IGenericRepository<Ingreso, Integer> getRepo() {
        return repo;
    }
}

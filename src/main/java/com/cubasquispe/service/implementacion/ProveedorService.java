package com.cubasquispe.service.implementacion;

import org.springframework.stereotype.Service;

import com.cubasquispe.model.Proveedor;
import com.cubasquispe.repository.IGenericRepository;
import com.cubasquispe.repository.IProveedorRepository;
import com.cubasquispe.service.IProveedorService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProveedorService extends GenericService<Proveedor, Integer> implements IProveedorService{
    
    private final IProveedorRepository repo;

    @Override
    protected IGenericRepository<Proveedor, Integer> getRepo() {
        return repo;
    }
}

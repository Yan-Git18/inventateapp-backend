package com.cubasquispe.service.implementacion;

import org.springframework.stereotype.Service;

import com.cubasquispe.model.Producto;
import com.cubasquispe.repository.IGenericRepository;
import com.cubasquispe.repository.IProductoRepository;
import com.cubasquispe.service.IProductoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService extends GenericService<Producto, Integer> implements IProductoService{
    
    private final IProductoRepository repo;

    @Override
    protected IGenericRepository<Producto, Integer> getRepo() {
        return repo;
    }
}

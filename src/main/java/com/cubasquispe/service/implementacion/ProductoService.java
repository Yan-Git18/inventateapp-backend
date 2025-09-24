package com.cubasquispe.service.implementacion;

import com.cubasquispe.model.Producto;
import com.cubasquispe.repository.IGenericRepository;
import com.cubasquispe.repository.IProductoRepository;
import com.cubasquispe.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService extends GenericService<Producto, Integer> implements IProductoService {
    private final IProductoRepository repo;

    @Override
    protected IGenericRepository<Producto, Integer> getRepo(){
        return repo;
    }
}
package com.cubasquispe.service.implementacion;

import org.springframework.stereotype.Service;

import com.cubasquispe.model.Categoria;
import com.cubasquispe.repository.ICategoriaRepository;
import com.cubasquispe.repository.IGenericRepository;
import com.cubasquispe.service.ICategoriaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService extends GenericService<Categoria, Integer> implements ICategoriaService{
    
    private final ICategoriaRepository repo;

    @Override
    protected IGenericRepository<Categoria, Integer> getRepo() {
        return repo;
    }
}

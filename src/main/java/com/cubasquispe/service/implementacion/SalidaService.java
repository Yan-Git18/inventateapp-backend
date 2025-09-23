package com.cubasquispe.service.implementacion;

import org.springframework.stereotype.Service;

import com.cubasquispe.model.Salida;
import com.cubasquispe.repository.IGenericRepository;
import com.cubasquispe.repository.ISalidaRepository;
import com.cubasquispe.service.ISalidaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalidaService extends GenericService<Salida, Integer> implements ISalidaService {
    
    private final ISalidaRepository repo;

    @Override
    protected IGenericRepository<Salida, Integer> getRepo() {
        return repo;
    }
}

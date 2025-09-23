package com.cubasquispe.service.implementacion;

import org.springframework.stereotype.Service;

import com.cubasquispe.model.Detalle_Salida;
import com.cubasquispe.repository.IDetalleSalidaRepository;
import com.cubasquispe.repository.IGenericRepository;
import com.cubasquispe.service.IDetalleSalidaService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DetalleSalidaService extends GenericService<Detalle_Salida, Integer> implements IDetalleSalidaService{
    
    private final IDetalleSalidaRepository repo;

    @Override
    protected IGenericRepository<Detalle_Salida, Integer> getRepo() {
        return repo;
    }
}

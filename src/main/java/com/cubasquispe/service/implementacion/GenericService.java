package com.cubasquispe.service.implementacion;

import java.util.List;

import com.cubasquispe.exception.ModelNotFoundException;
import com.cubasquispe.repository.IGenericRepository;
import com.cubasquispe.service.IGenericService;


public abstract class GenericService<T, ID> implements IGenericService<T, ID> {
    protected abstract IGenericRepository<T, ID> getRepo();
    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(()-> new ModelNotFoundException("ID NO ENCONTRADO " + id));
        getRepo().deleteById(id); 
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()-> new ModelNotFoundException("ID NO ENCONTRADO " + id));
        //return getRepo().findById(id).orElse(null);
    }

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        getRepo().findById(id).orElseThrow(()-> new ModelNotFoundException("ID NO ENCONTRADO " + id));
        return getRepo().save(t);
    }
}

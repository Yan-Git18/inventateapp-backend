package com.cubasquispe.controller;

import com.cubasquispe.dto.CategoriaDTO;
import com.cubasquispe.model.Categoria;
import com.cubasquispe.service.ICategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final ICategoriaService service;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() throws Exception {

        List<CategoriaDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable("id") Integer id) throws Exception{
        CategoriaDTO obj = convertToDTO(service.findById(id));
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@Valid @RequestBody CategoriaDTO dto) throws Exception{
        Categoria obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId_categoria()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@Valid @RequestBody CategoriaDTO dto, @PathVariable("id") Integer id) throws Exception {
        Categoria obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(modelMapper.map(obj, CategoriaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();

    }


    private CategoriaDTO convertToDTO(Categoria obj){
        return modelMapper.map(obj, CategoriaDTO.class);
    }

    private Categoria convertToEntity(CategoriaDTO dto){
        return modelMapper.map(dto, Categoria.class);
    }
}
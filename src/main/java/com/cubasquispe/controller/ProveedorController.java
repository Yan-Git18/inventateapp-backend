package com.cubasquispe.controller;

import com.cubasquispe.dto.ProveedorDTO;
import com.cubasquispe.model.Proveedor;
import com.cubasquispe.service.IProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final IProveedorService service;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> findAll() throws Exception {

        List<ProveedorDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> findById(@PathVariable("id") Integer id) throws Exception{
        ProveedorDTO obj = convertToDTO(service.findById(id));
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> save(@Valid @RequestBody ProveedorDTO dto) throws Exception{
        Proveedor obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId_proveedor()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> update(@Valid @RequestBody ProveedorDTO dto, @PathVariable("id") Integer id) throws Exception {
        Proveedor obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(modelMapper.map(obj, ProveedorDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();

    }


    private ProveedorDTO convertToDTO(Proveedor obj){
        return modelMapper.map(obj, ProveedorDTO.class);
    }

    private Proveedor convertToEntity(ProveedorDTO dto){
        return modelMapper.map(dto, Proveedor.class);
    }
}
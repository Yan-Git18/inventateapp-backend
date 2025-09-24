package com.cubasquispe.controller;

import com.cubasquispe.assembler.ProveedorAssembler;
import com.cubasquispe.dto.ProveedorDTO;
import com.cubasquispe.model.Proveedor;
import com.cubasquispe.service.IProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
    private final ProveedorAssembler assembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProveedorDTO>>> findAll() throws Exception {
        List<Proveedor> list = service.findAll();

        List<EntityModel<ProveedorDTO>> resources = list.stream()
                .map(proveedor -> {
                    try {
                        return assembler.toModel(proveedor);
                    } catch (Exception e) {
                        throw new RuntimeException("Error al convertir Proveedor a EntityModel<ProveedorDTO>", e);
                    }
                })
                .toList();

        return ResponseEntity.ok(CollectionModel.of(resources));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProveedorDTO>> findById(@PathVariable("id") Integer id) throws Exception {
        Proveedor proveedor = service.findById(id);
        EntityModel<ProveedorDTO> resource = assembler.toModel(proveedor);
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ProveedorDTO dto) throws Exception {
        Proveedor obj = service.save(convertToEntity(dto, null));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId_proveedor())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ProveedorDTO>> update(@PathVariable("id") Integer id,
    @Valid @RequestBody ProveedorDTO dto) throws Exception {
        Proveedor updated = service.update(convertToEntity(dto, id), id);
        EntityModel<ProveedorDTO> proveedorResource = assembler.toModel(updated);
        return ResponseEntity.ok(proveedorResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    private Proveedor convertToEntity(ProveedorDTO dto, Integer id) {
        Proveedor proveedor = new Proveedor();
        
        proveedor.setId_proveedor(id);

        proveedor.setNombre(dto.getNombre());
        proveedor.setRuc(dto.getRuc());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setCorreo(dto.getCorreo());

        return proveedor;
    }
}

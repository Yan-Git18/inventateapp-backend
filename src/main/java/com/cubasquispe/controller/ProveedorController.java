package com.cubasquispe.controller;

import com.cubasquispe.decorator.ProveedorDecorator;
import com.cubasquispe.dto.ProveedorDTO;
import com.cubasquispe.exception.CustomSuccessRecord;
import com.cubasquispe.factory.ProveedorFactory;
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
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proveedores")
public class ProveedorController {

    private final IProveedorService service;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProveedorDTO>>> findAll() throws Exception {
        List<Proveedor> list = service.findAll();

        List<EntityModel<ProveedorDTO>> proveedoresResource = list.stream()
                .map(ProveedorFactory::convertToDTO)                         
                .map(dto -> new ProveedorDecorator(dto).withLinks())        
                .toList();

        return ResponseEntity.ok(CollectionModel.of(proveedoresResource));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProveedorDTO>> findById(@PathVariable("id") Integer id) throws Exception {
        Proveedor proveedor = service.findById(id);

        ProveedorDTO dto = ProveedorFactory.convertToDTO(proveedor);
        EntityModel<ProveedorDTO> proveedorResource = new ProveedorDecorator(dto).withLinks();

        return ResponseEntity.ok(proveedorResource);
    }

    @PostMapping
    public ResponseEntity<CustomSuccessRecord> save(@Valid @RequestBody ProveedorDTO dto) throws Exception {
        Proveedor obj = service.save(ProveedorFactory.convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId_proveedor())
                .toUri();
        
        CustomSuccessRecord success = new CustomSuccessRecord(
            LocalDateTime.now(),
            "Proveedor creado con éxito",
            "ID generado: " + obj.getId_proveedor()
        );

        return ResponseEntity.created(location).body(success);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomSuccessRecord> update(@PathVariable("id") Integer id,
                                                            @Valid @RequestBody ProveedorDTO dto) throws Exception {
        Proveedor updated = service.update(ProveedorFactory.convertToEntity(dto), id);

        ProveedorDTO updatedDTO = ProveedorFactory.convertToDTO(updated);
        EntityModel<ProveedorDTO> proveedorResource = new ProveedorDecorator(updatedDTO).withLinks();
                    
        CustomSuccessRecord success = new CustomSuccessRecord(
            LocalDateTime.now(),
            "Proveedor actualizado con éxito",
            proveedorResource.toString()
        );

        return ResponseEntity.ok(success);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomSuccessRecord> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        CustomSuccessRecord success = new CustomSuccessRecord(
            LocalDateTime.now(),
            "Proveedor eliminado con éxito",
            "Proveedor con ID: " + id + "eliminado correctamente"
        );
        return ResponseEntity.ok(success);
        //return ResponseEntity.noContent().build();
    }
}

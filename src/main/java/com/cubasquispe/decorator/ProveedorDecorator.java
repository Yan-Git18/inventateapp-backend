package com.cubasquispe.decorator;

import org.springframework.hateoas.EntityModel;

import com.cubasquispe.controller.ProveedorController;
import com.cubasquispe.dto.ProveedorDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class ProveedorDecorator {
    
    private final ProveedorDTO proveedor;

    public ProveedorDecorator(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public EntityModel<ProveedorDTO> withLinks() {
        try {
            return EntityModel.of(proveedor,
                    linkTo(methodOn(ProveedorController.class).findById(proveedor.getIdProveedor())).withSelfRel(),
                    linkTo(methodOn(ProveedorController.class).findAll()).withRel("proveedores")
                    //linkTo(methodOn(ProveedorController.class).update(proveedor.getIdProveedor(), null)).withRel("update"),
                    //linkTo(methodOn(ProveedorController.class).delete(proveedor.getIdProveedor())).withRel("delete")
            );
        } catch (Exception e) {
            throw new RuntimeException("Error generando enlaces para el producto", e);
        }
    }
}

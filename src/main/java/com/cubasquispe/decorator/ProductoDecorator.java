package com.cubasquispe.decorator;

import com.cubasquispe.controller.CategoriaController;
import com.cubasquispe.controller.ProductoController;
import com.cubasquispe.controller.ProveedorController;
import com.cubasquispe.dto.ProductoDTO;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class ProductoDecorator {
    private final ProductoDTO dto;

    public ProductoDecorator(ProductoDTO dto) {
        this.dto = dto;
    }

    public EntityModel<ProductoDTO> withLinks() throws Exception {
        EntityModel<ProductoDTO> model = EntityModel.of(dto);

        model.add(linkTo(methodOn(ProductoController.class).findById(dto.getIdProducto())).withSelfRel());
        model.add(linkTo(methodOn(ProductoController.class).findAll()).withRel("productos"));
        model.add(linkTo(methodOn(ProductoController.class).update(dto, dto.getIdProducto())).withRel("update"));
        model.add(linkTo(methodOn(ProductoController.class).delete(dto.getIdProducto())).withRel("delete"));
        model.add(linkTo(methodOn(CategoriaController.class).findById(dto.getIdCategoria())).withRel("categoria"));
        model.add(linkTo(methodOn(ProveedorController.class).findById(dto.getIdProveedor())).withRel("proveedor"));

        return model;
    }
}
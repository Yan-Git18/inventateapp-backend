package com.cubasquispe.assembler;

import com.cubasquispe.controller.ProductoController;
import com.cubasquispe.controller.ProveedorController;
import com.cubasquispe.dto.ProveedorDTO;
import com.cubasquispe.model.Proveedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProveedorAssembler {

  public EntityModel<ProveedorDTO> toModel(Proveedor proveedor) throws Exception {
    ProveedorDTO dto = new ProveedorDTO(
        proveedor.getId_proveedor(),
        proveedor.getNombre(),
        proveedor.getRuc(),
        proveedor.getDireccion(),
        proveedor.getTelefono(),
        proveedor.getCorreo());

    EntityModel<ProveedorDTO> resource = EntityModel.of(dto);

    Link selfLink = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(ProveedorController.class)
            .findById(proveedor.getId_proveedor()))
        .withSelfRel();

    Link updateLink = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(ProveedorController.class)
            .update(proveedor.getId_proveedor(), null))
        .withRel("update");

    Link deleteLink = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(ProveedorController.class)
            .delete(proveedor.getId_proveedor()))
        .withRel("delete");

    resource.add(selfLink, updateLink, deleteLink);

    return resource;
  }
}

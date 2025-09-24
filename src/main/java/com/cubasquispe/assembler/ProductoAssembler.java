package com.cubasquispe.assembler;

import com.cubasquispe.dto.ProductoDTO;
import com.cubasquispe.model.Producto;
import com.cubasquispe.controller.ProductoController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProductoAssembler {

  public EntityModel<ProductoDTO> toModel(Producto producto) throws Exception {
    ProductoDTO dto = new ProductoDTO(
        producto.getId_producto(),
        producto.getNombre(),
        producto.getDescripcion(),
        producto.getCodigo_barra(),
        producto.getPrecio_compra(),
        producto.getPrecio_venta(),
        producto.getStock_actual(),
        producto.getStock_minimo(),
        producto.getEstado(),
        producto.getProveedor().getId_proveedor(),
        producto.getCategoria().getId_categoria());

    EntityModel<ProductoDTO> resource = EntityModel.of(dto);

    Link selfLink = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(ProductoController.class)
          .findById(producto.getId_producto())).withSelfRel();

    Link updateLink = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(ProductoController.class)
          .update(producto.getId_producto(), null)).withRel("update");

    Link deleteLink = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(ProductoController.class)
          .delete(producto.getId_producto())).withRel("delete");

    resource.add(selfLink, updateLink, deleteLink);

    return resource;
  }
}

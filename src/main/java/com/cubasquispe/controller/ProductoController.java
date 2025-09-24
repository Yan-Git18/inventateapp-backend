package com.cubasquispe.controller;

import com.cubasquispe.assembler.ProductoAssembler;
import com.cubasquispe.dto.ProductoDTO;
import com.cubasquispe.model.Categoria;
import com.cubasquispe.model.Producto;
import com.cubasquispe.model.Proveedor;
import com.cubasquispe.service.IProductoService;

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
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService service;
    private final ProductoAssembler assembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProductoDTO>>> findAll() throws Exception {
        List<Producto> list = service.findAll();
        List<EntityModel<ProductoDTO>> productosResource = list.stream()
                .map(producto -> {
                    try {
                        return assembler.toModel(producto);
                    } catch (Exception e) {
                        throw new RuntimeException("No fue posible transformar la entidad Producto al formato DTO", e);
                    }
                })
                .toList();
        return ResponseEntity.ok(CollectionModel.of(productosResource));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProductoDTO>> findById(@PathVariable("id") Integer id) throws Exception {
        Producto producto = service.findById(id);
        EntityModel<ProductoDTO> productoResource = assembler.toModel(producto);
        return ResponseEntity.ok(productoResource);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ProductoDTO dto) throws Exception {
        Producto obj = service.save(convertToEntity(dto, null));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId_producto())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ProductoDTO>> update(@PathVariable("id") Integer id,
            @Valid @RequestBody ProductoDTO dto) throws Exception {
        Producto updated = service.update(convertToEntity(dto, id), id);
        EntityModel<ProductoDTO> productoResource = assembler.toModel(updated);
        return ResponseEntity.ok(productoResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Conversión de DTO a Entidad (agrega proveedor y categoría por id)
    private Producto convertToEntity(ProductoDTO dto, Integer id) {
        Producto producto = new Producto();

        producto.setId_producto(id);

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCodigo_barra(dto.getCodigo_barra());
        producto.setPrecio_compra(dto.getPrecio_compra());
        producto.setPrecio_venta(dto.getPrecio_venta());
        producto.setStock_actual(dto.getStock_actual());
        producto.setStock_minimo(dto.getStock_minimo());
        producto.setEstado(dto.getEstado());

        // Proveedor
        Proveedor proveedor = new Proveedor();
        proveedor.setId_proveedor(dto.getIdProveedor());
        producto.setProveedor(proveedor);

        // Categoria
        Categoria categoria = new Categoria();
        categoria.setId_categoria(dto.getIdCategoria());
        producto.setCategoria(categoria);

        return producto;
    }

}

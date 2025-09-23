package com.cubasquispe.controller;

import com.cubasquispe.dto.ProductoDTO;
import com.cubasquispe.model.Categoria;
import com.cubasquispe.model.Producto;
import com.cubasquispe.model.Proveedor;
import com.cubasquispe.service.IProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final IProductoService service;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll() throws Exception {

        List<ProductoDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable("id") Integer id) throws Exception{
        ProductoDTO obj = convertToDTO(service.findById(id));
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> save(@Valid @RequestBody ProductoDTO dto) throws Exception{
        Producto obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId_producto()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@Valid @RequestBody ProductoDTO dto, @PathVariable("id") Integer id) throws Exception {
        Producto obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(modelMapper.map(obj, ProductoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();

    }


    private ProductoDTO convertToDTO(Producto obj){
        return modelMapper.map(obj, ProductoDTO.class);
    }

    private Producto convertToEntity(ProductoDTO dto){
        Producto producto = modelMapper.map(dto, Producto.class);

        // Crear "proxies" de entidades solo con el ID
        Proveedor proveedor = new Proveedor();
        proveedor.setId_proveedor(dto.getIdProveedor());

        Categoria categoria = new Categoria();
        categoria.setId_categoria(dto.getIdCategoria());

        // Asignarlos al producto
        producto.setProveedor(proveedor);
        producto.setCategoria(categoria);

        return producto;
    }
}
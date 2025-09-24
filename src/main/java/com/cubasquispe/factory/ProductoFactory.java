package com.cubasquispe.factory;

import com.cubasquispe.dto.ProductoDTO;
import com.cubasquispe.model.Categoria;
import com.cubasquispe.model.Producto;
import com.cubasquispe.model.Proveedor;

public class ProductoFactory {

    public static ProductoDTO createDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getId_producto());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCodigo_barra(producto.getCodigo_barra());
        dto.setPrecio_compra(producto.getPrecio_compra());
        dto.setPrecio_venta(producto.getPrecio_venta());
        dto.setStock_actual(producto.getStock_actual());
        dto.setStock_minimo(producto.getStock_minimo());
        dto.setEstado(producto.getEstado());

        dto.setIdProveedor(producto.getProveedor() != null ? producto.getProveedor().getId_proveedor() : null);
        dto.setIdCategoria(producto.getCategoria() != null ? producto.getCategoria().getId_categoria() : null);

        return dto;
    }

    public static Producto createEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId_producto(dto.getIdProducto());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCodigo_barra(dto.getCodigo_barra());
        producto.setPrecio_compra(dto.getPrecio_compra());
        producto.setPrecio_venta(dto.getPrecio_venta());
        producto.setStock_actual(dto.getStock_actual());
        producto.setStock_minimo(dto.getStock_minimo());
        producto.setEstado(dto.getEstado());

        Proveedor proveedor = new Proveedor();
        proveedor.setId_proveedor(dto.getIdProveedor());

        Categoria categoria = new Categoria();
        categoria.setId_categoria(dto.getIdCategoria());

        producto.setProveedor(proveedor);
        producto.setCategoria(categoria);

        return producto;
    }
}
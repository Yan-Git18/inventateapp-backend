package com.cubasquispe.factory;

import com.cubasquispe.dto.ProveedorDTO;
import com.cubasquispe.model.Proveedor;

public class ProveedorFactory {

    public static Proveedor convertToEntity(ProveedorDTO dto) {
        Proveedor proveedor = new Proveedor();

        proveedor.setId_proveedor(dto.getIdProveedor());

        proveedor.setNombre(dto.getNombre());
        proveedor.setRuc(dto.getRuc());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setCorreo(dto.getCorreo());

        return proveedor;
    }

    public static ProveedorDTO convertToDTO(Proveedor proveedor) {
        ProveedorDTO dto = new ProveedorDTO();

        dto.setIdProveedor(proveedor.getId_proveedor());
        dto.setNombre(proveedor.getNombre());
        dto.setRuc(proveedor.getRuc());
        dto.setDireccion(proveedor.getDireccion());
        dto.setTelefono(proveedor.getTelefono());
        dto.setCorreo(proveedor.getCorreo());

        return dto;
    }

}

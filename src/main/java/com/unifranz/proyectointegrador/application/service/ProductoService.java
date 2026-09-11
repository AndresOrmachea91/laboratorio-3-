package com.unifranz.proyectointegrador.application.service;

import com.unifranz.proyectointegrador.application.dto.ProductoDto;

import java.util.List;

public interface ProductoService {
    ProductoDto guardar(ProductoDto productoDto);
    List<ProductoDto> listar();
    ProductoDto buscarPorId(Long id);
    List<ProductoDto> filtrar(String nombre, String categoria, String marca,
                              Double precioMin, Double precioMax, Boolean conStock);
    ProductoDto actualizar(Long id, ProductoDto productoDto);
    void eliminar(Long id);
}

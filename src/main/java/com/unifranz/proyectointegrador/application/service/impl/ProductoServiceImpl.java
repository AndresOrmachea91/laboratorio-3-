package com.unifranz.proyectointegrador.application.service.impl;

import com.unifranz.proyectointegrador.application.dto.ProductoDto;
import com.unifranz.proyectointegrador.application.service.ProductoService;
import com.unifranz.proyectointegrador.domain.Producto;
import com.unifranz.proyectointegrador.infrastructure.persistence.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    private ProductoDto aDto(Producto p) {
        return new ProductoDto(p.getId(), p.getNombre(), p.getMarca(),
                p.getCategoria(), p.getPrecio(), p.getStock());
    }

    @Override
    public ProductoDto guardar(ProductoDto productoDto) {
        Producto producto = new Producto();
        producto.setNombre(productoDto.getNombre());
        producto.setMarca(productoDto.getMarca());
        producto.setCategoria(productoDto.getCategoria());
        producto.setPrecio(productoDto.getPrecio());
        producto.setStock(productoDto.getStock());
        return aDto(productoRepository.save(producto));
    }

    @Override
    public List<ProductoDto> listar() {
        return productoRepository.findAll()
                .stream()
                .map(this::aDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .map(this::aDto)
                .orElse(null);
    }

    @Override
    public List<ProductoDto> filtrar(String nombre, String categoria, String marca,
                                     Double precioMin, Double precioMax, Boolean conStock) {
        return productoRepository.findAll()
                .stream()
                .filter(p -> nombre == null || p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(p -> categoria == null || p.getCategoria().equalsIgnoreCase(categoria))
                .filter(p -> marca == null || p.getMarca().equalsIgnoreCase(marca))
                .filter(p -> precioMin == null || p.getPrecio() >= precioMin)
                .filter(p -> precioMax == null || p.getPrecio() <= precioMax)
                .filter(p -> conStock == null || !conStock || p.getStock() > 0)
                .map(this::aDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDto actualizar(Long id, ProductoDto productoDto) {
        return productoRepository.findById(id).map(p -> {
            p.setNombre(productoDto.getNombre());
            p.setMarca(productoDto.getMarca());
            p.setCategoria(productoDto.getCategoria());
            p.setPrecio(productoDto.getPrecio());
            p.setStock(productoDto.getStock());
            return aDto(productoRepository.save(p));
        }).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}

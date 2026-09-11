package com.unifranz.proyectointegrador.infrastructure.web.controller;

import com.unifranz.proyectointegrador.application.dto.ProductoDto;
import com.unifranz.proyectointegrador.application.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDto> guardarProducto(@RequestBody ProductoDto productoDto) {
        return ResponseEntity.ok(productoService.guardar(productoDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> listarProductos() {
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> buscarPorId(@PathVariable Long id) {
        ProductoDto producto = productoService.buscarPorId(id);
        return producto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(producto);
    }

    // Ejemplo: /productos/filtrar?categoria=laptop&marca=Asus&precioMin=500&precioMax=2000&conStock=true
    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoDto>> filtrarProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) Boolean conStock) {
        return ResponseEntity.ok(productoService.filtrar(nombre, categoria, marca, precioMin, precioMax, conStock));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDto productoDto) {
        ProductoDto producto = productoService.actualizar(id, productoDto);
        return producto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

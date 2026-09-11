package com.unifranz.proyectointegrador.infrastructure.persistence;

import com.unifranz.proyectointegrador.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriaIgnoreCase(String categoria);
    List<Producto> findByMarcaIgnoreCase(String marca);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByPrecioBetween(Double min, Double max);
    List<Producto> findByStockGreaterThan(Integer stock);
}

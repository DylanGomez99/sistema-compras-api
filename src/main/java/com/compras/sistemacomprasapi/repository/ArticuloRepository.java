package com.compras.sistemacomprasapi.repository;

import com.compras.sistemacomprasapi.entity.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
}
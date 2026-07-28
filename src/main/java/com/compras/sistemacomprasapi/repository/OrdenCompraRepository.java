package com.compras.sistemacomprasapi.repository;

import com.compras.sistemacomprasapi.entity.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

    @Query("SELECT o FROM OrdenCompra o WHERE " +
           "(:criterio IS NULL OR LOWER(o.numeroOrden) LIKE LOWER(CONCAT('%', :criterio, '%'))) OR " +
           "(:criterio IS NULL OR LOWER(o.proveedor.nombre) LIKE LOWER(CONCAT('%', :criterio, '%')))")
    List<OrdenCompra> buscarPorCriterio(@Param("criterio") String criterio);
}

package com.compras.sistemacomprasapi.service;

import com.compras.sistemacomprasapi.entity.Articulo;
import com.compras.sistemacomprasapi.entity.OrdenCompra;
import com.compras.sistemacomprasapi.repository.ArticuloRepository;
import com.compras.sistemacomprasapi.repository.OrdenCompraRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;
    private final ArticuloRepository articuloRepository;
    private final ContabilidadService contabilidadService;

    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository,
                              ArticuloRepository articuloRepository,
                              ContabilidadService contabilidadService) {
        this.ordenCompraRepository = ordenCompraRepository;
        this.articuloRepository = articuloRepository;
        this.contabilidadService = contabilidadService;
    }

    public List<OrdenCompra> listarTodos() {
        return ordenCompraRepository.findAll();
    }

    public OrdenCompra guardar(OrdenCompra ordenCompra) {
        // 🔹 Validaciones contra valores inválidos
        if (ordenCompra.getCantidad() == null || ordenCompra.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }

        if (ordenCompra.getCostoUnitario() == null || ordenCompra.getCostoUnitario() <= 0) {
            throw new IllegalArgumentException("El costo unitario debe ser mayor que cero");
        }

        // 🔹 Actualizar existencia del artículo
        if (ordenCompra.getArticulo() != null && ordenCompra.getArticulo().getId() != null) {
            Articulo articulo = articuloRepository.findById(ordenCompra.getArticulo().getId()).orElse(null);
            if (articulo != null) {
                articulo.setExistencia(articulo.getExistencia() + ordenCompra.getCantidad());
                articuloRepository.save(articulo);
                ordenCompra.setArticulo(articulo);
            }
        }

        // 🔹 Guardar orden de compra
        OrdenCompra guardada = ordenCompraRepository.save(ordenCompra);

        // 🔹 Registrar asiento contable
        BigDecimal monto = BigDecimal.valueOf(guardada.getCantidad())
                .multiply(BigDecimal.valueOf(guardada.getCostoUnitario()));

        contabilidadService.registrarAsientoPorOrdenCompra(guardada.getNumeroOrden(), monto);

        return guardada;
    }

    public OrdenCompra buscarPorId(Long id) {
        return ordenCompraRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        ordenCompraRepository.deleteById(id);
    }

    // 🔹 Búsqueda de órdenes de compra
    public List<OrdenCompra> buscarPorCriterio(String criterio) {
    return ordenCompraRepository.buscarPorCriterio(criterio);
}

}

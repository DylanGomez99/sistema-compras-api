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
        if (ordenCompra.getArticulo() != null && ordenCompra.getArticulo().getId() != null) {
            Articulo articulo = articuloRepository.findById(ordenCompra.getArticulo().getId()).orElse(null);
            if (articulo != null && ordenCompra.getCantidad() != null) {
                articulo.setExistencia(articulo.getExistencia() + ordenCompra.getCantidad());
                articuloRepository.save(articulo);
                ordenCompra.setArticulo(articulo);
            }
        }

        OrdenCompra guardada = ordenCompraRepository.save(ordenCompra);

        if (guardada.getCantidad() != null && guardada.getCostoUnitario() != null) {
            BigDecimal monto = BigDecimal.valueOf(guardada.getCantidad())
                    .multiply(BigDecimal.valueOf(guardada.getCostoUnitario()));

            contabilidadService.registrarAsientoPorOrdenCompra(guardada.getNumeroOrden(), monto);
        }

        return guardada;
    }

    public OrdenCompra buscarPorId(Long id) {
        return ordenCompraRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        ordenCompraRepository.deleteById(id);
    }

    public List<OrdenCompra> buscarPorCriterio(String criterio) {
        return ordenCompraRepository.buscarPorCriterio(criterio);
    }
}
package com.compras.sistemacomprasapi.service;

import com.compras.sistemacomprasapi.entity.Articulo;
import com.compras.sistemacomprasapi.entity.OrdenCompra;
import com.compras.sistemacomprasapi.repository.ArticuloRepository;
import com.compras.sistemacomprasapi.repository.OrdenCompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;
    private final ArticuloRepository articuloRepository;

    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository,
                              ArticuloRepository articuloRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
        this.articuloRepository = articuloRepository;
    }

    public List<OrdenCompra> listarTodos() {
        return ordenCompraRepository.findAll();
    }

    public OrdenCompra guardar(OrdenCompra ordenCompra) {

        if (ordenCompra.getArticulo() != null && ordenCompra.getArticulo().getId() != null) {

            Articulo articulo = articuloRepository
                    .findById(ordenCompra.getArticulo().getId())
                    .orElse(null);

            if (articulo != null && ordenCompra.getCantidad() != null) {
                Integer existenciaActual = articulo.getExistencia();
                Integer cantidadComprada = ordenCompra.getCantidad();

                articulo.setExistencia(existenciaActual + cantidadComprada);

                articuloRepository.save(articulo);

                ordenCompra.setArticulo(articulo);
            }
        }

        return ordenCompraRepository.save(ordenCompra);
    }

    public OrdenCompra buscarPorId(Long id) {
        return ordenCompraRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        ordenCompraRepository.deleteById(id);
    }
}
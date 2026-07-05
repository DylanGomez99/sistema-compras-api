package com.compras.sistemacomprasapi.service;

import com.compras.sistemacomprasapi.entity.OrdenCompra;
import com.compras.sistemacomprasapi.repository.OrdenCompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;

    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
    }

    public List<OrdenCompra> listarTodos() {
        return ordenCompraRepository.findAll();
    }

    public OrdenCompra guardar(OrdenCompra ordenCompra) {
        return ordenCompraRepository.save(ordenCompra);
    }

    public OrdenCompra buscarPorId(Long id) {
        return ordenCompraRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        ordenCompraRepository.deleteById(id);
    }
}

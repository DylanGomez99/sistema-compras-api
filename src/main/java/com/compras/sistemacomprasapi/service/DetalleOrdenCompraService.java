package com.compras.sistemacomprasapi.service;

import com.compras.sistemacomprasapi.entity.DetalleOrdenCompra;
import com.compras.sistemacomprasapi.repository.DetalleOrdenCompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleOrdenCompraService {

    private final DetalleOrdenCompraRepository detalleOrdenCompraRepository;

    public DetalleOrdenCompraService(DetalleOrdenCompraRepository detalleOrdenCompraRepository) {
        this.detalleOrdenCompraRepository = detalleOrdenCompraRepository;
    }

    public List<DetalleOrdenCompra> listarTodos() {
        return detalleOrdenCompraRepository.findAll();
    }

    public DetalleOrdenCompra guardar(DetalleOrdenCompra detalleOrdenCompra) {
        return detalleOrdenCompraRepository.save(detalleOrdenCompra);
    }

    public DetalleOrdenCompra buscarPorId(Long id) {
        return detalleOrdenCompraRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        detalleOrdenCompraRepository.deleteById(id);
    }
}
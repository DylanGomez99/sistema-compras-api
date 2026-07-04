package com.compras.sistemacomprasapi.service;

import com.compras.sistemacomprasapi.entity.UnidadMedida;
import com.compras.sistemacomprasapi.repository.UnidadMedidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaService {

    private final UnidadMedidaRepository unidadMedidaRepository;

    public UnidadMedidaService(UnidadMedidaRepository unidadMedidaRepository) {
        this.unidadMedidaRepository = unidadMedidaRepository;
    }

    public List<UnidadMedida> listarTodos() {
        return unidadMedidaRepository.findAll();
    }

    public UnidadMedida guardar(UnidadMedida unidadMedida) {
        return unidadMedidaRepository.save(unidadMedida);
    }

    public UnidadMedida buscarPorId(Long id) {
        return unidadMedidaRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        unidadMedidaRepository.deleteById(id);
    }
}
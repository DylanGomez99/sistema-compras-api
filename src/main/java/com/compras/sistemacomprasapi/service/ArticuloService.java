package com.compras.sistemacomprasapi.service;

import com.compras.sistemacomprasapi.entity.Articulo;
import com.compras.sistemacomprasapi.repository.ArticuloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public List<Articulo> listarTodos() {
        return articuloRepository.findAll();
    }

    public Articulo guardar(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    public Articulo buscarPorId(Long id) {
        return articuloRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        articuloRepository.deleteById(id);
    }
}
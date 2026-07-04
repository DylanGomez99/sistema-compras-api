package com.compras.sistemacomprasapi.service;

import com.compras.sistemacomprasapi.entity.Departamento;
import com.compras.sistemacomprasapi.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public List<Departamento> listarTodos() {
        return departamentoRepository.findAll();
    }

    public Departamento guardar(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public void eliminar(Long id) {
        departamentoRepository.deleteById(id);
    }

    public Departamento buscarPorId(Long id) {
        return departamentoRepository.findById(id).orElse(null);
    }
}
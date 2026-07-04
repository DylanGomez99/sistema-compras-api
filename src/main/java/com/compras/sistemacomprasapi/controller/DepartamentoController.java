package com.compras.sistemacomprasapi.controller;

import com.compras.sistemacomprasapi.entity.Departamento;
import com.compras.sistemacomprasapi.service.DepartamentoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("departamentos", departamentoService.listarTodos());
        model.addAttribute("departamento", new Departamento());
        return "Departamento";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Departamento departamento) {
        departamentoService.guardar(departamento);
        return "redirect:/departamento";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
        return "redirect:/departamento";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("departamento", departamentoService.buscarPorId(id));
        model.addAttribute("departamentos", departamentoService.listarTodos());
        return "Departamento";
    }
}

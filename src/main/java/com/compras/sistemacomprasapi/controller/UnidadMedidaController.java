package com.compras.sistemacomprasapi.controller;

import com.compras.sistemacomprasapi.entity.UnidadMedida;
import com.compras.sistemacomprasapi.service.UnidadMedidaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/unidadmedida")
public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

    public UnidadMedidaController(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("unidadMedidas", unidadMedidaService.listarTodos());
        model.addAttribute("unidadMedida", new UnidadMedida());
        return "UnidadMedida";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute UnidadMedida unidadMedida) {
        unidadMedidaService.guardar(unidadMedida);
        return "redirect:/unidadmedida";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("unidadMedida", unidadMedidaService.buscarPorId(id));
        model.addAttribute("unidadMedidas", unidadMedidaService.listarTodos());
        return "UnidadMedida";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        unidadMedidaService.eliminar(id);
        return "redirect:/unidadmedida";
    }
}
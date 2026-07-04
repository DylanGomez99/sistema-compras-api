package com.compras.sistemacomprasapi.controller;

import com.compras.sistemacomprasapi.entity.Articulo;
import com.compras.sistemacomprasapi.service.ArticuloService;
import com.compras.sistemacomprasapi.service.UnidadMedidaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articulo")
public class ArticuloController {

    private final ArticuloService articuloService;
    private final UnidadMedidaService unidadMedidaService;

    public ArticuloController(ArticuloService articuloService, UnidadMedidaService unidadMedidaService) {
        this.articuloService = articuloService;
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("articulos", articuloService.listarTodos());
        model.addAttribute("articulo", new Articulo());
        model.addAttribute("unidades", unidadMedidaService.listarTodos());
        return "Articulo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Articulo articulo) {
        articuloService.guardar(articulo);
        return "redirect:/articulo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("articulo", articuloService.buscarPorId(id));
        model.addAttribute("articulos", articuloService.listarTodos());
        model.addAttribute("unidades", unidadMedidaService.listarTodos());
        return "Articulo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        articuloService.eliminar(id);
        return "redirect:/articulo";
    }
}
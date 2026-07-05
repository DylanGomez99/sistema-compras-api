package com.compras.sistemacomprasapi.controller;

import com.compras.sistemacomprasapi.entity.OrdenCompra;
import com.compras.sistemacomprasapi.service.ArticuloService;
import com.compras.sistemacomprasapi.service.OrdenCompraService;
import com.compras.sistemacomprasapi.service.ProveedorService;
import com.compras.sistemacomprasapi.service.UnidadMedidaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/ordencompra")
public class OrdenCompraController {

    private final OrdenCompraService ordenCompraService;
    private final ProveedorService proveedorService;
    private final ArticuloService articuloService;
    private final UnidadMedidaService unidadMedidaService;

    public OrdenCompraController(
            OrdenCompraService ordenCompraService,
            ProveedorService proveedorService,
            ArticuloService articuloService,
            UnidadMedidaService unidadMedidaService
    ) {
        this.ordenCompraService = ordenCompraService;
        this.proveedorService = proveedorService;
        this.articuloService = articuloService;
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping
    public String listar(Model model) {
        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setFechaOrden(LocalDate.now());
        ordenCompra.setEstado(true);

        model.addAttribute("ordenCompra", ordenCompra);
        model.addAttribute("ordenes", ordenCompraService.listarTodos());
        model.addAttribute("proveedores", proveedorService.listarTodos());
        model.addAttribute("articulos", articuloService.listarTodos());
        model.addAttribute("unidades", unidadMedidaService.listarTodos());

        return "OrdenCompra";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute OrdenCompra ordenCompra) {
        ordenCompraService.guardar(ordenCompra);
        return "redirect:/ordencompra";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("ordenCompra", ordenCompraService.buscarPorId(id));
        model.addAttribute("ordenes", ordenCompraService.listarTodos());
        model.addAttribute("proveedores", proveedorService.listarTodos());
        model.addAttribute("articulos", articuloService.listarTodos());
        model.addAttribute("unidades", unidadMedidaService.listarTodos());

        return "OrdenCompra";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        ordenCompraService.eliminar(id);
        return "redirect:/ordencompra";
    }
}
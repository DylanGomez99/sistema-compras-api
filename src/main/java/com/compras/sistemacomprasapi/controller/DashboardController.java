package com.compras.sistemacomprasapi.controller;

import com.compras.sistemacomprasapi.service.ArticuloService;
import com.compras.sistemacomprasapi.service.DepartamentoService;
import com.compras.sistemacomprasapi.service.OrdenCompraService;
import com.compras.sistemacomprasapi.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DepartamentoService departamentoService;
    private final ProveedorService proveedorService;
    private final ArticuloService articuloService;
    private final OrdenCompraService ordenCompraService;

    public DashboardController(DepartamentoService departamentoService,
                                ProveedorService proveedorService,
                                ArticuloService articuloService,
                                OrdenCompraService ordenCompraService) {
        this.departamentoService = departamentoService;
        this.proveedorService = proveedorService;
        this.articuloService = articuloService;
        this.ordenCompraService = ordenCompraService;
    }

    @GetMapping("/")
    public String mostrarDashboard(Model model) {
        int totalDepartamentos = departamentoService.listarTodos().size();
        int totalProveedores = proveedorService.listarTodos().size();
        int totalArticulos = articuloService.listarTodos().size();
        int totalOrdenes = ordenCompraService.listarTodos().size();

        model.addAttribute("totalDepartamentos", totalDepartamentos);
        model.addAttribute("totalProveedores", totalProveedores);
        model.addAttribute("totalArticulos", totalArticulos);
        model.addAttribute("totalOrdenes", totalOrdenes);

        return "Dashboard";
    }
}
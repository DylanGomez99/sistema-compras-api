package com.compras.sistemacomprasapi.controller;

import com.compras.sistemacomprasapi.entity.Proveedor;
import com.compras.sistemacomprasapi.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores", proveedorService.listarTodos());
        model.addAttribute("proveedor", new Proveedor());
        return "Proveedor";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("proveedor") Proveedor proveedor, 
                          BindingResult result, 
                          Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("proveedores", proveedorService.listarTodos());
            return "Proveedor";
        }

        proveedorService.guardar(proveedor);
        return "redirect:/proveedor";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("proveedor", proveedorService.buscarPorId(id));
        model.addAttribute("proveedores", proveedorService.listarTodos());
        return "Proveedor";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return "redirect:/proveedor";
    }
}

package com.compras.sistemacomprasapi.controller;

import com.compras.sistemacomprasapi.entity.OrdenCompra;
import com.compras.sistemacomprasapi.service.ArticuloService;
import com.compras.sistemacomprasapi.service.OrdenCompraService;
import com.compras.sistemacomprasapi.service.ProveedorService;
import com.compras.sistemacomprasapi.service.UnidadMedidaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
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

    @GetMapping("/pdf/{id}")
    public void generarPDF(@PathVariable Long id,
                           HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=OrdenCompra_" + id + ".pdf");

        OrdenCompra orden = ordenCompraService.buscarPorId(id);

        Document documento = new Document();

        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font titulo = new Font(Font.HELVETICA, 18, Font.BOLD);

        documento.add(new Paragraph("ORDEN DE COMPRA", titulo));
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Número: " + orden.getNumeroOrden()));
        documento.add(new Paragraph("Fecha: " + orden.getFechaOrden()));
        documento.add(new Paragraph("Proveedor: " + orden.getProveedor().getNombre()));
        documento.add(new Paragraph("Artículo: " + orden.getArticulo().getDescripcion()));
        documento.add(new Paragraph("Cantidad: " + orden.getCantidad()));
        documento.add(new Paragraph("Unidad: " + orden.getUnidadMedida().getDescripcion()));
        documento.add(new Paragraph("Costo Unitario: RD$ " + orden.getCostoUnitario()));

        double total = orden.getCantidad() * orden.getCostoUnitario();

        documento.add(new Paragraph("----------------------------------------"));
        documento.add(new Paragraph("TOTAL: RD$ " + total));

        documento.close();
    }
}
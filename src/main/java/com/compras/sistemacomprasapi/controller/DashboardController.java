package com.compras.sistemacomprasapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String mostrarDashboard() {
        return "Dashboard";
    }
}

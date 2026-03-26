package com.example.ventas_service;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> getVentas() {
        return ventaService.getAllVentas();
    }

    @PostMapping
    public Venta addVenta(@RequestBody Venta venta) {
        return ventaService.addVenta(venta);
    }
}

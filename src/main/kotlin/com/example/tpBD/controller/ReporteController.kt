package com.example.tpBD.controller

import com.example.tpBD.service.ReporteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class ReporteController {

    @Autowired
    lateinit var reporteService: ReporteService

    @GetMapping("/reporte-contenidos")
    fun buscarReporte() = reporteService.buscarReporte()
}
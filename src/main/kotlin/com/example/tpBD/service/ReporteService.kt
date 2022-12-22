package com.example.tpBD.service

import com.example.tpBD.repository.ReporteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReporteService {

    @Autowired
    lateinit var reporteRepository: ReporteRepository

    fun buscarReporte() = reporteRepository.buscarReporte()
}
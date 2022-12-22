package com.example.tpBD.service

import com.example.tpBD.model.Reproduccion
import com.example.tpBD.repository.ReproduccionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReproduccionService {
    @Autowired
    lateinit var reproduccionRepository: ReproduccionRepository

    fun crear(reproduccion: Reproduccion) {
        reproduccionRepository.crear(reproduccion.idUsuario, reproduccion.idContenido, reproduccion.soUsado, reproduccion.fechaInicio, reproduccion.fechaFin, reproduccion.horaInicio, reproduccion.horaFin)
    }

}
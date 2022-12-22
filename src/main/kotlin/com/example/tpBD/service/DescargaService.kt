package com.example.tpBD.service

import com.example.tpBD.model.Descarga
import com.example.tpBD.repository.DescargaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class DescargaService {

    @Autowired
    lateinit var descargaRepository: DescargaRepository

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun crear(velocidadTransferencia :String, idContenido :Int) {

        jdbcTemplate.update("INSERT INTO es_descargado VALUES (" + idContenido + ", 1, " + velocidadTransferencia + ")")
    }
}
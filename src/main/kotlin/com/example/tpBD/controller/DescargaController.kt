package com.example.tpBD.controller

import com.example.tpBD.model.Descarga
import com.example.tpBD.model.DescargaVo
import com.example.tpBD.service.DescargaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class DescargaController {
    @Autowired
    lateinit var descargaService: DescargaService

    @PostMapping("/descarga")
    fun crear(@RequestBody descargaVo: DescargaVo) {
        descargaService.crear(descargaVo.velocidadTransferencia, descargaVo.idContenido)
    }
}
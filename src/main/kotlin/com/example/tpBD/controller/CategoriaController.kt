package com.example.tpBD.controller

import com.example.tpBD.model.Categoria
import com.example.tpBD.repository.CategoriaRepository
import com.example.tpBD.service.CategoriaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class CategoriaController {
    @Autowired
    lateinit var categoriaService: CategoriaService

    @GetMapping("/categorias")
    fun buscarTodas(): List<Categoria> {
        return categoriaService.buscarTodas()
    }
}
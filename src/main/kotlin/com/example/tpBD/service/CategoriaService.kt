package com.example.tpBD.service

import com.example.tpBD.model.Categoria
import com.example.tpBD.repository.CategoriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoriaService {

    @Autowired
    lateinit var categoriaRepository: CategoriaRepository

    fun buscarTodas() :List<Categoria> = categoriaRepository.buscarTodas()

}
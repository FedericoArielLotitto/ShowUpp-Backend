package com.example.tpBD.repository

import com.example.tpBD.model.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface CategoriaRepository: JpaRepository<Categoria, Long>  {

    @Query(value = "SELECT * FROM categoria", nativeQuery = true)
    fun buscarTodas() :List<Categoria>
}
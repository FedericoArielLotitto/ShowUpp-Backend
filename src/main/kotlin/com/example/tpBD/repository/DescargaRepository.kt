package com.example.tpBD.repository

import com.example.tpBD.model.Descarga
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal


interface DescargaRepository :JpaRepository<Descarga, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO descarga VALUES( :id, :velocidadTransferencia, 1 )", nativeQuery = true)
    fun crear(id:Long, velocidadTransferencia :String)
}
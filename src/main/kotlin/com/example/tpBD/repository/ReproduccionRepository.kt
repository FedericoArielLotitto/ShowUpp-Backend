package com.example.tpBD.repository

import com.example.tpBD.model.Reproduccion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalTime

interface ReproduccionRepository : JpaRepository<Reproduccion, Long>{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO es_reproducido (ID_USUARIO,ID_CONTENIDO,SISTEMA_OPERATIVO_USADO,FECHA_INICIO,FECHA_FIN,HORA_INICIO,HORA_FIN) " +
            "VALUES(:id_usuario, :id_contenido, :so_usado, :fecha_inicio, :fecha_fin, :hora_inicio, :hora_fin)", nativeQuery = true)
    fun crear(id_usuario: Long,
              id_contenido: Long,
              so_usado: String,
              fecha_inicio: LocalDate,
              fecha_fin: LocalDate,
              hora_inicio: LocalTime,
              hora_fin: LocalTime
              )
}
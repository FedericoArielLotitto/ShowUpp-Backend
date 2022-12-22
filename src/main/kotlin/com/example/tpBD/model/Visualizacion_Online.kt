package com.example.tpBD.model

import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "visualizacion_online")
class Visualizacion_Online {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var ID_VISUALIZACION: Long = 0

    @Column
    var SISTEMA_OPERATIVO_USADO: String = ""

    @Column
    var FECHA_INICIO: LocalDate = LocalDate.now()

    @Column
    var FECHA_FIN: LocalDate = LocalDate.now()

    @Column
    var HORA_INICIO: LocalTime = LocalTime.now()

    @Column
    var HORA_FIN: LocalTime = LocalTime.now()
}
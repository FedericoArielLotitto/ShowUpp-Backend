package com.example.tpBD.model

import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "es_reproducido")
class Reproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VISUALIZACION")
    var id: Long = 0
    @Column(name = "ID_USUARIO")
    var idUsuario: Long = 1
    @Column(name = "ID_CONTENIDO")
    var idContenido: Long = 1
    @Column(name = "SISTEMA_OPERATIVO_USADO")
    var soUsado: String = ""
    @Column(name = "FECHA_INICIO")
    var fechaInicio: LocalDate = LocalDate.now()
    @Column(name = "FECHA_FIN")
    var fechaFin: LocalDate = LocalDate.now()
    @Column(name = "HORA_INICIO")
    var horaInicio: LocalTime = LocalTime.now()
    @Column(name = "HORA_FIN")
    var horaFin: LocalTime = LocalTime.now()
}
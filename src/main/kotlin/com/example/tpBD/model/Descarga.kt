package com.example.tpBD.model

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "descarga")
class Descarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DESCARGA")
    var id: Long = 0
    @Column(name = "VELOCIDAD_TRANSFERENCIA")
    var velocidadTransferencia: String = ""
}
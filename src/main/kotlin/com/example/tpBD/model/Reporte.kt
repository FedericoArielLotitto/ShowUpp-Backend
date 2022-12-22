package com.example.tpBD.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class Reporte(
    var id_contenido: Long,
    var tipo: String,
    var extension: String,
    var cantidadVisualizaciones: Long,
    var cantidadDescargas: Long,
    var categorias: String
)
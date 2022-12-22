package com.example.tpBD.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

//Temporal . Hay que crear la clase abstrracta Contenido con sus clases  hijos

@Entity
@Table(name = "contenido")
class ContenidoG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTENIDO")
    var idContenido: Long = 0
    @Column(name = "TITULO")
    var titulo: String = ""
    @Column(name = "EXTENSION")
    var extension: String = ""
    @Column(name = "URL")
    var url: String = ""
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "FECHA_PUBLICADO")
    var fechaPublicacion: LocalDateTime = LocalDateTime.now()
}

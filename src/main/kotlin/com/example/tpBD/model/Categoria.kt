package com.example.tpBD.model

import javax.persistence.*

@Entity
@Table(name = "categoria")
class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    var id = 0
    @Column(name = "TIPO")
    var nombre :String = ""
}
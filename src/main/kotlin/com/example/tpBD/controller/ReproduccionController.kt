package com.example.tpBD.controller

import com.example.tpBD.model.ContenidoG
import com.example.tpBD.model.Reproduccion
import com.example.tpBD.service.ContenidoGService
import com.example.tpBD.service.ReproduccionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.nio.file.Files

@CrossOrigin
@RestController
class ReproduccionController {
    @Autowired
    lateinit var reproduccionService: ReproduccionService
    @Autowired
    lateinit var contenidoGenController: ContenidoGenController
    @Autowired
    lateinit var contenidoGService: ContenidoGService

    @GetMapping("/reproduccion/{id}")
    fun crear(@PathVariable id: Long, @RequestHeader(HttpHeaders.USER_AGENT) userAgent: String) : ResponseEntity<ByteArray> {
        println(userAgent)
        var reproduccion = Reproduccion().apply {
            soUsado = userAgent
            idContenido = id
        }
        reproduccionService.crear(reproduccion)
        var contenido = contenidoGenController.buscarXID(id)
        var rutaArchivo = contenidoGService.obtenerRutaCompletaArchivo(contenido)
        //Files.createDirectories(File(rutaArchivo).toPath())
        var rutaArchivoBytes = Files.readAllBytes(File(rutaArchivo).toPath())
        println(rutaArchivoBytes)
        //return FileSystemResource(rutaArchivo)
        //return rutaArchivoBytes


        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("video/mp4"))
            .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=video_%s.%s", 1, "mp4"))
            .body(rutaArchivoBytes);
    }
}
package com.example.tpBD.controller

import com.example.tpBD.model.Categoria
import com.example.tpBD.model.ContenidoG
import com.example.tpBD.model.ContenidoVo
import com.example.tpBD.repository.ContenidoGRepository
import com.example.tpBD.service.ContenidoGService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files


@RestController
@CrossOrigin
class ContenidoGenController {
    @Autowired
    lateinit var contenidoGService : ContenidoGService
    @Autowired
    lateinit var contenidoRepository: ContenidoGRepository

    @GetMapping("/")
    fun hola() ="BUENAS"

    @GetMapping("/getAll")
    fun todosLosContenidos(): List<ContenidoG> {

        return contenidoGService.obtenerTodosLosContenidos()
    }

    @GetMapping("/contenidos-por-titulo")
    fun buscarPorTitulo(@RequestParam titulo :String): List<ContenidoG> {
        return contenidoGService.buscarPorTitulo(titulo)
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String? {
        contenidoGService.eliminarUnContenido(id) //
        return "Contenido eliminado"
    }

    @PostMapping("/guardar-el-contenido")
    fun guardarContenido(@RequestPart("contenidoG") contenidoVo: ContenidoVo, @RequestPart("archivo") archivo: MultipartFile): String {
        var contenidoG :ContenidoG = ContenidoG()
        contenidoG.titulo = contenidoVo.titulo
        contenidoG.extension = contenidoVo.extension

        var categorias :List<Categoria> = contenidoVo.categorias

        contenidoGService.guardarContenido(contenidoG, archivo.bytes)
        var contenidos :List<ContenidoG> = contenidoGService.buscarOrdenadosPorId()
        contenidoGService.guardarAsociacionContenidoCategoria(contenidos[0].idContenido, categorias)
        return "Contenido guardado"
    }

    @PutMapping("/actualizarContenido/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody contenidoG: ContenidoG): String? {
        contenidoGService.actualizarContenido(id, contenidoG)

        return "contenido actualizado"
    }

    @GetMapping("/obtenerContenidoPorID/{id}")
    fun buscarXID(@PathVariable id: Long): ContenidoG {
        println(contenidoRepository.buscarContenidoPorId(id))
        return contenidoRepository.buscarContenidoPorId(id)
    }

    @GetMapping("/descargar/{id}")
    fun descargar(@PathVariable id: Long): ResponseEntity<InputStreamResource> {
        println(id)
        var contenido = contenidoRepository.buscarContenidoPorId(id)
        var rutaArchivo = contenidoGService.obtenerRutaCompletaArchivo(contenido)
        var rutaArchivoBytes = Files.readAllBytes(File(rutaArchivo).toPath())
        val archivo = File(rutaArchivo)
        val resource = InputStreamResource(FileInputStream(archivo))
        val headers: HttpHeaders = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + contenido.titulo + "." +contenido.extension)

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

 /*   @GetMapping("/obtenerContenidosPorCategoria/{categoria}")
    fun buscarXCategoria(@PathVariable categoria: String): List<ContenidoG> {
        var categoriaNumber = 0
        when (categoria) {
            "Video" -> categoriaNumber = 1
            "Audio", "Documentos" -> categoriaNumber = 2
        }
        return contenidoGService.buscarPorCategoria(categoriaNumber)
    }*/
    @GetMapping("/contenidos-por-categoria")
    fun buscarPorCategorias(@RequestParam categoria: String): List<ContenidoG> {
        return contenidoGService.buscarPorCategorias(categoria)
    }

}
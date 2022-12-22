package com.example.tpBD.service

import com.example.tpBD.model.Categoria
import com.example.tpBD.model.ContenidoG
import com.example.tpBD.repository.ContenidoGRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDateTime
import java.util.*

@Service
class ContenidoGService {

    @Value("\${tp.bd.contenidos}")
    private final var rutaBaseContenidos :String = ""
    private final var rutaContenidos :String = "/public/contenidos"
    private final var extensionesVideo :List<String> = Arrays.asList("MP4", "WAV", "AVI")
    @Autowired
    lateinit var contenidoGRepository: ContenidoGRepository
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun obtenerTodosLosContenidos(): List<ContenidoG> {
        return contenidoGRepository.todosLosContenidos()
    }

    fun guardarContenido(contenidoG: ContenidoG, archivo: ByteArray) :Int {
        val titulo = contenidoG.titulo
        val extension = contenidoG.extension
        val idTipo = if (esVisualizacion(contenidoG)) 2 else 1
        persitirEnFileSystem(contenidoG, archivo)
        var url :String = "${obtenerRutaDescargaArchivo(contenidoG)}"
        return contenidoGRepository.agregarContenido(titulo, LocalDateTime.now(), extension, url, idTipo)
    }

    fun eliminarUnContenido(id : Long) : String{
        var contenidoABorrar = contenidoGRepository.buscarContenidoPorId(id)
        eliminarArchivoAsociadoDelFileSystem(contenidoABorrar)
        contenidoGRepository.eliminarContenido(id)
        return "Contenido eliminado"
    }

    fun actualizarContenido(id : Long , contenidoG: ContenidoG){
        val contenidoEncontrado = contenidoGRepository.buscarContenidoPorId(id)
        val titulo = contenidoG.titulo
        val extension = contenidoG.extension
        modificarNombreArchivoEnFileSystem(contenidoEncontrado, contenidoG)
        contenidoGRepository.actualizarContenido(titulo,extension,contenidoEncontrado.idContenido)
    }

    fun guardarAsociacionContenidoCategoria(idContenido :Long, categorias :List<Categoria>) {
        categorias.forEach { categoria: Categoria ->
            jdbcTemplate.update("INSERT INTO se_clasifica_en VALUES ($idContenido,${categoria.id})")
        }
    }

    fun buscarOrdenadosPorId() :List<ContenidoG> = contenidoGRepository.findAllByOrderByIdContenidoDesc()

    private fun modificarNombreArchivoEnFileSystem(contenidoEncontrado: ContenidoG, contenidoG: ContenidoG) {
        Files.move(File(obtenerRutaCompletaArchivo(contenidoEncontrado)).toPath(),
                File(obtenerRutaCompletaArchivo(contenidoG)).toPath())
    }

    private fun persitirEnFileSystem(contenidoG :ContenidoG, archivo: ByteArray) {
        var rutaArchivo :String = "$rutaBaseContenidos$rutaContenidos/"
        Files.createDirectories(File(rutaArchivo).toPath())
        var file = File("${obtenerRutaCompletaArchivo(contenidoG)}")
        file.writeBytes(archivo)
    }

    private fun eliminarArchivoAsociadoDelFileSystem(contenidoG: ContenidoG) {
        Files.deleteIfExists(File(obtenerRutaCompletaArchivo(contenidoG)).toPath())
    }

    fun obtenerRutaCompletaArchivo(contenidoG: ContenidoG) :String
    = "$rutaBaseContenidos$rutaContenidos/${obtenerNombreArchivo(contenidoG)}"
    private fun obtenerRutaDescargaArchivo(contenidoG: ContenidoG) :String
    = "/contenidos/${obtenerNombreArchivo(contenidoG)}"
    private fun obtenerNombreArchivo(contenidoG: ContenidoG) :String
    = "${contenidoG.titulo}.${contenidoG.extension.lowercase()}"

    private fun esVisualizacion(contenidoG: ContenidoG) :Boolean {
        return extensionesVideo.contains(contenidoG.extension)
    }
    fun buscarPorCategoria(categoria: Number): List<ContenidoG> {
        return contenidoGRepository.buscarPorCategoria(categoria)
    }

    fun buscarPorCategorias(categoria :String) :List<ContenidoG> {
        return contenidoGRepository.buscarPorCategorias(categoria)
    }

    fun buscarPorTitulo(titulo :String) :List<ContenidoG> {
        return contenidoGRepository.buscarPorTitulo(titulo)
    }
}
package com.example.tpBD.repository

import com.example.tpBD.model.Reporte
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet


@Component
class ReporteRepository {

    @Autowired
    lateinit var jdbcTempllate: JdbcTemplate

    fun buscarReporte(): List<Reporte> {
        var rowMapper: RowMapper<Reporte> = RowMapper<Reporte> { resultSet: ResultSet, index: Int ->
            Reporte(
                    resultSet.getLong("ID_CONTENIDO"),
                    resultSet.getString("TITULO"),
                    resultSet.getString("EXTENSION"),
                    resultSet.getLong("CANTIDAD_VISUALIZACIONES"),
                    resultSet.getLong("CANTIDAD_DESCARGADOS"),
                    resultSet.getString("CATEGORIA")
            )
        }

        var results = jdbcTempllate.query("SELECT  CTN.ID_CONTENIDO\n" +
                "       ,CTN.TITULO\n" +
                "       ,CTN.EXTENSION\n" +
                "       ,COUNT(ES_REPRODUCIDO.ID_CONTENIDO) CANTIDAD_VISUALIZACIONES\n" +
                "       ,COUNT(ES_DESCARGADO.ID_CONTENIDO) CANTIDAD_DESCARGADOS\n" +
                "       ,CLF.CATEGORIA\n" +
                "FROM contenido CTN\n" +
                "LEFT JOIN ES_DESCARGADO ON ES_DESCARGADO.ID_CONTENIDO = CTN.ID_CONTENIDO\n" +
                "LEFT JOIN ES_REPRODUCIDO ON ES_REPRODUCIDO.ID_CONTENIDO = CTN.ID_CONTENIDO\n" +
                "INNER JOIN\n" +
                "   (\n" +
                "      SELECT  ID_CONTENIDO\n" +
                "            ,group_concat(cat.tipo) CATEGORIA\n" +
                "      FROM SE_CLASIFICA_EN SCLF\n" +
                "      INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = SCLF.ID_CATEGORIA\n" +
                "      GROUP BY  ID_CONTENIDO\n" +
                "   ) CLF ON clf.id_contenido = ctn.id_contenido\n" +
                "GROUP BY  CTN.ID_CONTENIDO\n" +
                "ORDER BY COUNT(ES_REPRODUCIDO.ID_CONTENIDO)+COUNT(ES_DESCARGADO.ID_CONTENIDO) DESC", rowMapper)

        return results
    }
}
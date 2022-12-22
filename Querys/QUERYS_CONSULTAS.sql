/*
===============================================================================================================================================================================================
Listar datos para los menus de opciones
===============================================================================================================================================================================================
*/
SELECT * FROM categoria
select * from contenido  where ID_CONTENIDO = :id

/*
===============================================================================================================================================================================================
SENTENCIAS PARA MANIPULAR INFORMACION
===============================================================================================================================================================================================
*/
DELETE FROM CONTENIDO WHERE ID_CONTENIDO=:id
UPDATE contenido SET TITULO = :titulo, EXTENSION = :extension WHERE ID_CONTENIDO = :id
INSERT  CONTENIDO (TITULO,FECHA_PUBLICADO,EXTENSION,URL,ID_TIPO_CONTENIDO) VALUES(:titulo,:fechaPublicado, :extension, :url, :idTipo)

/*
===============================================================================================================================================================================================
CONSULTAS DE REPORTERIA
===============================================================================================================================================================================================
*/

-- Busca contenidos por categoria
SELECT  distinct cont.*
FROM se_clasifica_en claf
INNER JOIN contenido cont ON cont.ID_CONTENIDO = claf.ID_CONTENIDO
INNER JOIN categoria cat ON cat.ID_CATEGORIA = claf.ID_CATEGORIA
WHERE cat.TIPO IN (:categoria)
ORDER BY cont.ID_CONTENIDO desc;


-- Busca contenidos por titulo.
SELECT *
FROM CONTENIDO
WHERE TITULO LIKE '%:titulo%';


-- Ordenar archivos (no importa su tipo o categoría) por cantidad de descargas de mayor a menor
SELECT  CTN.ID_CONTENIDO
       ,CTN.TITULO
       ,CTN.EXTENSION
       ,COUNT(ES_REPRODUCIDO.ID_CONTENIDO) CANTIDAD_VISUALIZACIONES
       ,COUNT(ES_DESCARGADO.ID_CONTENIDO) CANTIDAD_DESCARGADOS
       ,CLF.CATEGORIA
FROM contenido CTN
LEFT JOIN ES_DESCARGADO ON ES_DESCARGADO.ID_CONTENIDO = CTN.ID_CONTENIDO
LEFT JOIN ES_REPRODUCIDO ON ES_REPRODUCIDO.ID_CONTENIDO = CTN.ID_CONTENIDO
INNER JOIN
   (
      SELECT  ID_CONTENIDO
            ,group_concat(cat.tipo) CATEGORIA
      FROM SE_CLASIFICA_EN SCLF
      INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = SCLF.ID_CATEGORIA
      GROUP BY  ID_CONTENIDO
   ) CLF ON clf.id_contenido = ctn.id_contenido
GROUP BY  CTN.ID_CONTENIDO
ORDER BY COUNT(ES_REPRODUCIDO.ID_CONTENIDO)+COUNT(ES_DESCARGADO.ID_CONTENIDO) DESC;



/*
===============================================================================================================================================================================================
VARIOS
===============================================================================================================================================================================================
*/

-- Se lista los contenidos que son descargable
select cont.*
from contenido cont
where cont.id_tipo_contenido = 2;


-- Busqueda contenido por Extension de archivo
select *
from contenido
where EXTENSION in('MP4','PDF');




-- Inserta un documento
INSERT  CONTENIDO (TITULO,FECHA_PUBLICADO,EXTENSION,URL,ID_TIPO_CONTENIDO) VALUES('AGREGACION DE CONTENIDO','2021-05-01','MP4','https://drive.google.com/drive/u/0/folders/AGREGACION DE CONTENIDO.MP4',1);

-- Lista de todos los contenidos.
SELECT * FROM CONTENIDO;

-- Elimina un contenido
DELETE FROM CONTENIDO
WHERE ID_CONTENIDO=51;

-- Cantidad de descargas por extension.
SELECT CTN.EXTENSION, COUNT(ES_DESCARGADO.ID_CONTENIDO) CANTIDAD_ARCHIVOS_DESCARGADOS
FROM contenido CTN
         LEFT JOIN ES_DESCARGADO ON ES_DESCARGADO.ID_CONTENIDO = CTN.ID_CONTENIDO
GROUP BY CTN.EXTENSION
ORDER BY COUNT(ES_DESCARGADO.ID_CONTENIDO) DESC
;


-- Cantidad de videos vistos por extension
SELECT CTN.EXTENSION,COUNT(ES_REPRODUCIDO.ID_CONTENIDO) CANTIDAD_DE_VIDEOS_VISTOS
FROM contenido CTN
         INNER JOIN ES_REPRODUCIDO ON ES_REPRODUCIDO.ID_CONTENIDO = CTN.ID_CONTENIDO
GROUP BY CTN.EXTENSION
ORDER BY COUNT(ES_REPRODUCIDO.ID_CONTENIDO) DESC ;



-- Cantidad de veces que se descargo y visualizaron los archivos
-- Ordenar archivos (no importa su tipo o categoría) por cantidad de descargas de mayor a menor


-- CANTIDAD DE CONTENIDO POR CLASIFICACION
select cat.tipo, count(claf.id_contenido) CANTIDAD_CONTENIDO_CATEGORIA
from categoria cat
         inner join SE_CLASIFICA_EN claf on claf.ID_CATEGORIA = cat.ID_CATEGORIA
group by cat.tipo;


-- Busca contenidos por categoria de forma excluyente, es decir que debe tener todas las categorias seleccionadas.
select cont.*
from se_clasifica_en claf
         inner join contenido cont on cont.ID_CONTENIDO = claf.ID_CONTENIDO
         inner join categoria cat on cat.ID_CATEGORIA = claf.ID_CATEGORIA
where cat.TIPO in ('BASICO','ULTRA')
group by cont.ID_CONTENIDO
having count(distinct cat.tipo) = 2;


-- Filtrar todos los contenidos cuya fecha de publicación esté dentro de un rango a elección del usuario logueado.
SELECT *
FROM CONTENIDO CONT
WHERE FECHA_PUBLICADO BETWEEN '2022-01-01' AND '2022-12-31';
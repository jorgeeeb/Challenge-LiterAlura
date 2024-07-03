# Literalura Challenge
Tercer y ultimo proyecto de la formación, muy interesante y que deja muchas enseñanzas. Esta aplicación Java permite buscar información sobre libros y respectivos autores desde la API GUTENDEX, almacenando toda esta informacion en una base de datos PostgreSQL. Y teniendo diversas funciones para la impresión de la información obtenida que será explicado mas adelante


# Requisitos previos
Java 17 o superior, Maven, PostgreSQL, IntelliJ IDEA. Necesitará clonar el repositorio, configurar la base de datos y luego ejecutar la aplicación.


# Menú principal
La aplicación presenta un menú interactivo con las siguientes opciones:

**1 - Buscar libro por título*

**2 - Listar libros registrados*

**3 - Listar autores registrados*

**4 - Listar autores vivos en un determinado año*

**5 - Listar libros por idioma*

**0 - Salir*


  **Buscar libro por título**: Le pido al usuario que indique el nombre del libro que busca, y la aplicación lo buscará en la API almacenando la información en la base de datos. Si ya existe el libro en la base de datos, se le notificará eso al usuario e imprimirá la información de todas formas.


  **Listar libros registrados**: Imprime todos los libros almacenados en la base de datos, con el titulo, autores, idioma y cantidad de descargas.


  **Listar autores registrados**: Imprime a todos los autores almacenados, indicando su nombre, año de nacimiento, año de fallecimiento y lista de los libros escritos por el.


  **Listar autores vivos en un determinado año**: Se le pide al usuario un año que le interese, y la aplicación buscará en la base de datos a los autores que estuvieron vivos en ese año. imprimiendo la lista de estos con la misma información que el apartado anterior.


   **Listar libros por idioma**: El usuario va a determinar el idioma de los libro que desea listar, eligiendo el la opción y devolverá la impresión de los libros que cumplan el requisito.



   Esa es toda la aplicación, muy buen proyecto del cual me llevo muchas enseñanzas.

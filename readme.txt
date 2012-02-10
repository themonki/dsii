exite un archivo llamado .gitignore que contiene lo siguiente:

SIGETI/nbproject/
SIGETI/build/
SIGETI/dist/
eShop/nbproject/
eShop/build/
eShop/dist/
SIGETI/build.xml
eShop/build.xml
SIGETI/src/java/Utilidades/FachadaBD.java

estos archivos estaran localmente en sus máquinas y corresponden a las ppropiedades específicas de cada quien.
También se a ignorado la fachada puesto que si tiene bases de datos y usuarios distintos esot creara problemas.

NOTA: Como servidor estoy usando Apache Tomcat por que en un principio Glasfish no me servia, me daba errores. Pero sepuede usar cualquiera

Para abrir los proyectos, selecionen crear un nuevo proyecto web, pero seleccionan la opcion de crearlo con fuentes existentes. Recuerden añadir el controlador de la base de datos para postgres, tambien las librerias jsf 2.1 y jstl 1.1

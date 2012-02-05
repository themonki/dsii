por fa prueben que eShop funcione, no se si como estÃ© se los reconozca netbeans la idea es hacer un archivo
 .gitignore para que no se cargue a git las propiedades especificas de configuracion de cada quien, el archivo debe de contener

/SIGETI/nbproject/private/
/SIGETI/build/
/eShop/nbproject/private/
/eShop/build/

NOTA: Como servidor estoy usando Apache Tomcat por que en un principio Glasfish no me servia, me daba errores,
aunque esto contradice lo que dijo Erika.


este... para abrirlo hay que borrar /nbproject 

en netbeans le damos nuevo proyecto web de folder existente y seleccionamos la carpeta sigeti , si se encuentra con este archivo no abre de lo contrario lo tomara como un folder normal y lo abrira sin problema , luego recuerden añadir el servidor web , y postgres 

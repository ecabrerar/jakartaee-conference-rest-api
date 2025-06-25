#  jakartaee-conference-rest-api
Esta es una prueba de concepto del uso de la librería rest-assured en proyectos Java.

## Clonar el repositorio
```bash 
git clone git@github.com:ecabrerar/jakartaee-conference-rest-api.git
```
#### Java 21 es requerido.

## Moverte al directorio del proyecto


```bash 
cd jakartaee-conference-rest-api
chmod +x mvnw // Agregar permiso de ejecución
```


## Correr el proyecto
```bash 
./mvnw clean package wildfly:dev -DskipTests  //Modo development
./mvnw clean package wildfly:run -DskipTests
```

Una vez que se inicia el servidor, puede acceder al endpoint [http://localhost:8080/jakartaee-conference-rest-api/rest/speakers](http://localhost:8080/jakartaee-conference-rest-api/rest/speakers).

También puedes hacerlo desde la terminal, usando curl


```bash 
curl http://localhost:8080/jakartaee-conference-rest-api/rest/speakers
```

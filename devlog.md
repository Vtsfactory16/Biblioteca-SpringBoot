# Diario de a bordo

Nuestras hazañas y desgracias :feelsgood:

### 2024-02-18

- Librería `FlatLaf`
- Creación de Dockerfile

> Crear el contenedor de docker es sencillo pero no he conseguido que se conecte
> con la base de datos mysql. He probado primero a cambiar la ip de mysql y después
> he probado conectando los dos contenedores a la misma red Docker.

### 2024-02-17

- Inserciones en **histórico** desde el resto de controladores
- **Actualización** de ventanas cuando se produce un cambio (anteriormente implementado con observer)
- Búsqueda de **libros** y **usuarios** desde la ventana `FichaPrestamo`
- Mejora en la **gestión de errores** de las peticiones HTTP
    - Lanzamos una excepción si el código de respuesta es distinto a 200
- Volver a implementar exportaciones a **csv**
- Volver a implementar el patrón **DAO**

### 2024-02-16

Pequeños arreglos:

- Refactorizar **controladores**
- Arreglar error en el método `toJson()` de `Libro`
- Eliminar archivos **JavaDoc** antiguos

### 2024-02-15

- Eliminar el **controlador de Historico**
- Inserción de prestamos

### 2024-02-14

- Uso de `ObjectMapper` para pasar de **json** a **objeto**

### 2024-02-13

- Peticiones HTTP al API REST.
- Object Mapping (Pasar objetos a JSON y JSON a objetos)

### 2024-02-10

- Aplicación cliente del proyecto anterior adaptada a este proyecto
    - Eliminar librerías de **Hibernate** y **JDBC**
    - Eliminar **DAOs**
    - Adaptar **DTOs** (eliminar etiquetas JPA)
    - Añadir librería para **json**
    - Implementada función de **insertar usuarios**
    - login desactivado temporalmente

### 2024-02-07

- Microservicio probado con peticiones GET, POST PUT y DELETE
- Arreglado método **POST**
    - Línea `usuario.setId(0);`
- Validación de las entidades
    - `@Validated`, `@NotEmpty`, `@Size`
- Arreglado `@JsonIgnore` de usuario

### 2024-02-06

- Implementados **Repositorios** y **Controladores**
- Microservicio en funcionamiento
- Problemas: 
    - [Unknown Column in Field List](https://stackoverflow.com/questions/50567041/spring-boot-jpa-unknown-column-in-field-list)
    - Etiqueta `@JsonIgnoreProperties` para evitar bucle infinito al generar el Json

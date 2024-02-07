# Diario de a bordo

:feelsgood:


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

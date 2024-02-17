<h1 align="center">Proyecto Biblioteca SpringBoot</h1>

<div align="center">
  
![Grupo C](https://img.shields.io/badge/Grupo-C-blue?style=flat)

[![Static Badge](https://img.shields.io/badge/Iridescent-purple?style=flat&logo=github)](https://github.com/Iridescent1010)&nbsp;&nbsp;
[![Static Badge](https://img.shields.io/badge/Red_One-black?style=flate&logo=github)](https://github.com/Vtsfactory16)&nbsp;&nbsp;
[![Static Badge](https://img.shields.io/badge/Cakeneka-pink?style=flat&logo=github)](https://github.com/CakeNeka)

</div >

## 📄 Descripción

El proyecto consiste en dos aplicaciones: por un lado un microservicio API REST desarrollado en java con **Springboot** y por otro
una aplicación **cliente** desarrollada en Java Swing.



<div>
<b>Tecnologías:</b> 
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=flat&logo=Hibernate&logoColor=white" />
  <img alt="java shield" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white" />
  <img alt="mysql shield" src="https://img.shields.io/badge/MySQL-005C84?style=flat&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?&style=flat&logo=spring&logoColor=white" />
</div>

### Aplicación Spring Boot (API REST)

La API REST proporciona endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en las siguientes entidades:

- **Controladores:**
  - Usuario
  - Categoría
  - Histórico
  - Libro
  - Préstamos

Cada controlador gestiona las operaciones relacionadas con su respectiva entidad, como crear un nuevo usuario, obtener información sobre libros, etc.

### Aplicación Cliente (Java Swing)

La aplicación cliente proporciona una interfaz de usuario amigable para interactuar con la API REST. Incluye:

#### HTTPRequests

- Módulos para realizar peticiones HTTP a la API en cada entidad:
  - Request Categoría
  - Request Libro
  - Request Préstamos
  - Request Usuario

#### CRUD Cliente

- Clases para realizar operaciones CRUD en cada entidad:
  - Presentador Usuario
  - Presentador Libro
  - Presentador Categoría
  - Presentador Préstamos

Estos presentadores actúan como la capa intermedia entre la interfaz de usuario y las solicitudes HTTP, gestionando la lógica de negocio y la presentación de datos.

## 🚀 Instrucciones de Uso

Para ejecutar la aplicación, sigue estos pasos:

1. Clona este repositorio en tu máquina local.
2. Importa el proyecto en tu IDE de Java preferido.
3. Configura la conexión a la base de datos MySQL en el archivo `application.properties`.
4. Ejecuta la aplicación Spring Boot para iniciar el servidor.
5. Ejecuta la aplicación cliente para interactuar con la API.



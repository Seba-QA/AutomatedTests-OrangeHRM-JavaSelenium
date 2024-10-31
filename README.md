# Portafolio de Pruebas Automatizadas para OrangeHRM

## Objetivo

El propósito de este proyecto es demostrar conocimientos en pruebas automatizadas mediante la implementación de casos de prueba en la aplicación web de recursos humanos OrangeHRM. El proyecto abarca la estrategia, ejecución y gestión de pruebas, proporcionando una visión completa del proceso de automatización en un entorno realista.

## Tecnologías y Herramientas

Este proyecto utiliza las siguientes tecnologías y herramientas:

- **Java**: Lenguaje de programación principal.
- **Maven**: Herramienta de gestión de proyectos y dependencias.
- **Selenium**: Entorno de pruebas automatizadas para navegadores web.
- **Cucumber**: Herramienta para pruebas basadas en comportamiento (BDD).
- **Gherkin**: Lenguaje específico para la escritura de pruebas en Cucumber.

## Página Web Probada

Las pruebas están diseñadas para la página de OrangeHRM: [OrangeHRM Demo](https://www.orangehrm.com/). Esta aplicación ofrece funcionalidades de gestión de recursos humanos, permitiendo probar desde el inicio de sesión hasta la administración de perfiles y datos de empleados, siendo ideal para la automatización de flujos empresariales.

## Documentación Adicional

- [Documentación de Selenium](https://www.selenium.dev/documentation/en/)
- [Documentación de Cucumber](https://cucumber.io/docs/guides/overview/)

## Estructura del Proyecto

La estructura del proyecto está organizada de la siguiente manera:

- **Screenshots**: Carpeta para almacenar capturas de pantalla de los flujos de prueba ejecutados.
- **Carpeta src**: Contiene el código fuente, organizado en las siguientes subcarpetas:
  - **main/java**
    - **utils**: Funciones generales utilizadas en los flujos de pruebas.
    - **pages**: Lógica de las pruebas con clases que representan páginas de la aplicación y sus interacciones.
  - **test/java**
    - **steps**: Definiciones de pasos (steps) que conectan los archivos feature y las páginas de prueba.
    - **runners**: Archivos runner para ejecutar las pruebas. Estos runners pueden ejecutar todas las pruebas o específicas mediante tags.
- **Carpeta resources**
  - **features**: Archivos de características escritos en Gherkin y archivos de configuración necesarios.

## Archivo de configuración

- **pom.xml**: Archivo de configuración de Maven donde se definen las dependencias y configuraciones del proyecto.





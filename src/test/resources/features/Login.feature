Feature: Login

Background: Ingreso a la pagina web
    Given Ingreso a la url

    @credencialesValidas
    Scenario Outline: Iniciar sesión con credenciales válidas
        Given Ingreso "<user>" y "<pass>"
        When Se presiona boton login
        Then Se ve el dashboard
        Examples:
            | user  | pass     |
            | Admin | admin123 |

    @credencialesInvalidas
    Scenario Outline: Iniciar sesión con distintas combinaciones
        Given Ingreso "<user>" y "<pass>" incorrecto
        When Se presiona boton login falla inicio de sesio
        Then Se validan mensajes de error "<caso>"
        Examples:
            | user   | pass      | caso                  |
            | admi   | admin123  | USR_INCORRECTO        |
            | Admin  | admin 321 | PASS_INCORRECTO       |
            | admi   | admin321  | AMBOS_INCORRECTOS     |
            |        | admin123  | USR_VACIO             |
            | Admin  |           | PASS_VACIO            |
            |        |           | AMBOS_VACIO           |
# Test Plan — qa-selenium-java
**Version:** 1.0 | **Autor:** Henry Seya | **Fecha:** 2026-05-30

## Objetivos
Verificar flujos criticos de autenticacion y navegacion
mediante automatizacion E2E con Selenium WebDriver.

## Alcance
### En scope
- Login / Logout
- Validaciones de formulario
- Navegacion entre paginas

### Out of scope
- Tests de performance
- Tests de seguridad avanzados

## Stack
| Herramienta | Version |
|-------------|---------|
| Java        | 21      |
| Selenium    | 4.18.1  |
| TestNG      | 7.9.0   |
| Maven       | 3.9+    |
| Allure      | 2.25.0  |

## Criterios de salida
- 0 tests fallidos en smoke suite
- Pipeline CI/CD verde
- Reporte Allure generado

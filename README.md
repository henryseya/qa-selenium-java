# qa-selenium-java

![CI](https://github.com/henryseya/qa-selenium-java/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-21-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.18.1-green)
![TestNG](https://img.shields.io/badge/TestNG-7.9.0-blue)

## Descripción
Framework de automatización E2E para aplicaciones web usando Selenium WebDriver 4
con Java 21, TestNG y patrón Page Object Model (POM).
Integrado con GitHub Actions para ejecución continua en CI/CD.

## Stack Tecnológico
| Herramienta | Versión | Propósito |
|-------------|---------|-----------|
| Java        | 21      | Lenguaje principal |
| Selenium    | 4.18.1  | Automatización web |
| TestNG      | 7.9.0   | Test runner |
| Maven       | 3.9+    | Gestión de dependencias |
| Allure      | 2.25.0  | Reportes |
## Estructura del Proyecto
src/
├── main/java/com/qaengineer/
│   ├── pages/       # Page Objects (LoginPage)
│   └── utils/       # BasePage
└── test/java/com/qaengineer/
├── tests/       # LoginTest
└── utils/       # BaseTest
## Casos de Prueba
| Test | Tipo | Estado |
|------|------|--------|
| Login exitoso con credenciales válidas | Smoke | ✅ |
| Login fallido con usuario incorrecto | Regression | ✅ |
| Login fallido con password incorrecta | Regression | ✅ |

## Cómo ejecutar
```bash
# Clonar el repositorio
git clone https://github.com/henryseya/qa-selenium-java.git
# Ejecutar todos los tests
mvn clean test

# Generar reporte Allure
mvn allure:report
```
## Patrones aplicados
- Page Object Model (POM)
- Data-Driven Testing con @DataProvider
- Explicit Waits (sin Thread.sleep)
- Herencia con BaseTest y BasePage

## CI/CD
Cada push a `main` dispara el pipeline en GitHub Actions.
Los reportes se publican como artefactos descargables por 30 días.
# Facturacion PDF

Proyecto Android (Kotlin + Jetpack Compose + Room/SQLite) listo para abrir en Android Studio.

## Cómo abrirlo

1. Descomprime el archivo `FacturacionPDF.zip`.
2. Abre Android Studio → **File > Open** → selecciona la carpeta `FacturacionPDF` (la que contiene `settings.gradle.kts`).
3. Espera a que Android Studio sincronice Gradle (la primera vez puede tardar varios minutos porque descarga el Gradle 8.7 indicado en `gradle/wrapper/gradle-wrapper.properties` y las dependencias).
4. Si Android Studio pregunta por la versión del **Gradle JDK**, usa JDK 17 (Settings > Build Tools > Gradle).
5. Presiona **Run ▶** con un emulador o dispositivo conectado (mínimo Android 7.0 / API 24).

## Qué hace la app

- Formulario para generar una factura (RNC/Cédula, nombre del cliente, ID de producto, cantidad).
- Guarda Cliente, Producto y Factura en una base de datos **Room (SQLite)** local.
- Valida stock disponible antes de procesar.
- Al confirmar, genera un **recibo en PDF** (usando `PdfDocument`) y lo abre automáticamente con el visor de PDF del dispositivo.
- Ya viene con un producto de prueba precargado: **ID 1 - Laptop - $500.00 - stock 10**.

## Estructura

```
app/src/main/java/com/example/facturacionpdf/
├── MainActivity.kt          # UI en Compose + lógica de negocio
├── FacturacionApp.kt        # Clase Application, inicializa Room
├── database/
│   ├── ClienteEntity.kt
│   ├── ProductoEntity.kt
│   ├── FacturaEntity.kt
│   ├── FacturacionDao.kt
│   └── FacturacionDatabase.kt
└── pdf/
    └── PdfGenerator.kt      # Genera el recibo PDF
```

## Nota sobre el Gradle Wrapper

Este paquete no incluye el binario `gradle-wrapper.jar` (por restricciones de este entorno para generar archivos binarios). Android Studio lo regenera automáticamente al sincronizar por primera vez. Si prefieres usar la línea de comandos (`./gradlew`), genera el wrapper tú mismo con:

```
gradle wrapper --gradle-version 8.7
```

(requiere tener Gradle instalado una sola vez para ese comando).

# Fix Your Plants App

## Estructura de Carpetas del Proyecto

Aquí se detalla la estructura de carpetas sugerida para organizar un proyecto de Android en Android Studio, especialmente cuando se utiliza Jetpack Compose como framework de UI.

### Directorio `app`

- **src/main/java/com/tuempresa/tuproject**: Directorio principal de código fuente de la aplicación.

    - **data**: Contiene la capa de acceso a datos.
        - **model**: Modelos de datos utilizados en la aplicación.
        - **repository**: Clases para el acceso a datos, como Room, Retrofit, etc.

    - **di**: Configuración de inyección de dependencias.
        - **module**: Módulos de Dagger o Hilt para la inyección de dependencias.

    - **ui**: Contiene las clases relacionadas con la interfaz de usuario.
        - **components**: Componentes reutilizables de Jetpack Compose.
        - **screens**: Pantallas de la aplicación.
        - **theme**: Estilos de la aplicación, como colores, tipografía, etc.
            - `Color.kt`: Definiciones de colores.
            - `Typography.kt`: Definiciones de tipografía.
            - `Shape.kt`: Definiciones de forma.
            - `Type.kt`: Definiciones de tipos de letra.

    - **util**: Utilidades varias.

    - **viewmodel**: Contiene los ViewModels.
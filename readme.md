[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=20542526)
# Patrones de Diseño Creacionales en Java: Prototype
## 📚 Descripción del Curso
**Curso:** Programación Orientada a Objetos Avanzada con Java  
**Tema:** Patrones de Diseño Creacionales - Prototype 
**Objetivo de Aprendizaje:** Al finalizar esta sesión, los estudiantes serán capaces de:
1.  Identificar escenarios donde los patrones Prototype y Singleton son aplicables.
2.  Implementar este patron en Java, comprendiendo la mecánica de la clonación de objetos.
3.  Articular las ventajas y los potenciales inconvenientes.

---

## 🧩 Ejercicio 1: El Patrón Prototype

### Contexto del Problema
Imagina que estamos construyendo una aplicación de diseño gráfico. Los usuarios pueden crear objetos `GraphicElement` complejos (por ejemplo, un botón estilizado con sombras, gradientes y formas personalizadas). Crear estos objetos desde cero usando la palabra clave `new` es computacionalmente costoso porque implica cargar recursos y aplicar estilos complejos.

### Código Inicial (El Problema)
El código actual crea nuevos objetos de manera ineficiente llamando al constructor cada vez. El constructor simula una operación costosa con un `Thread.sleep`.

### Tu Tarea: Transformar el código usando el Patrón Prototype.

#### Instrucciones Paso a Paso:
1.  **Haz que la clase sea Cloneable:**
    *   En la clase `GraphicElement`, implementa la interfaz `Cloneable`. Esta es una interfaz marcadora que le indica a Java que está permitido clonar objetos de esta clase.

2.  **Sobrescribe el método `clone()`:**
    *   Dentro de la clase `GraphicElement`, sobrescribe el método protegido `Object.clone()`. Hazlo público.
    *   La implementación por defecto de `Object.clone()` realiza una *copia superficial* (shallow copy), que es suficiente para esta clase simple.
    *   Tu método debe verse así:
    ```java
    @Override
    public GraphicElement clone() {
        try {
            return (GraphicElement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // No puede ocurrir ya que implementamos Cloneable
        }
    }
    ```
    *Nota el casteo a `(GraphicElement)`. Esto es necesario.*

3.  **Usa el Prototipo en el Código Cliente (Main):**
    *   En la clase `Main`, cambia la forma en que se crea `button2`.
    *   En lugar de llamar al costoso constructor, clona el objeto preexistente y preconfigurado (`button1`).
    *   Luego, modifica solo las propiedades que necesitan cambiar (la posición).
    ```java
    GraphicElement button2 = button1.clone(); // ¡Ahora esto es muy rápido!
    button2.setX(40);
    button2.setY(60);
    ```

#### Preguntas de Análisis:
*   Ejecuta tanto el código original como el modificado. ¿Cuál es la diferencia en el tiempo de ejecución? ¿Por qué?
*   ¿Cuál es la ventaja clave de usar `object.clone()` en lugar de `new Object()` en este escenario?
*   Este ejemplo utiliza una *copia superficial* (shallow copy). ¿Puedes pensar en un escenario donde una shallow copy podría ser problemática? (Pista: ¿qué pasaría si la clase `GraphicElement` tuviera un campo que fuera una `List` u otro objeto mutable?).

---

## 🔒 Ejercicio 2: El Patrón Singleton

### Contexto del Problema
Nuestra aplicación necesita un administrador central de `AppConfig`. Esta clase contiene configuraciones globales, como el tema y el idioma de la aplicación. Es crucial que solo exista **una única instancia** del objeto `AppConfig` en toda la aplicación. Tener múltiples objetos de configuración podría llevar a settings inconsistentes y bugs extraños.

### Código Inicial (El Problema)
El código actual no impide que múltiples partes de la aplicación creen sus propias instancias de `AppConfig`.

### Tu Tarea: Transformar el código usando el Patrón Singleton (variante de Inicialización Ansiosa - Eager Initialization).

#### Instrucciones Paso a Paso:
1.  **Haz el Constructor Privado:**
    *   Este es el paso más crítico. Evita que cualquier otra clase use la palabra clave `new` para crear una instancia de `AppConfig`.

2.  **Crea una Instancia Estática y Privada:**
    *   Dentro de la clase `AppConfig`, crea un campo `private`, `static` y `final` que contenga la única instancia de la clase.
    *   Inicialízala de manera ansiosa (eagerly) llamando directamente al constructor privado.
    ```java
    private static final AppConfig instance = new AppConfig();
    ```

3.  **Provee un Método Getter Público y Estático:**
    *   Proporciona un método público y estático (comúnmente llamado `getInstance()`) que permita a otras clases obtener una referencia a esta única instancia.
    ```java
    public static AppConfig getInstance() {
        return instance;
    }
    ```

4.  **Actualiza el Código Cliente (Main):**
    *   En la clase `Main`, reemplaza todas las llamadas a `new AppConfig()` por llamadas a `AppConfig.getInstance()`.

#### Preguntas de Análisis:
*   Después de tus cambios, ¿cuál es el resultado de `(config1 == config2)`? ¿Por qué es importante este resultado?
*   ¿Por qué el campo `instance` se declara como `static`?
*   ¿Cuál es la principal desventaja de usar el enfoque de "Inicialización Ansiosa" (Eager Initialization) demostrado aquí? (Pista: ¿cuándo se crea la instancia?). Investiga y prepárate para discutir la alternativa de "Inicialización Perezosa" (Lazy Initialization) en la próxima clase.
*   El patrón Singleton a veces es criticado. ¿Puedes pensar en una potencial desventaja, especialmente en aplicaciones grandes? (Pista: piensa en estado global y capacidad de prueba - testability).

---

## ✅ Cómo Empezar
1.  Clona o descarga este repositorio.
2.  Abre el proyecto en tu IDE favorito (Eclipse, IntelliJ IDEA, VS Code).
3.  Sigue las instrucciones en cada archivo Java para refactorizar el código según el patrón de diseño correspondiente.
4.  Ejecuta el código después de cada cambio para verificar el funcionamiento y observar las diferencias.

## 🧪 Pruebas
Asegúrate de ejecutar el método `main` en la clase `Main` después de realizar cada transformación para verificar que el patrón se ha implementado correctamente y que el comportamiento es el esperado.

¡Buena suerte y feliz coding!
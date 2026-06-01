# RPG Manager — Proyecto 2
## Patrones de Diseño GoF aplicados al RPG Manager
**Programación Orientada a Objetos · UDFJC · 2026-I**

---

## Patrones implementados

### 1. Singleton — `ConfiguracionJuego` y `GestorMisiones`

**Problema que resuelve:**
El sistema necesita un único punto de acceso global a la configuración del juego (idioma, dificultad, volumen) y a la lista de misiones activas. Sin Singleton, cada clase podría crear su propia instancia con estado diferente, generando inconsistencias entre módulos.

**Cómo se aplicó:**
- Constructor `private` para impedir `new ConfiguracionJuego()` desde afuera.
- Campo `private static volatile` con Double-Checked Locking para soporte thread-safe.
- Método `public static getInstance()` como único punto de acceso.

**Ventajas en el RPG Manager:**
- Garantiza que todos los módulos leen la misma configuración.
- Inicialización perezosa: el objeto se crea solo cuando se necesita por primera vez.
- Thread-safe: seguro si en el futuro el juego usa múltiples hilos (combate paralelo).

**Desventajas:**
- Dificulta las pruebas unitarias (estado global compartido entre tests).
- Puede ocultar dependencias: las clases que usan `getInstance()` no declaran explícitamente esa dependencia.

---

### 2. Simple Factory — `FabricaPersonajes`

**Problema que resuelve:**
El cliente necesita crear personajes de distintos tipos (Guerrero, Mago, Arquero, Paladin) a partir de una cadena de texto recibida desde la UI o un archivo de configuración. Sin Factory, el código de creación estaría disperso en toda la aplicación, acoplado a las clases concretas.

**Cómo se aplicó:**
- Catálogo `HashMap<String, Function<String, Personaje>>` que mapea tipo → constructor.
- Método estático `crear(tipo, nombre)` que desacopla al cliente de las clases concretas.
- Agregar un nuevo tipo (`"paladin"`) solo requiere una línea en el catálogo, sin tocar el método `crear`.

**Ventajas en el RPG Manager:**
- El cliente (`Principal.java`) no conoce `Guerrero`, `Mago`, etc. — solo conoce `Personaje`.
- Centraliza la creación: si cambia un constructor, solo se modifica la Factory.
- Fácilmente extensible: Open/Closed Principle.

**Desventajas:**
- No es el Factory Method GoF puro (es una simplificación sin clases creadoras abstractas).
- Si hay muchos tipos, el catálogo puede crecer demasiado (considerar Abstract Factory).

---

### 3. Observer — `ObservadorPersonaje` ⭐ Reto extra

**Problema que resuelve:**
Cuando un Personaje sube de nivel o recibe daño, varios módulos independientes (log de combate, gestor de logros, sistema de sonido) deben reaccionar. Sin Observer, `Personaje` tendría que conocer y llamar a cada módulo directamente, generando alto acoplamiento.

**Cómo se aplicó:**
- Interfaz `ObservadorPersonaje` con método `actualizar(personaje, evento)`.
- `Personaje` mantiene una lista de observadores y los notifica con `notificar()`.
- Tres implementaciones independientes: `LogCombate`, `GestorLogros`, `SistemaSonido`.

**Ventajas en el RPG Manager:**
- Agregar un nuevo observador (ej. `SistemaEstadisticas`) no modifica `Personaje`.
- Bajo acoplamiento: `Personaje` solo conoce la interfaz, no las implementaciones.
- Demuestra el Open/Closed Principle en su forma más pura.

**Desventajas:**
- Si hay muchos observadores, la notificación puede volverse costosa.
- El orden de notificación no está garantizado.

---

## Estructura del proyecto

```
src/
└── com/mycompany/rpgmanager/
    ├── Personaje.java              (clase abstracta base)
    ├── Guerrero.java
    ├── Mago.java
    ├── Arquero.java
    ├── Paladin.java
    ├── ConfiguracionJuego.java     (Singleton)
    ├── GestorMisiones.java         (Singleton + Double-Checked Locking)
    ├── FabricaPersonajes.java      (Simple Factory con catálogo)
    ├── ObservadorPersonaje.java    (interface Observer)
    ├── LogCombate.java             (Observer concreto)
    ├── GestorLogros.java           (Observer concreto)
    ├── SistemaSonido.java          (Observer concreto)
    └── Principal.java              (main — integra todos los patrones)
```

## Diagrama UML

Ver archivo `uml_rpg_manager.png` en la raíz del repositorio.

## Commits

| Commit | Descripción |
|--------|-------------|
| `[singleton][semana-15]` | ConfiguracionJuego y GestorMisiones con DCL |
| `[factory][semana-15]` | FabricaPersonajes con catálogo HashMap |
| `[observer][semana-15]` | ObservadorPersonaje, LogCombate, GestorLogros, SistemaSonido |
| `[integrador][semana-15]` | Principal.java — integración de los tres patrones |
| `[semana-15] design-patterns singleton-factory rpg-manager` | Commit final con README y UML |

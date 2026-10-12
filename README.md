# T6 - Implementación de un Árbol Binario

**Autor:** Andrés Llamuca  
**Asignatura:** Estructura de Datos (Tercer Nivel - Ingeniería de Software)  
**Facultad:** FISEI - Universidad Técnica de Ambato  

## 1. Descripción del Problema

Este proyecto implementa un sistema orientado a objetos en Java para representar la organización jerárquica de las dependencias académicas de la Universidad. Se utiliza una estructura de datos de árbol binario donde cada nodo almacena el nombre de una dependencia y cuenta con un máximo de dos hijos. El sistema permite construir el árbol, visualizarlo por consola y analizar matemáticamente su estructura (cálculo recursivo de nodos, hojas y altura).

## 2. Estructura del Árbol Binario

El sistema inicializa la siguiente jerarquía institucional, la cual contiene **9 nodos** en total, **5 nodos hoja** y una **altura de 3 aristas**:

```
              UNIVERSIDAD
              /         \
        FISEI             DIDE
        /   \             /   \
  SOFTWARE   TI     INVEST.  VINCULACIÓN
    /   \
 NIVEL1 NIVEL3
```

| Métrica | Valor |
|---------|-------|
| Total de nodos | 9 |
| Nodos hoja | 5 (NIVEL1, NIVEL3, TI, INVEST., VINCULACIÓN) |
| Altura (en aristas) | 3 |

## 3. Estructura del Proyecto

```
src/
├── app/
│   └── Main.java            # Punto de entrada: construye y analiza el árbol
├── modelo/
│   └── Nodo.java            # Nodo con dato y referencias a hijo izquierdo/derecho
└── negocio/
    └── ArbolBinario.java    # Lógica del árbol: visualización y cálculos recursivos
```

## 4. Instrucciones de Ejecución

El proyecto está configurado para ejecutarse desde cualquier IDE (como Visual Studio Code) o mediante la terminal.

1. Clona este repositorio:
   ```bash
   git clone https://github.com/llamucaandres161-prog/T6-ArbolBinario-LlamucaAndres.git
   ```
2. Navega a la carpeta principal del proyecto y entra a `src`:
   ```bash
   cd T6-ArbolBinario-LlamucaAndres/src
   ```
3. Compila los archivos:
   ```bash
   javac app/Main.java modelo/Nodo.java negocio/ArbolBinario.java
   ```
4. Ejecuta el programa:
   ```bash
   java app.Main
   ```

> **Requisito:** tener instalado el JDK (Java 8 o superior).

## 5. Funcionalidades

- Construcción de un árbol binario de dependencias académicas.
- Visualización del árbol por consola.
- Cálculo recursivo del número total de nodos.
- Cálculo recursivo del número de nodos hoja.
- Cálculo recursivo de la altura del árbol.

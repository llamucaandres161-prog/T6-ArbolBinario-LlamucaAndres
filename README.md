T6 - Implementación de un Árbol Binario

Autor: Andrés Llamuca
Asignatura: Estructura de Datos (Tercer Nivel - Ingeniería de Software)
Facultad: FISEI - Universidad Técnica de Ambato

1. Descripción del Problema

Este proyecto implementa un sistema orientado a objetos en Java para representar la organización jerárquica de las dependencias académicas de la Universidad. Se utiliza una estructura de datos de árbol binario donde cada nodo almacena el nombre de una dependencia y cuenta con un máximo de dos hijos. El sistema permite construir el árbol, visualizarlo por consola y analizar su estructura (cálculo recursivo de nodos, hojas y altura) mediante un menú interactivo.

2. Estructura del Árbol Binario

El sistema inicializa la siguiente jerarquía institucional, la cual contiene 9 nodos, 5 hojas y una altura de 3 aristas:

              UNIVERSIDAD
              /         \
        FISEI             DIDE
        /   \             /   \
  SOFTWARE   TI     INVEST.  VINCULACIÓN
    /   \
 NIVEL1 NIVEL3
3. Estructura del Proyecto
T6-ArbolBinario-LlamucaAndres/
 ├── README.md
 └── src/
     ├── modelo/
     │   └── Nodo.java            # Nodo genérico Nodo<T>: dato, hijos, esHoja(), grado()
     ├── negocio/
     │   └── ArbolBinario.java    # Lógica del árbol: inserción, conteo, altura, visualización
     └── app/
         └── Main.java            # Menú interactivo con Scanner y casos de prueba
4. Instrucciones de Ejecución
Clona este repositorio:
bash
   git clone https://github.com/llamucaandres161-prog/T6-ArbolBinario-LlamucaAndres.git
Entra a la carpeta src:
bash
   cd T6-ArbolBinario-LlamucaAndres/src
Compila:
bash
   javac app/Main.java modelo/Nodo.java negocio/ArbolBinario.java
Ejecuta:
bash
   java app.Main

Requisito: JDK 8 o superior.

5. Menú del Programa
Opción	Acción
1	Crear el árbol inicial
2	Visualizar el árbol
3	Consultar el número de nodos
4	Consultar el número de hojas
5	Consultar la altura
6	Consultar el grado de un nodo
7	Verificar si un nodo es hoja
8	Ejecutar casos de prueba
0	Salir
6. Resultados y Casos de Prueba
Caso	Resultado esperado
Árbol vacío	0 nodos, altura -1
Árbol con un solo nodo	1 hoja, altura 0
Árbol inicial	9 nodos, 5 hojas, altura 3
Insertar en posición ocupada	Se rechaza con un mensaje de error
Grado de FISEI y SOFTWARE	2 y 2
Capturas de ejecución

Agregar aquí las capturas de pantalla (menú, visualización del árbol y casos de prueba).

7. Restricciones Cumplidas
No se usan TreeSet, TreeMap, PriorityQueue, NavigableSet ni NavigableMap.
El conteo de nodos, el conteo de hojas y la altura se implementan con recursividad.

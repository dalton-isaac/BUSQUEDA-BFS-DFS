# Proyecto LigaPro - Grafos en Java (Maven)

Proyecto universitario de **Estructuras de Datos** que modela los partidos jugados en la **LigaPro** mediante un **Grafo No Dirigido** implementado con una **Matriz de Adyacencia**, aplicando algoritmos de recorrido y búsqueda:
- **BFS (Breadth-First Search / Búsqueda en Anchura)** usando una **Cola (FIFO)**.
- **DFS (Depth-First Search / Búsqueda en Profundidad)** usando **Recursión / Pila (LIFO)**.
- **Búsqueda de Caminos** (ruta de partidos jugados entre dos equipos).

---

## 📁 Estructura del Proyecto Maven

```text
├── pom.xml                                   # Configuración de Maven y dependencias (JUnit 5)
├── README.md                                 # Documentación del proyecto
└── src
    └── ec
        └── edu
            └── puce                          # Paquete ec.edu.puce
                ├── LigaPro.java              # Clase principal con Matriz, BFS y DFS
                └── LigaProTest.java          # Pruebas unitarias con JUnit 5
```

---

## ⚽ Modelado del Grafo

### Paquete:
`package ec.edu.puce;`

### Vértices (Equipos):
- `0`: **LDU** (Liga de Quito)
- `1`: **BSC** (Barcelona SC)
- `2`: **UIO** (Deportivo Quito)
- `3`: **EME** (Emelec)

### Aristas (Partidos Jugados):
- Si el equipo $i$ jugó contra el equipo $j$, entonces:
  $$\text{matrizAdyacencia}[i][j] = 1 \quad \text{y} \quad \text{matrizAdyacencia}[j][i] = 1$$

### Matriz de Adyacencia Resultante:
```text
Equipo	[LDU]	[BSC]	[UIO]	[EME]	
[LDU]	  0	  1	  1	  1	
[BSC]	  1	  0	  0	  0	
[UIO]	  1	  0	  0	  0	
[EME]	  1	  0	  0	  0	
```

---

## 🔍 Explicación de los Algoritmos

### 1. Búsqueda en Anchura (BFS - Breadth-First Search)
- **Estructura de datos:** Cola (`Queue<Integer>` / `LinkedList` - FIFO).
- **Funcionamiento:**
  1. Se marca el nodo de inicio como visitado y se añade a la cola.
  2. Mientras la cola no esté vacía, se extrae el nodo al frente y se exploran **todos sus vecinos directos** (nivel 1).
  3. Los vecinos no visitados se marcan y se encolan para ser procesados posteriormente.
- **Caso de uso:** Encontrar el camino con menor número de partidos (camino más corto no ponderado).

### 2. Búsqueda en Profundidad (DFS - Depth-First Search)
- **Estructura de datos:** Pila (`Stack<Integer>` / Pila de llamadas del sistema por Recursión - LIFO).
- **Funcionamiento:**
  1. Se visita el nodo actual y se marca como visitado.
  2. Se explora el **primer vecino no visitado**, avanzando tan profundo como sea posible por esa rama antes de retroceder (*backtracking*).
- **Caso de uso:** Detección de ciclos, conectividad, exploración exhaustiva de caminos.

---

## 🚀 Cómo Ejecutar el Proyecto

### Opción 1: Con Maven (Línea de comandos)
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación principal
mvn exec:java

# Ejecutar las pruebas unitarias
mvn test
```

### Opción 2: Con Java directo (Terminal / PowerShell)
```bash
# Compilar clases
javac -d bin src/ec/edu/puce/LigaPro.java

# Ejecutar clase principal
java -cp bin ec.edu.puce.LigaPro
```

### Opción 3: En VS Code / Antigravity IDE / Eclipse
1. Abre el archivo [LigaPro.java](file:///c:/Users/ISAAC/Desktop/PUCE/Segundo%20Semestre/Estructuras%20de%20datos/Parcial%203/Gestión%20de%20Memoria%20Dinámica%20y%20Estructuras%20Lineales/src/ec/edu/puce/LigaPro.java).
2. Haz clic en **Run** o presiona `F5` / `Ctrl + F5`.

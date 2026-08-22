# Proyecto LigaPro - Grafos en Java (Maven)

Proyecto de **Estructuras de Datos** que modela los partidos jugados en la **LigaPro** mediante un **Grafo No Dirigido** implementado con una **Matriz de Adyacencia**, aplicando algoritmos de recorrido y búsqueda:
- **BFS (Breadth-First Search / Búsqueda en Anchura)** usando una **Cola (FIFO)**.
- **DFS (Depth-First Search / Búsqueda en Profundidad)** usando **Recursión / Pila (LIFO)**.
- **Búsquedas de Conectividad** (`buscarBFS` y `buscarDFS`).

---

## 📁 Estructura Estándar de Maven

```text
├── pom.xml                                   # Configuración de Maven
├── README.md                                 # Documentación del proyecto
├── .gitignore                                # Exclusión de binarios y temporales
└── src
    └── main
        └── java
            └── ec
                └── edu
                    └── puce                  # Paquete: ec.edu.puce
                        ├── Grafo.java        # Clase Grafo: Matriz, BFS y DFS
                        └── LigaPro.java      # Clase principal ejecutable (main)
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

### Matriz de Adyacencia Resultante:
```text
	LDU	BSC	UIO	EME	
LDU	 0	 1	 1	 1	
BSC	 1	 0	 0	 0	
UIO	 1	 0	 0	 0	
EME	 1	 0	 0	 0	
```

---

## 🔍 Métodos Implementados en `Grafo.java`

- `agregarArista(int origen, int destino, boolean noDirigido)`: Agrega partidos entre equipos.
- `imprimirMatriz()`: Muestra la matriz de adyacencia formateada.
- `obtenerIndice(String nombre)`: Obtiene el índice numérico correspondiente a un equipo.
- `bfs(String nombreInicio)` / `bfs(int nodoInicio)`: Recorrido en anchura con Cola (FIFO).
- `buscarBFS(String origen, String destino)`: Verifica conectividad entre equipos usando BFS.
- `dfs(String nombreInicio)` / `dfs(int nodoInicio)`: Recorrido en profundidad con Recursión (LIFO).
- `buscarDFS(String origen, String destino)`: Verifica conectividad entre equipos usando DFS recursivo.

---

## 🚀 Cómo Ejecutar el Proyecto

### Opción 1: Desde el IDE (VS Code / Eclipse / IntelliJ)
Abre [LigaPro.java](file:///c:/Users/ISAAC/Desktop/PUCE/Segundo%20Semestre/Estructuras%20de%20datos/Parcial%203/Gestión%20de%20Memoria%20Dinámica%20y%20Estructuras%20Lineales/src/main/java/ec/edu/puce/LigaPro.java) y presiona **`Ctrl + F5`** (o haz clic en el botón de **Run ▶️**).

### Opción 2: Con Maven
```bash
mvn clean compile
mvn exec:java
```

### Opción 3: Con Java directo (Terminal)
```bash
javac -d bin src/main/java/ec/edu/puce/Grafo.java src/main/java/ec/edu/puce/LigaPro.java
java -cp bin ec.edu.puce.LigaPro
```

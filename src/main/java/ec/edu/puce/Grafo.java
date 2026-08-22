package ec.edu.puce;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================================
 * Clase Grafo: Representación de Grafo mediante Matriz de Adyacencia
 * Algoritmos: Búsqueda en Anchura (BFS) y Búsqueda en Profundidad (DFS)
 * Paquete: ec.edu.puce
 * Asignatura: Estructuras de Datos
 * ============================================================================
 */
public class Grafo {

    private int numVertices;
    private int[][] matrizAdyacencia;
    private String[] nombresVertices;

    // Constructor creando una matriz vacía
    public Grafo(int numVertices, String[] nombresVertices) {
        this.numVertices = numVertices;
        this.nombresVertices = nombresVertices;
        this.matrizAdyacencia = new int[numVertices][numVertices];
    }

    // Constructor con matriz ya existente
    public Grafo(int[][] matrizAdyacencia, String[] nombresVertices) {
        this.numVertices = matrizAdyacencia.length;
        this.nombresVertices = nombresVertices;
        this.matrizAdyacencia = matrizAdyacencia;
    }

    /**
     * Registra una arista/conexión entre dos nodos.
     * @param origen Índice del nodo origen
     * @param destino Índice del nodo destino
     * @param noDirigido True si la conexión es bidireccional (grafo no dirigido)
     */
    public void agregarArista(int origen, int destino, boolean noDirigido) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            matrizAdyacencia[origen][destino] = 1;
            if (noDirigido) {
                matrizAdyacencia[destino][origen] = 1;
            }
        } else {
            System.out.println("Error: Índices fuera de rango (" + origen + ", " + destino + ")");
        }
    }

    /**
     * Muestra en consola la matriz de adyacencia formateada con los nombres de los nodos.
     */
    public void imprimirMatriz() {
        System.out.println("\n--- MATRIZ DE ADYACENCIA ---");
        System.out.print("\t");
        for (String nombre : nombresVertices) {
            System.out.print(nombre + "\t");
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.print(nombresVertices[i] + "\t");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrizAdyacencia[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------------------------\n");
    }

    /**
     * Obtiene el índice de un nodo a partir de su nombre.
     */
    public int obtenerIndice(String nombre) {
        for (int i = 0; i < nombresVertices.length; i++) {
            if (nombresVertices[i].equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    // =========================================================================
    // BFS (Breadth-First Search) - Búsqueda / Recorrido en Anchura
    // =========================================================================

    /**
     * Recorrido BFS a partir del nombre del nodo inicial.
     */
    public void bfs(String nombreInicio) {
        int inicio = obtenerIndice(nombreInicio);
        if (inicio == -1) {
            System.out.println("El nodo '" + nombreInicio + "' no existe en el grafo.");
            return;
        }
        bfs(inicio);
    }

    /**
     * Recorrido BFS a partir del índice del nodo inicial.
     * Utiliza una estructura de datos Cola (Queue / FIFO).
     */
    public void bfs(int nodoInicio) {
        boolean[] visitados = new boolean[numVertices];
        Queue<Integer> cola = new LinkedList<>();

        // Marcar el nodo inicial como visitado y encolarlo
        visitados[nodoInicio] = true;
        cola.add(nodoInicio);

        System.out.print("Recorrido BFS (Anchura) desde [" + nombresVertices[nodoInicio] + "]: ");

        while (!cola.isEmpty()) {
            // Desencolar el nodo actual
            int actual = cola.poll();
            System.out.print(nombresVertices[actual] + " ");

            // Revisar todos los vecinos adyacentes
            for (int vecino = 0; vecino < numVertices; vecino++) {
                if (matrizAdyacencia[actual][vecino] == 1 && !visitados[vecino]) {
                    visitados[vecino] = true;
                    cola.add(vecino);
                }
            }
        }
        System.out.println();
    }

    /**
     * Búsqueda BFS para verificar si un nodo destino es alcanzable desde un nodo origen.
     */
    public boolean buscarBFS(String nombreOrigen, String nombreDestino) {
        int origen = obtenerIndice(nombreOrigen);
        int destino = obtenerIndice(nombreDestino);

        if (origen == -1 || destino == -1) {
            System.out.println("Uno de los nodos ingresados no existe.");
            return false;
        }

        boolean[] visitados = new boolean[numVertices];
        Queue<Integer> cola = new LinkedList<>();

        visitados[origen] = true;
        cola.add(origen);

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            if (actual == destino) {
                return true;
            }

            for (int vecino = 0; vecino < numVertices; vecino++) {
                if (matrizAdyacencia[actual][vecino] == 1 && !visitados[vecino]) {
                    visitados[vecino] = true;
                    cola.add(vecino);
                }
            }
        }
        return false;
    }

    // =========================================================================
    // DFS (Depth-First Search) - Búsqueda / Recorrido en Profundidad
    // =========================================================================

    /**
     * Recorrido DFS a partir del nombre del nodo inicial.
     */
    public void dfs(String nombreInicio) {
        int inicio = obtenerIndice(nombreInicio);
        if (inicio == -1) {
            System.out.println("El nodo '" + nombreInicio + "' no existe en el grafo.");
            return;
        }
        dfs(inicio);
    }

    /**
     * Recorrido DFS a partir del índice del nodo inicial.
     * Utiliza Recursión (pila de llamadas del sistema / LIFO).
     */
    public void dfs(int nodoInicio) {
        boolean[] visitados = new boolean[numVertices];
        System.out.print("Recorrido DFS (Profundidad) desde [" + nombresVertices[nodoInicio] + "]: ");
        dfsRecursivo(nodoInicio, visitados);
        System.out.println();
    }

    private void dfsRecursivo(int actual, boolean[] visitados) {
        // Marcar nodo actual como visitado e imprimirlo
        visitados[actual] = true;
        System.out.print(nombresVertices[actual] + " ");

        // Explorar recursivamente cada vecino adyacente no visitado
        for (int vecino = 0; vecino < numVertices; vecino++) {
            if (matrizAdyacencia[actual][vecino] == 1 && !visitados[vecino]) {
                dfsRecursivo(vecino, visitados);
            }
        }
    }

    /**
     * Búsqueda DFS para verificar si un nodo destino es alcanzable desde un nodo origen.
     */
    public boolean buscarDFS(String nombreOrigen, String nombreDestino) {
        int origen = obtenerIndice(nombreOrigen);
        int destino = obtenerIndice(nombreDestino);

        if (origen == -1 || destino == -1) {
            System.out.println("Uno de los nodos ingresados no existe.");
            return false;
        }

        boolean[] visitados = new boolean[numVertices];
        return dfsBuscarRecursivo(origen, destino, visitados);
    }

    private boolean dfsBuscarRecursivo(int actual, int destino, boolean[] visitados) {
        if (actual == destino) {
            return true;
        }

        visitados[actual] = true;

        for (int vecino = 0; vecino < numVertices; vecino++) {
            if (matrizAdyacencia[actual][vecino] == 1 && !visitados[vecino]) {
                if (dfsBuscarRecursivo(vecino, destino, visitados)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Getters
    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public String[] getNombresVertices() {
        return nombresVertices;
    }

    public int getNumVertices() {
        return numVertices;
    }
}

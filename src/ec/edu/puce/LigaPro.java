package ec.edu.puce;

import java.util.*;

/**
 * ============================================================================
 * Proyecto: Modelado de LigaPro con Grafos (Matriz de Adyacencia)
 * Algoritmos: Búsqueda en Anchura (BFS) y Búsqueda en Profundidad (DFS)
 * Paquete: ec.edu.puce
 * Asignatura: Estructuras de Datos
 * ============================================================================
 */
public class LigaPro {

    // Nombres de los equipos (Vértices del grafo)
    private final String[] equipos;
    // Matriz de adyacencia (Aristas / Partidos jugados)
    private final int[][] matrizAdyacencia;
    // Número total de vértices
    private final int numVertices;

    /**
     * Constructor para inicializar el grafo con una lista de equipos.
     *
     * @param equipos Arreglo de nombres de equipos
     */
    public LigaPro(String[] equipos) {
        this.equipos = equipos;
        this.numVertices = equipos.length;
        this.matrizAdyacencia = new int[numVertices][numVertices];
    }

    /**
     * Registra un partido jugado entre dos equipos (grafo no dirigido).
     *
     * @param origen  Índice del primer equipo
     * @param destino Índice del segundo equipo
     */
    public void registrarPartido(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            matrizAdyacencia[origen][destino] = 1;
            matrizAdyacencia[destino][origen] = 1;
        } else {
            System.err.println("Error: Índices de equipo inválidos (" + origen + ", " + destino + ")");
        }
    }

    /**
     * Imprime la matriz de adyacencia formateada con nombres de filas y columnas.
     */
    public void imprimirMatriz() {
        System.out.println("\n=======================================================");
        System.out.println("       MATRIZ DE ADYACENCIA (PARTIDOS JUGADOS)");
        System.out.println("=======================================================");

        // Encabezado de columnas
        System.out.print("Equipo\t");
        for (String equipo : equipos) {
            System.out.print("[" + equipo + "]\t");
        }
        System.out.println();

        // Filas de la matriz
        for (int i = 0; i < numVertices; i++) {
            System.out.print("[" + equipos[i] + "]\t");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrizAdyacencia[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("=======================================================\n");
    }

    // =========================================================================
    //  ALGORITMO 1: BFS (Breadth-First Search / Búsqueda en Anchura)
    // =========================================================================

    /**
     * Realiza un recorrido en anchura (BFS) comenzando desde un nodo de inicio.
     * Utiliza una COLA (Queue - FIFO) para explorar nivel por nivel.
     *
     * @param nodoInicio Índice del nodo inicial
     * @return Lista con el orden de equipos visitados
     */
    public List<String> bfs(int nodoInicio) {
        if (nodoInicio < 0 || nodoInicio >= numVertices) {
            throw new IllegalArgumentException("Índice de nodo de inicio no válido: " + nodoInicio);
        }

        List<String> recorrido = new ArrayList<>();
        boolean[] visitado = new boolean[numVertices];
        Queue<Integer> cola = new LinkedList<>();

        System.out.println("-------------------------------------------------------");
        System.out.println(" >>> INICIANDO RECORRIDO BFS (Búsqueda en Anchura) <<<");
        System.out.println(" Nodo Inicial: [" + nodoInicio + "] " + equipos[nodoInicio]);
        System.out.println(" Estructura usada: COLA (Queue - FIFO)");
        System.out.println("-------------------------------------------------------");

        // 1. Marcar el nodo inicial como visitado y encolarlo
        visitado[nodoInicio] = true;
        cola.add(nodoInicio);

        int paso = 1;
        while (!cola.isEmpty()) {
            // 2. Desencolar el elemento al frente de la cola
            int actual = cola.poll();
            recorrido.add(equipos[actual]);

            System.out.println("Paso " + paso + ": Procesando [" + equipos[actual] + "] (Índice " + actual + ")");
            paso++;

            // 3. Explorar todos los vecinos adyacentes del nodo actual
            for (int vecino = 0; vecino < numVertices; vecino++) {
                if (matrizAdyacencia[actual][vecino] == 1 && !visitado[vecino]) {
                    visitado[vecino] = true;
                    cola.add(vecino);
                    System.out.println("   -> Encolando vecino directo: [" + equipos[vecino] + "] (Nivel siguiente)");
                }
            }
        }

        System.out.println(">> Orden de visita BFS: " + String.join(" -> ", recorrido));
        return recorrido;
    }

    /**
     * Encuentra el camino más corto entre dos equipos usando BFS.
     *
     * @param origen  Índice del equipo origen
     * @param destino Índice del equipo destino
     * @return Lista con el camino de equipos o lista vacía si no hay conexión
     */
    public List<String> bfsCaminoMasCorto(int origen, int destino) {
        boolean[] visitado = new boolean[numVertices];
        int[] padre = new int[numVertices];
        Arrays.fill(padre, -1);

        Queue<Integer> cola = new LinkedList<>();
        visitado[origen] = true;
        cola.add(origen);

        boolean encontrado = false;
        while (!cola.isEmpty()) {
            int actual = cola.poll();

            if (actual == destino) {
                encontrado = true;
                break;
            }

            for (int vecino = 0; vecino < numVertices; vecino++) {
                if (matrizAdyacencia[actual][vecino] == 1 && !visitado[vecino]) {
                    visitado[vecino] = true;
                    padre[vecino] = actual;
                    cola.add(vecino);
                }
            }
        }

        List<String> camino = new ArrayList<>();
        if (encontrado) {
            int curr = destino;
            while (curr != -1) {
                camino.add(0, equipos[curr]);
                curr = padre[curr];
            }
        }
        return camino;
    }

    // =========================================================================
    //  ALGORITMO 2: DFS (Depth-First Search / Búsqueda en Profundidad)
    // =========================================================================

    /**
     * Inicia el recorrido en profundidad (DFS) en su versión RECURSIVA.
     * Utiliza la PILA de llamadas del sistema (LIFO) para explorar rama por rama.
     *
     * @param nodoInicio Índice del nodo inicial
     * @return Lista con el orden de equipos visitados
     */
    public List<String> dfs(int nodoInicio) {
        if (nodoInicio < 0 || nodoInicio >= numVertices) {
            throw new IllegalArgumentException("Índice de nodo de inicio no válido: " + nodoInicio);
        }

        List<String> recorrido = new ArrayList<>();
        boolean[] visitado = new boolean[numVertices];

        System.out.println("\n-------------------------------------------------------");
        System.out.println(" >>> INICIANDO RECORRIDO DFS (Búsqueda en Profundidad) <<<");
        System.out.println(" Nodo Inicial: [" + nodoInicio + "] " + equipos[nodoInicio]);
        System.out.println(" Estructura usada: RECURSIÓN / PILA (Stack - LIFO)");
        System.out.println("-------------------------------------------------------");

        dfsRecursivo(nodoInicio, visitado, recorrido, 1);

        System.out.println(">> Orden de visita DFS: " + String.join(" -> ", recorrido));
        return recorrido;
    }

    /**
     * Función auxiliar recursiva para DFS.
     */
    private int dfsRecursivo(int actual, boolean[] visitado, List<String> recorrido, int paso) {
        visitado[actual] = true;
        recorrido.add(equipos[actual]);
        System.out.println("Paso " + paso + ": Visitando [" + equipos[actual] + "] (Índice " + actual + ")");
        paso++;

        for (int vecino = 0; vecino < numVertices; vecino++) {
            if (matrizAdyacencia[actual][vecino] == 1 && !visitado[vecino]) {
                System.out.println("   -> Descendiendo en profundidad desde [" + equipos[actual] + "] hacia: [" + equipos[vecino] + "]");
                paso = dfsRecursivo(vecino, visitado, recorrido, paso);
                System.out.println("   <- Regresando (backtracking) a [" + equipos[actual] + "]");
            }
        }
        return paso;
    }

    /**
     * Versión ITERATIVA de DFS utilizando explícitamente una Pila (Stack).
     *
     * @param nodoInicio Índice del nodo inicial
     * @return Lista con el orden de equipos visitados
     */
    public List<String> dfsIterativo(int nodoInicio) {
        List<String> recorrido = new ArrayList<>();
        boolean[] visitado = new boolean[numVertices];
        Stack<Integer> pila = new Stack<>();

        pila.push(nodoInicio);

        while (!pila.isEmpty()) {
            int actual = pila.pop();

            if (!visitado[actual]) {
                visitado[actual] = true;
                recorrido.add(equipos[actual]);

                // Se apilan los vecinos en orden inverso para que el menor índice se procese primero
                for (int vecino = numVertices - 1; vecino >= 0; vecino--) {
                    if (matrizAdyacencia[actual][vecino] == 1 && !visitado[vecino]) {
                        pila.push(vecino);
                    }
                }
            }
        }
        return recorrido;
    }

    // Getters
    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public String[] getEquipos() {
        return equipos;
    }

    public int getNumVertices() {
        return numVertices;
    }

    // =========================================================================
    //  MÉTODO PRINCIPAL (MAIN)
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println("   PROYECTO: LIGA PRO - GRAFOS CON BFS Y DFS");
        System.out.println("   Paquete: ec.edu.puce");
        System.out.println("=======================================================");

        /**
         * Definición de índices de los equipos:
         * 0 = LDU (Liga de Quito)
         * 1 = BSC (Barcelona SC)
         * 2 = UIO (Deportivo Quito)
         * 3 = EME (Emelec)
         */
        String[] equipos = { "LDU", "BSC", "UIO", "EME" };

        LigaPro ligaPro = new LigaPro(equipos);

        // -------------------------------------------------------------
        // Registro de partidos jugados (código base)
        // -------------------------------------------------------------
        // Partido jugado entre LDU (0) y BSC (1)
        ligaPro.registrarPartido(0, 1);

        // Partido jugado entre LDU (0) y D.Quito/UIO (2)
        ligaPro.registrarPartido(0, 2);

        // Partido jugado entre LDU (0) y EME (3)
        ligaPro.registrarPartido(0, 3);

        // 1. Mostrar la matriz de adyacencia
        ligaPro.imprimirMatriz();

        // 2. Ejecutar Recorrido en Anchura (BFS) desde LDU (0)
        System.out.println("\n>>> 1. EJECUTANDO BÚSQUEDA EN ANCHURA (BFS) DESDE LDU (0):");
        ligaPro.bfs(0);

        // 3. Ejecutar Recorrido en Profundidad (DFS) desde LDU (0)
        System.out.println("\n>>> 2. EJECUTANDO BÚSQUEDA EN PROFUNDIDAD (DFS) DESDE LDU (0):");
        ligaPro.dfs(0);

        // 4. Aplicación: Encontrar camino de partidos entre dos equipos
        System.out.println("\n-------------------------------------------------------");
        System.out.println(" >>> APLICACIÓN: CAMINO DE PARTIDOS ENTRE EQUIPOS <<<");
        System.out.println("-------------------------------------------------------");
        int origen = 1;  // BSC
        int destino = 3; // EME
        List<String> camino = ligaPro.bfsCaminoMasCorto(origen, destino);
        System.out.println("¿Cómo se conecta [" + equipos[origen] + "] con [" + equipos[destino] + "] a través de partidos jugados?");
        if (!camino.isEmpty()) {
            System.out.println("Camino más corto encontrado: " + String.join(" <--> ", camino));
        } else {
            System.out.println("No existe camino entre los dos equipos.");
        }

        // -------------------------------------------------------------
        // Ejemplo 2: Grafo con conexiones en cadena
        // -------------------------------------------------------------
        System.out.println("\n=======================================================");
        System.out.println(" EJEMPLO 2: GRAFO CON CONEXIONES EN CADENA");
        System.out.println(" (Demuestra la diferencia visual entre BFS y DFS)");
        System.out.println(" Conexiones: LDU-BSC, BSC-UIO, UIO-EME");
        System.out.println("=======================================================");
        LigaPro ligaProCadena = new LigaPro(equipos);
        ligaProCadena.registrarPartido(0, 1); // LDU - BSC
        ligaProCadena.registrarPartido(1, 2); // BSC - UIO
        ligaProCadena.registrarPartido(2, 3); // UIO - EME

        ligaProCadena.imprimirMatriz();

        System.out.println("--- BFS en Cadena desde LDU (0) ---");
        ligaProCadena.bfs(0);

        System.out.println("\n--- DFS en Cadena desde LDU (0) ---");
        ligaProCadena.dfs(0);

        System.out.println("\n=======================================================");
        System.out.println("           FIN DE LA EJECUCIÓN DEL PROGRAMA");
        System.out.println("=======================================================");
    }
}

package ec.edu.puce;

/**
 * ============================================================================
 * Proyecto: LigaPro - Aplicación de Grafos con Búsqueda BFS y DFS
 * Paquete: ec.edu.puce
 * Asignatura: Estructuras de Datos
 * ============================================================================
 */
public class LigaPro {

    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println("       PROYECTO LIGA PRO: GRAFOS (BFS Y DFS)");
        System.out.println("=======================================================");

        /**
         * 0 = LDU (Liga de Quito)
         * 1 = BSC (Barcelona SC)
         * 2 = UIO (Deportivo Quito)
         * 3 = EME (Emelec)
         */
        String[] equipos = { "LDU", "BSC", "UIO", "EME" };

        // 1. Crear el Grafo de la LigaPro con los 4 equipos
        Grafo grafoLiga = new Grafo(4, equipos);

        // 2. Registrar partidos jugados (Grafo no dirigido / bidireccional)
        // Registrar el partido jugado entre LDU (0) y BSC (1)
        grafoLiga.agregarArista(0, 1, true);

        // Registrar el partido jugado entre LDU (0) y D.Quito/UIO (2)
        grafoLiga.agregarArista(0, 2, true);

        // Registrar el partido jugado entre LDU (0) y EME (3)
        grafoLiga.agregarArista(0, 3, true);

        // 3. Imprimir la matriz de adyacencia de partidos jugados
        grafoLiga.imprimirMatriz();

        // 4. Recorrido BFS (Búsqueda en Anchura)
        System.out.println("-------------------------------------------------------");
        System.out.println("1. RECORRIDO BFS (Búsqueda en Anchura - COLA/FIFO):");
        grafoLiga.bfs("LDU");
        grafoLiga.bfs("BSC");

        // 5. Recorrido DFS (Búsqueda en Profundidad)
        System.out.println("\n-------------------------------------------------------");
        System.out.println("2. RECORRIDO DFS (Búsqueda en Profundidad - RECURSIÓN):");
        grafoLiga.dfs("LDU");
        grafoLiga.dfs("BSC");

        // 6. Funciones de Búsqueda de Conexión (¿Existe camino entre dos equipos?)
        System.out.println("\n-------------------------------------------------------");
        System.out.println("3. BÚSQUEDAS ESPECÍFICAS ENTRE EQUIPOS:");
        System.out.println("-------------------------------------------------------");

        // Búsqueda con BFS
        boolean conectadoBFS = grafoLiga.buscarBFS("BSC", "EME");
        System.out.println("¿Están conectados BSC y EME mediante partidos (BFS)? -> " + (conectadoBFS ? "SÍ CONECTADOS" : "NO CONECTADOS"));

        // Búsqueda con DFS
        boolean conectadoDFS = grafoLiga.buscarDFS("UIO", "BSC");
        System.out.println("¿Están conectados UIO y BSC mediante partidos (DFS)? -> " + (conectadoDFS ? "SÍ CONECTADOS" : "NO CONECTADOS"));

        System.out.println("=======================================================");
    }
}

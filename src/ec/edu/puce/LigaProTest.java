package ec.edu.puce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Grafo y sus funciones de búsqueda BFS y DFS.
 * Paquete: ec.edu.puce
 */
public class LigaProTest {

    private Grafo grafo;
    private final String[] equipos = { "LDU", "BSC", "UIO", "EME" };

    @BeforeEach
    public void setUp() {
        grafo = new Grafo(4, equipos);
        // LDU (0) juega contra BSC (1), UIO (2) y EME (3)
        grafo.agregarArista(0, 1, true);
        grafo.agregarArista(0, 2, true);
        grafo.agregarArista(0, 3, true);
    }

    @Test
    public void testMatrizAdyacenciaSimetria() {
        int[][] matriz = grafo.getMatrizAdyacencia();
        assertEquals(1, matriz[0][1]);
        assertEquals(1, matriz[1][0]);
        assertEquals(1, matriz[0][2]);
        assertEquals(1, matriz[2][0]);
        assertEquals(1, matriz[0][3]);
        assertEquals(1, matriz[3][0]);

        // No conectados directamente
        assertEquals(0, matriz[1][2]);
        assertEquals(0, matriz[2][3]);
    }

    @Test
    public void testBuscarBFS() {
        // BSC se conecta a EME a través de LDU
        assertTrue(grafo.buscarBFS("BSC", "EME"));
        assertTrue(grafo.buscarBFS("LDU", "UIO"));
        // Nodo inexistente
        assertFalse(grafo.buscarBFS("AUCAS", "LDU"));
    }

    @Test
    public void testBuscarDFS() {
        // UIO se conecta a BSC a través de LDU
        assertTrue(grafo.buscarDFS("UIO", "BSC"));
        assertTrue(grafo.buscarDFS("EME", "LDU"));
        // Nodo inexistente
        assertFalse(grafo.buscarDFS("CATOLICA", "BSC"));
    }

    @Test
    public void testObtenerIndice() {
        assertEquals(0, grafo.obtenerIndice("LDU"));
        assertEquals(1, grafo.obtenerIndice("BSC"));
        assertEquals(2, grafo.obtenerIndice("UIO"));
        assertEquals(3, grafo.obtenerIndice("EME"));
        assertEquals(-1, grafo.obtenerIndice("DESCONOCIDO"));
    }
}

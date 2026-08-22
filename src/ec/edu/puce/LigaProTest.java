package ec.edu.puce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para validar la Matriz de Adyacencia, BFS, DFS y camino más corto.
 * Paquete: ec.edu.puce
 */
public class LigaProTest {

    private LigaPro ligaPro;
    private final String[] equipos = { "LDU", "BSC", "UIO", "EME" };

    @BeforeEach
    public void setUp() {
        ligaPro = new LigaPro(equipos);
        // LDU juega contra BSC, UIO y EME
        ligaPro.registrarPartido(0, 1);
        ligaPro.registrarPartido(0, 2);
        ligaPro.registrarPartido(0, 3);
    }

    @Test
    public void testMatrizAdyacenciaSimetria() {
        int[][] matriz = ligaPro.getMatrizAdyacencia();
        // Verificar que es simétrica (grafo no dirigido)
        assertEquals(1, matriz[0][1]);
        assertEquals(1, matriz[1][0]);
        assertEquals(1, matriz[0][2]);
        assertEquals(2, matriz[0][2] + matriz[2][0]);
        assertEquals(1, matriz[0][3]);
        assertEquals(1, matriz[3][0]);

        // Verificar partidos no jugados
        assertEquals(0, matriz[1][2]);
        assertEquals(0, matriz[2][3]);
        assertEquals(0, matriz[0][0]); // Diagonal principal en 0
    }

    @Test
    public void testRecorridoBFSDesdeLDU() {
        List<String> recorrido = ligaPro.bfs(0);
        assertNotNull(recorrido);
        assertEquals(4, recorrido.size());
        // En BFS desde LDU (0), se visita primero LDU, luego sus vecinos directos en orden
        assertEquals("LDU", recorrido.get(0));
        assertTrue(recorrido.contains("BSC"));
        assertTrue(recorrido.contains("UIO"));
        assertTrue(recorrido.contains("EME"));
    }

    @Test
    public void testRecorridoDFSDesdeLDU() {
        List<String> recorrido = ligaPro.dfs(0);
        assertNotNull(recorrido);
        assertEquals(4, recorrido.size());
        assertEquals("LDU", recorrido.get(0));
    }

    @Test
    public void testDFSIterativoEquivalencia() {
        List<String> recorridoRecursivo = ligaPro.dfs(0);
        List<String> recorridoIterativo = ligaPro.dfsIterativo(0);
        assertEquals(recorridoRecursivo, recorridoIterativo);
    }

    @Test
    public void testCaminoMasCortoEntreEquipos() {
        // Camino de BSC (1) a EME (3) pasando por LDU (0)
        List<String> camino = ligaPro.bfsCaminoMasCorto(1, 3);
        assertEquals(List.of("BSC", "LDU", "EME"), camino);
    }
}

import java.io.BufferedReader;
import java.io.FileReader;

public class Grafo {

    private int[][] matrizAdyacencia;
    private String[] paises;

    public Grafo() {
        matrizAdyacencia = new int[0][0];
        paises = new String[0];
    }

    public void cargarGrafo(String archivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea = br.readLine();
            while (linea != null) {
                String[] datos = linea.split(",");
                String pais1 = datos[0];
                String pais2 = datos[1];
                int distancia = Integer.parseInt(datos[2]);
                // agregar los paises al arreglo de paises
                agregarPais(pais1);
                agregarPais(pais2);
                // agregar la distancia a la matriz de adyacencia
                int i = indicePais(pais1);
                int j = indicePais(pais2);
                matrizAdyacencia[i][j] = distancia;
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void agregarPais(String pais) {
        if (indicePais(pais) == -1) {
            // crear un nuevo arreglo de paises
            String[] paises2 = new String[paises.length + 1];
            // copiar los paises del arreglo anterior
            for (int i = 0; i < paises.length; i++) {
                paises2[i] = paises[i];
            }
            // agregar el nuevo pais
            paises2[paises2.length - 1] = pais;
            // asignar el nuevo arreglo de paises
            paises = paises2;
            // crear una nueva matriz de adyacencia
            int[][] matrizAdyacencia2 = new int[paises.length][paises.length];
            // copiar los valores de la matriz anterior
            for (int i = 0; i < matrizAdyacencia.length; i++) {
                for (int j = 0; j < matrizAdyacencia.length; j++) {
                    matrizAdyacencia2[i][j] = matrizAdyacencia[i][j];
                }
            }
            // asignar la nueva matriz de adyacencia
            matrizAdyacencia = matrizAdyacencia2;
        }
    }

    private int indicePais(String pais) {
        for (int i = 0; i < paises.length; i++) {
            if (paises[i].equals(pais)) {
                return i;
            }
        }
        return -1;
    }

    public void imprimirCamino(String pais1, String pais2) {
        int[][] matrizFloydWarshall = floydWarshall();
        int i = indicePais(pais1);
        int j = indicePais(pais2);
        if (matrizFloydWarshall[i][j] == 0) {
            System.out.println("No hay camino entre " + pais1 + " y " + pais2);
        } else {
            System.out.println("Camino mas corto entre " + pais1 + " y " + pais2);
            System.out.println(pais1);
            imprimirCamino(i, j, matrizFloydWarshall);
            System.out.println(pais2);
            System.out.println("Distancia total: " + matrizFloydWarshall[i][j]);
        }
    }

    private int[][] floydWarshall() {
        int[][] matrizFloydWarshall = new int[paises.length][paises.length];
        // copiar los valores de la matriz de adyacencia
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia.length; j++) {
                matrizFloydWarshall[i][j] = matrizAdyacencia[i][j];
            }
        }
        // aplicar el algoritmo de floyd warshall
        for (int k = 0; k < paises.length; k++) {
            for (int i = 0; i < paises.length; i++) {
                for (int j = 0; j < paises.length; j++) {
                    if (matrizFloydWarshall[i][k] != 0 && matrizFloydWarshall[k][j] != 0) {
                        if (matrizFloydWarshall[i][j] == 0) {
                            matrizFloydWarshall[i][j] = matrizFloydWarshall[i][k] + matrizFloydWarshall[k][j];
                        } else {
                            matrizFloydWarshall[i][j] = Math.min(matrizFloydWarshall[i][j], matrizFloydWarshall[i][k] + matrizFloydWarshall[k][j]);
                        }
                    }
                }
            }
        }
        return matrizFloydWarshall;
    }

    private void imprimirCamino(int i, int j, int[][] matrizFloydWarshall) {
        for (int k = 0; k < paises.length; k++) {
            if (matrizFloydWarshall[i][k] != 0 && matrizFloydWarshall[k][j] != 0) {
                if (matrizFloydWarshall[i][j] == matrizFloydWarshall[i][k] + matrizFloydWarshall[k][j]) {
                    System.out.println(paises[k]);
                    if (k != j) {
                        imprimirCamino(k, j, matrizFloydWarshall);
                    }
                    break;
                }
            }
        }
    }
}

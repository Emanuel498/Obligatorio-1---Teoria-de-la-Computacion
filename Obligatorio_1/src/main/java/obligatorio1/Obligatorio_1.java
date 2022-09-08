/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package obligatorio1;

import java.io.File;
import java.util.Map;

/**
 *
 * @author USUARIO
 */
public class Obligatorio_1 {

    public static void main(String[] args) {

        String sCarpAct = System.getProperty("user.dir");
        //File carpeta = new File(sCarpAct);

        //--------------------------MATRIZ POR COSTOS DE VIAJES--------------------------
        TGrafoDirigido gd_costo = (TGrafoDirigido) UtilGrafos.cargarGrafo(".\\src\\main\\java\\obligatorio1\\aeropuertos.txt", ".\\src\\main\\java\\obligatorio1\\conexiones_hora.txt",
                false, TGrafoDirigido.class);
        Map<Comparable, TVertice> vertices_costo = gd_costo.getVertices();
        Comparable[][] matrizPorCosto = UtilGrafos.obtenerMatrizCostos(vertices_costo);
        UtilGrafos.imprimirMatrizMejorado(matrizPorCosto, vertices_costo, "Matriz");

        //--------------------------MATRIZ POR HORAS DE VIAJES--------------------------
        TGrafoDirigido gd_hora = (TGrafoDirigido) UtilGrafos.cargarGrafo(".\\src\\main\\java\\obligatorio1\\aeropuertos.txt", ".\\src\\main\\java\\obligatorio1\\conexiones_costo.txt",
                false, TGrafoDirigido.class);
        Map<Comparable, TVertice> vertices_hora = gd_hora.getVertices();
        Comparable[][] matrizPorHora = UtilGrafos.obtenerMatrizCostos(vertices_hora);
        UtilGrafos.imprimirMatrizMejorado(matrizPorHora, vertices_hora, "Matriz");

        gd_costo.caminoMenosCostoso("Montevideo", "Manama").ImprimirEtiquetas();
        gd_hora.caminoMenosCostoso("Montevideo", "Manama").ImprimirEtiquetas();
    }
}

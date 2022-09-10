/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obligatorio1;

/**
 *
 * @author USUARIO
 */
public class Parte_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grafo g = new Grafo();
        g.cargarGrafo(".\\src\\main\\java\\obligatorio1\\conexiones_hora.txt");
        g.imprimirCamino("Montevideo", "Manama");
        System.out.println();  
        g.cargarGrafo(".\\src\\main\\java\\obligatorio1\\conexiones_costo.txt");
        g.imprimirCamino("Montevideo", "Manama");
    }
    
}

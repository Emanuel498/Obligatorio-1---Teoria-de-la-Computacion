/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obligatorio1;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author USUARIO
 */
public class Parte_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int distanciaCaceria = 2;
        ArrayList<Animal> animales = new ArrayList<>();
        animales.add(new Raton("nico"));
        animales.add(new Raton("ema"));
        animales.add(new Gato("gato1"));
        animales.add(new Raton("raton1"));
        animales.add(new Gato("gato2"));
        animales.add(new Raton("raton2"));
        animales.add(new Gato("gato3"));
        animales.add(new Raton("raton3"));
        animales.add(new Gato("gato4"));
        animales.add(new Gato("gato5"));
        animales.add(new Raton("raton4"));
        animales.add(new Raton("raton5"));
        animales.add(new Raton("raton6"));

        cargarRatonesPosibles(animales, distanciaCaceria);
        imprimirRatonesPosibles(animales);
    }

    public static void cargarRatonesPosibles(ArrayList<Animal> animales, int distanciaCaceria) {
        Gato gatoActual;
        for (int i = 0; i < animales.size(); i++) {
            if (animales.get(i) instanceof Gato) {
                gatoActual = (Gato) animales.get(i);
                for (int j = 0; j < animales.size(); j++) {
                    if (animales.get(j) instanceof Raton) {
                        Raton raton = (Raton) animales.get(j);
                        if (distanciaCaceria >= Math.abs(i - j)) {
                            gatoActual.agregarRatonesPosibles(raton);
                        }
                    }
                }
            }
        }
    }
    
    /*// Siempre comenzara a comer el gato que tenga la menor cantidad de ratones para elegir.
    public static void comerRatones(ArrayList<Animal> animales, int distanciaCaceria){
        ArrayList<Animal> listaOrdenadaDeGatosQueVanAComer = ordenarListaPorGatos(animales);
    }

    public static ArrayList<Animal> ordenarListaPorGatos(ArrayList<Animal> animales){
        ArrayList<Animal> listaOrdenada = new ArrayList<>();
        for (int i = 0; i < animales.size(); i++) {
            if(animales.get(i) instanceof Gato){
                listaOrdenada.add(animales.get(i));
            }
        }
        listaOrdenada.sort(Comparator.comparing(Gato :: ratonesPosibles));
        return listaOrdenada;
    }*/
    
    public static void imprimirRatonesPosibles(ArrayList<Animal> animales) {
        Gato gatoActual;
        for (int i = 0; i < animales.size(); i++) {
            if (animales.get(i) instanceof Gato) {
                gatoActual = (Gato) animales.get(i);
                System.out.println(gatoActual.getNombre() + " puede cazar a: ");
                ArrayList<Raton> ratonesPosibles = gatoActual.getRatonesPosibles();
                for (int j = 0; j < ratonesPosibles.size(); j++) {
                    System.out.println(ratonesPosibles.get(j).getNombre());
                }
            }
        }
    }
}

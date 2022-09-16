/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obligatorio1;

import java.util.ArrayList;

/**
 * @author Emanuel Fonseca, Nicolas Prantl, German Marquez
 */
public class Parte_2 {

    /**
     * Este metodo se encarga de iniciar la ejecución del apartado N`2 del
     * obligatorio.
     *
     * @param args[]
     * @return void
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

        Caceria.cargarRatonesPosibles(animales, distanciaCaceria);
        Caceria.comerRatones(animales);
    }
}

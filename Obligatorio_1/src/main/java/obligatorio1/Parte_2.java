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
     * Este metodo se encarga de iniciar la ejecución del apartado N`2 del obligatorio.
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

        cargarRatonesPosibles(animales, distanciaCaceria);
        comerRatones(animales);
    }

    /*
        Metodo encargado de cargar todos los ratones que posee un gato, para comer, segun una distancia como parametro.
        @param ArrayList<Animal> animales, es la lista de todos los animales. Posee instancia de Gato y Raton
        @param int distanciaCaceria, es la distancia maxima que puede comer un gato desde su posicion
        @return 
    */
    public static void cargarRatonesPosibles(ArrayList<Animal> animales, int distanciaCaceria) {
        Gato gatoActual;
        for (int i = 0; i < animales.size(); i++) {
            if (animales.get(i) instanceof Gato) {
                gatoActual = (Gato) animales.get(i);
                for (int j = i - distanciaCaceria; j <= i + distanciaCaceria; j++) {
                    if (animales.get(j) instanceof Raton) {
                        Raton raton = (Raton) animales.get(j);
                        gatoActual.agregarRatonesPosibles(raton);
                    }
                }
            }
        }
    }

    /*
        Metodo encargado de comer la mayor cantidad de ratones posibles
        @param ArrayList<Animal> animales, es la lista de todos los animales. Posee instancia de Gato y Raton
        @return 
    */
    public static void comerRatones(ArrayList<Animal> animales) {
        // Separamos los gatos y ratones en listas separadas para poder hacer menos recorridos sobre la lisra animales.
        ArrayList<Gato> gatosCapacesComer = new ArrayList<>();
        ArrayList<Raton> ratonPorSerComidos = new ArrayList<>();

        Gato gatoActual;
        for (int i = 0; i < animales.size(); i++) {
            if (animales.get(i) instanceof Gato) {
                gatoActual = (Gato) animales.get(i);
                if (!gatoActual.getRatonesPosibles().isEmpty()) {
                    gatosCapacesComer.add(gatoActual);              // Agrego solo los gatos que tienen ratones para comer.
                }
            } else {
                ratonPorSerComidos.add((Raton) animales.get(i));    // Agrego los ratones para luego comerlos y no tener que buscar en la lista generica.
            }
        }
        while (!gatosCapacesComer.isEmpty()) {
            // Obtengo el proximo gato que comera, sera el que tenga el menor numero de eleccion de ratones
            Gato gatoConMenorNumerosDeRatones = proxGatoQueVaAComer(gatosCapacesComer);

            if (gatoConMenorNumerosDeRatones.getRatonesPosibles().size() == 0) {
                break;
            }
            Raton comido = gatoConMenorNumerosDeRatones.comerRaton();   // Como y elimino el raton del gato
            gatosCapacesComer.remove(gatoConMenorNumerosDeRatones);     // Elimino el gato que comio el raton
            comido.setGatoQueLoCazo(gatoConMenorNumerosDeRatones);      // Seteo el gato que comio al raton
            comido.setMurio(true);

            // Elemino dicho raton de todos los gatos que lo tienen.
            eliminarRatonDeTodosLosGatos(comido, gatosCapacesComer);
        }
        // Se imprime por consola los ratones que fueron comidos por respectivo gato de caza y los ratones que no lo fueron
        imprimirRatonesComidosPorQuien(ratonPorSerComidos);
    }

    /*
        Metodo encargado eliminar un raton de todos los gatos que posean dicho raton.
        @param ArrayList<Gato> gatosCapacesComer, son todos los gatos que poseen ratones para comer
        @param Raton comido, raton que sera eliminado de todos los gatos que poseen el raton en sus posibilidades para comer.
        @return 
    */
    public static void eliminarRatonDeTodosLosGatos(Raton comido, ArrayList<Gato> gatosCapacesComer) {
        for (int i = 0; i < gatosCapacesComer.size(); i++) {
            gatosCapacesComer.get(i).eliminarRaton(comido);
        }
    }

    /*
        Metodo encargado de devolver el proximo gato que comera a un raton.
        @param ArrayList<Gato> gatosCapacesComer, son todos los gatos que poseen ratones para comer
        @return Gato gato, sera el gato con menor numero de ratones a comer.
    */
    public static Gato proxGatoQueVaAComer(ArrayList<Gato> gatosCapacesComer) {
        if (gatosCapacesComer.size() == 1) {
            return gatosCapacesComer.get(0);
        }
        Gato gatoConMenorNumerosDeRatones = gatosCapacesComer.get(0);           // Aqui debería ser un valor capaz de ser cambiado y no 0
        for (int i = 0; i < gatosCapacesComer.size(); i++) {
            if (gatosCapacesComer.get(i).getRatonesPosibles().isEmpty()) {       // Pregunto si ya no tiene mas ratones y si ya comio
                gatosCapacesComer.remove(i);                                                                            // Elimino los gatos que ya no tienen ratones por comer
                if (gatosCapacesComer.size() == 1) {
                    return gatosCapacesComer.get(0);
                }
            } else {
                if (gatoConMenorNumerosDeRatones.getRatonesPosibles().size() > gatosCapacesComer.get(i).getRatonesPosibles().size()) {  //Busco el gato con menos cantidad de ratones para empezar a comer
                    gatoConMenorNumerosDeRatones = gatosCapacesComer.get(i);
                }
            }
        }
        return gatoConMenorNumerosDeRatones;
    }

    /*
        Metodo encargado de imprimir por consola los ratones que fueron comidos y los que no.
        @param ArrayList<Raton>, lista de todos los ratones que habia en lista princial 'animales'
        @return
    */
    public static void imprimirRatonesComidosPorQuien(ArrayList<Raton> ratones) {
        Raton ratonActual;
        for (int i = 0; i < ratones.size(); i++) {
            ratonActual = ratones.get(i);
            if (ratonActual.getMurio()) {
                System.out.println("Raton " + ratonActual.getNombre() + " comido por: " + ratonActual.getGatoQueLoCazo().getNombre());
            }
            else{
                System.out.println("Raton " + ratonActual.getNombre() + " sigue vivo");
            }
        }
    }

    /*
        Metodo encargado de imprimir por consola que ratones puede comer cada gato.
        @param ArrayList<Animal> animales, es la lista de todos los animales. Posee instancia de Gato y Raton
        @return
    */
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

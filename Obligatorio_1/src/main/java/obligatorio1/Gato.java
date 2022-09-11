/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1;

import java.util.ArrayList;

/**
 * Clase que extiende de clase Animal, la cual tambien heredara los atributos que la superclase posee.
 * @author Emanuel Fonseca, Nicolas Prantl, German Marquez
 */
public class Gato extends Animal {
    
    // Booleano que nos referenciara si un gato ha comido algun raton, este parametro siempre comienza en falso al crear el objeto.
    private boolean comio;
    
    // ArrayList de todos los ratones que sera capaces de comer un gato, dependiendo de la distancia de alcance
    private ArrayList<Raton> ratonesPosibles;

    /*
        Constructor de objeto gato
        @param nombre es una cadena de texto con el nombre identificador del gato
        Al crear un nueva instancia de gato se creara una lista vacia de ratones posibles a comer y se seteara de que no ha comido.
    */
    public Gato(String nombre) {
        super(nombre);
        ratonesPosibles = new ArrayList<>();
        this.comio = false;
    }

    /*
        Metodo encargado de devolver si el gato ha comido.
        @param
        @return true si ha comido, falso si no ha comido
    */
    public boolean getComio() {
        return comio;
    }

    /*
        Metodo encargado de setear si el gato ha comido.
        @param comio, tipo booleano
        @return
    */
    public void setComio(boolean comio) {
        this.comio = comio;
    }

    /*
        Metodo encargado de devolver la lista de ratones que puede comer un gato.
        @param
        @return lista de los ratones que puede comer
    */
    public ArrayList<Raton> getRatonesPosibles() {
        return ratonesPosibles;
    }

    /*
        Metodo encargado de agregar un raton a la lista de ratones que puede comer el gato.
        @param raton, de tipo Raton
        @return
    */
    public void agregarRatonesPosibles(Raton raton) {
        ratonesPosibles.add(raton);
    }
    
    /*
        Metodo encargado de eliminar un raton a la lista de ratones que puede comer el gato.
        @param raton, de tipo Raton
        @return
    */
    public void eliminarRaton(Raton raton) {
        ratonesPosibles.remove(raton);
    }
    
    /*
        Metodo encargado de eliminar el primer raton de la lista de ratones que puede comer el gato, setear que ya comio el gato y devolver dicho raton.
        @param 
        @return raton comido por el gato
    */
    public Raton comerRaton(){
        setComio(true);
        return ratonesPosibles.remove(0); //Elimino primer raton de la lista pero debo poner, afuera, que fue comido
    }

}

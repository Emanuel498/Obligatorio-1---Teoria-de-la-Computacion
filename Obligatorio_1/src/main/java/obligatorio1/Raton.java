/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1;

/**
 * Clase que extiende de clase Animal, la cual tambien heredara los atributos que la superclase posee.
 * @author Emanuel Fonseca, Nicolas Prantl, German Marquez
 */
public class Raton extends Animal {
    
    // Booleano que referencia si un raton fue comido.
    private boolean murio;
    
    // Referencia al gato que ha comido al raton.
    private Gato gatoQueLoCazo;

    /*
        Constructor de objeto raton
        @param nombre es una cadena de texto con el nombre identificador del raton
        Al crear un nueva instancia de raton se setea su nombre, setea de que sigue vivo y seteamos el gato que lo comio en null, 
        ya que no ha sido comido.
    */
    public Raton(String nombre) {
        super(nombre);
        this.murio = false;
        this.gatoQueLoCazo = null;
    }

    /*
        Metodo encargado de devolver si el raton ha sido comdio.
        @param
        @return true si ha sido comido, falso si no ha sido comido
    */
    public boolean getMurio() {
        return murio;
    }

    /*
        Metodo encargado de setear si un raton fue comido.
        @param murio, tipo booleano
        @return
    */
    public void setMurio(Boolean murio) {
        this.murio = murio;
    }

    /*
        Metodo encargado de devolver el gato que ha comido el raton.
        @param
        @return Gato
    */
    public Gato getGatoQueLoCazo() {
        return gatoQueLoCazo;
    }

    /*
        Metodo encargado setear el gato que ha comido al raton.
        @param gato, tipo Gato
        @return
    */
    public void setGatoQueLoCazo(Gato gatoQueLoCazo) {
        this.gatoQueLoCazo = gatoQueLoCazo;
    }
}

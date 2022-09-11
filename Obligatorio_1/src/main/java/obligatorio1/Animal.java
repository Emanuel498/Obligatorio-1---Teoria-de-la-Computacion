/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1;

/**
 * Clase abstracta que contendra las caracteristicas basicas de los animales.
 *
 * @author Emanuel Fonseca, Nicolas Prantl, German Marquez
 */
public abstract class Animal {

    // clase abstracta animal
    // Atributo que contednr[a el nombre identificador del Animal.
    private String nombre;

    /*
        Constructor de objeto animal a traves del nombre
        @param nombre es una cadena de texto con el nombre identificador del animal
    */
    public Animal(String nombre) {
        this.nombre = nombre;
    }

    /*
        Metodo encargado de devolver el nombre identificador del animal.
        @param
        @return nombre identificador del animal
    */
    public String getNombre() {
        return nombre;
    }
}

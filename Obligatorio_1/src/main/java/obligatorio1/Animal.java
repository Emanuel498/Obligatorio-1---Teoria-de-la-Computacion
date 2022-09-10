/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1;

/**
 *
 * @author USUARIO
 */
public abstract class Animal {

    // clase abstracta animal
    // atributos
    private String nombre;

    // constructor
    public Animal(String nombre) {
        this.nombre = nombre;
    }

    // metodos
    public String getNombre() {
        return nombre;
    }
}

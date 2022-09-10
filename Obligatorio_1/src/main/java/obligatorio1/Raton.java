/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1;

/**
 *
 * @author USUARIO
 */
public class Raton extends Animal {
    private boolean murio;
    private Gato gatoQueLoCazo;

    public Raton(String nombre) {
        super(nombre);
        this.murio = false;
        this.gatoQueLoCazo = null;
    }

    public boolean getMurio() {
        return murio;
    }

    public void setMurio(Boolean murio) {
        this.murio = murio;
    }

    public Gato getGatoQueLoCazo() {
        return gatoQueLoCazo;
    }

    public void setGatoQueLoCazo(Gato gatoQueLoCazo) {
        this.gatoQueLoCazo = gatoQueLoCazo;
    }
}

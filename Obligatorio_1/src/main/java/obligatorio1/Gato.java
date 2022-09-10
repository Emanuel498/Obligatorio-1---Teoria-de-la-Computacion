/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1;

import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Gato extends Animal {

    private boolean comio;
    private ArrayList<Raton> ratonesPosibles;

    public Gato(String nombre) {
        super(nombre);
        ratonesPosibles = new ArrayList<>();
        this.comio = false;
    }

    public boolean getComio() {
        return comio;
    }

    public void setComio(boolean comio) {
        this.comio = comio;
    }

    public ArrayList<Raton> getRatonesPosibles() {
        return ratonesPosibles;
    }
    
    public int getCantidadRatonesPosibles() {
        return ratonesPosibles.size();
    }

    public void agregarRatonesPosibles(Raton raton) {
        ratonesPosibles.add(raton);
    }
    
    public void eliminarRaton(Raton raton) {
        ratonesPosibles.remove(raton);
    }

}

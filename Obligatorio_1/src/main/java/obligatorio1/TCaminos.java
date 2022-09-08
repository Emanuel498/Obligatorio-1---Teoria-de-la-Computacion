package obligatorio1;

import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FIT
 */
public class TCaminos {// contendrá elementos del tipo TCamino

    private LinkedList<TCamino> Caminos;

    public TCaminos() {
        Caminos = new LinkedList<>();
    } 
    
    public LinkedList<TCamino> getCaminos(){
        return Caminos;
    }
    public void agregarCaminos(TCamino nuevoCamino){
        Caminos.add(nuevoCamino);
    }

    public void imprimir() {
        for(TCamino camino : Caminos){
            camino.ImprimirEtiquetas();
        }
    } 
}

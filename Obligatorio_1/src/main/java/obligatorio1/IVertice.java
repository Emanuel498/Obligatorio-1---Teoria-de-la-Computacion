package obligatorio1;

import java.util.Collection;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernesto
 */
public interface IVertice {

    TAdyacencia buscarAdyacencia(TVertice verticeDestino);

    TAdyacencia buscarAdyacencia(Comparable etiquetaDestino);

    boolean eliminarAdyacencia(Comparable nomVerticeDestino);

    LinkedList<TAdyacencia> getAdyacentes();

    boolean insertarAdyacencia(Double costo, TVertice verticeDestino);

    Double obtenerCostoAdyacencia(TVertice verticeDestino);

    TVertice primerAdyacente();

    TVertice siguienteAdyacente(TVertice w);
    
    public void bpf(Collection<TVertice> visitados) ;
    
    boolean tieneCiclo(TCamino unCamino);
    
    TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos);
    
    void ordenTopologico();
    
    void ordenParcial(LinkedList<TVertice> tareas);
    
    void clasificarArcos(LinkedList<TArista> arcosArbol, LinkedList<TArista> arcosRetroceso, LinkedList<TArista> arcosAvance, LinkedList<TArista> arcosCruzados);

    void bpfConNumero(int[] numerobp);
    
    void bea(Collection<TVertice> visitados);
    
    void puntosArticulacion(Collection<TVertice> puntosDeArticulacion, int[] numerobp);
    
    int bpfConNumero(Collection<TVertice> puntosDeArticulacion, int numerobp);
    
    int beaBacon(Comparable actor);
}

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
 * @author FIT
 */
public class TCamino {

    public TVertice origen;
    public LinkedList<Comparable> otrosVertices;
    private Double costoTotal;

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public TCamino(TVertice v) {
        this.origen = v;
        this.otrosVertices = new LinkedList<>();
        this.costoTotal = 0.0;
    }
    
    public TVertice getOrigen() {
        return origen;
    }

    public LinkedList<Comparable> getOtrosVertices() {
        return otrosVertices;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }
    
    public void ImprimirEtiquetas() {
        System.out.print(origen.getEtiqueta() + ", ");
        System.out.println(otrosVertices.toString().substring(1, otrosVertices.toString().length()-1));
    }
    
    public String devolverCamino(){
        return origen.getEtiqueta() + ", " + otrosVertices.toString();
    }

    public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal = costoTotal + ((Number) adyacenciaActual.getCosto()).doubleValue();
            return otrosVertices.add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (otrosVertices.contains(adyacenciaActual.getDestino().getEtiqueta())) {
            costoTotal = costoTotal - ((Number) adyacenciaActual.getCosto()).doubleValue();
            return (otrosVertices.remove(adyacenciaActual.getDestino().getEtiqueta()));
        }
        return false;
    }

    public TCamino copiar() {
        TVertice origen = new TVertice(this.getOrigen().getEtiqueta());
        TCamino copia = new TCamino(origen);
        origen.getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());
        copia.setCostoTotal(costoTotal);
        return copia;
    }

    
}

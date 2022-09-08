package obligatorio1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices; // vertices del grafo.-
    private Collection<TArista> aristas = new LinkedList<>();

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        vertices.forEach((vertice) -> {
            insertarVertice(vertice.getEtiqueta());
        });
        this.aristas = aristas;
        aristas.forEach((arista) -> {
            insertarArista(arista);
        });
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     */
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista.Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    public TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override

    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    /**
     * Metodo encargado de encontrar el centro del grafo Obtiene la
     * excentricidad de cada vertice y se queda con la menor de todas Retorna la
     * etiqueta del vertice 'centro' de grafo si lo encuentra Retorna -1 si no
     * se ha podido encontrar
     */
    public Comparable centroDelGrafo() {
        Comparable[][] costos = floyd();
        Set<Comparable> etiquetas = vertices.keySet();

        int indice = 0;
        Double costoMaxGrafo = Double.MAX_VALUE;
        for (int j = 0; j < etiquetas.size(); j++) {
            Double costoMax = 0d;
            for (int i = 0; i < etiquetas.size(); i++) {
                if ((Double) costos[i][j] > costoMax) {
                    costoMax = (Double) costos[i][j];
                }
            }
            if (costoMax < costoMaxGrafo) {
                indice = j;
                costoMaxGrafo = costoMax;
            }
        }

        Comparable etiquetaGrafo = "";
        for (Comparable etiqueta : etiquetas) {
            if (indice == 0) {
                etiquetaGrafo = etiqueta;
                break;
            }
            indice--;
        }
        return etiquetaGrafo;
    }

    @Override
    public Comparable[][] floyd() {
        int cantidad = vertices.size();
        Double[][] A = UtilGrafos.obtenerMatrizCostos(vertices);
        for (int k = 0; k < cantidad; k++) {
            for (int i = 0; i < cantidad; i++) {
                if (k != i) {
                    for (int j = 0; j < cantidad; j++) {
                        if (A[i][j] > A[i][k] + A[k][j]) {
                            A[i][j] = A[i][k] + A[k][j];
                        }
                    }
                }
            }
        }
        return A;
    }

    /**
     * Método encargado de obtener la excentricidad dado una etiqueta de vertice
     * Retorna la excentricidad del vertice pasado por parametro si este se
     * encuentra en el grafo Retorna -1 si no se puede conseguir ese vertice o
     * no hay vertices adyacentes
     *
     * @param etiquetaVertice
     * @return
     */
    @Override
    @SuppressWarnings("null")
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Comparable[][] costos = floyd();
        Set<Comparable> etiquetas = vertices.keySet();
        int indice = 0;
        for (Comparable vertice : etiquetas) {
            if (etiquetaVertice.compareTo(vertice) == 0) {
                break;
            }
            indice++;
        }

        Double costoMax = 0d;
        for (int i = 0; i < costos[0].length; i++) {
            if ((Double) costos[i][indice] > costoMax) {
                costoMax = (Double) costos[i][indice];
            }
        }

        return costoMax == Double.MAX_VALUE ? -1 : costoMax;
    }

    @Override
    public boolean[][] warshall() {
        int cantidad = vertices.size();
        Double[][] C = UtilGrafos.obtenerMatrizCostos(vertices);
        boolean[][] A = new boolean[cantidad][cantidad];
        for (int i = 0; i < cantidad; i++) {
            for (int j = 0; j < cantidad; j++) {
                if (i != j) {
                    A[i][j] = C[i][j] < Double.MAX_VALUE;
                }
            }
        }
        for (int k = 0; k < cantidad; k++) {
            for (int i = 0; i < cantidad; i++) {
                if (k != i) {
                    for (int j = 0; j < cantidad; j++) {
                        if (i != j && !A[i][j]) {
                            A[i][j] = A[i][k] && A[k][j];
                        }
                    }
                }
            }
        }
        return A;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<TVertice> bpf() {
        desvisitarVertices();
        LinkedList<TVertice> resultado = new LinkedList<>();
        for (TVertice v : this.getVertices().values()) {
            if (!v.getVisitado()) {
                v.bpf(resultado);
            }
        }
        return resultado;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        desvisitarVertices();
        TVertice origen = this.buscarVertice(etiquetaOrigen);
        LinkedList<TVertice> resultado = new LinkedList<>();
        if (origen != null) {
            origen.bpf(resultado);
        }
        // Ver cuales no han sido visitados
        for (TVertice v : this.getVertices().values()) {
            if (!v.getVisitado()) {
                v.bpf(resultado);
            }
        }
        return resultado;
    }

    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        desvisitarVertices();
        LinkedList<TVertice> resultado = new LinkedList<>();
        if (vertice != null) {
            vertice.bpf(resultado);
        }
        // Ver cuales no han sido visitados
        for (TVertice v : this.getVertices().values()) {
            if (!v.getVisitado()) {
                v.bpf(resultado);
            }
        }
        return resultado;
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice v : this.getVertices().values()) {
            v.setVisitado(false);
        }
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice verticeOrigen = buscarVertice(etiquetaOrigen);
        if (verticeOrigen != null) {
            return verticeOrigen.todosLosCaminos(etiquetaDestino, new TCamino(verticeOrigen), new TCaminos());
        }
        return null; // El vertice origen no existe.
    }

    @Override
    public void camino(TVertice origen, TVertice destino, TCaminos caminos, TCamino elCamino) {
        origen.setVisitado(true);
        LinkedList<TAdyacencia> adyacencias = origen.getAdyacentes();
        for (TAdyacencia adyacente : adyacencias) {
            elCamino.agregarAdyacencia(adyacente);
            if (adyacente.getDestino().equals(destino)) {
                caminos.agregarCaminos(elCamino.copiar()); //El camino lo copia porque luego voy a modificarlo.
            } else {
                if (!adyacente.getDestino().getVisitado()) {
                    camino(adyacente.getDestino(), destino, caminos, elCamino);
                }
            }
            elCamino.eliminarAdyacencia(adyacente);
        }
        origen.setVisitado(false);  // Lo desvisito porque desde A capaz puedo acceder desde otro nodo a B.
    }

    @Override
    public void ordenTopologico(Comparable etiquetaDestino) {
        desvisitarVertices();
        TGrafoDirigido grafoCopiaConAristasInvertidas = invertirGrafo();  //O(V+A)
        TVertice v = grafoCopiaConAristasInvertidas.buscarVertice(etiquetaDestino);
        if (v != null) {
            v.ordenTopologico();
        }
    }

    @Override
    public LinkedList<TVertice> ordenParcial() {
        if (!tieneCiclo()) {
            desvisitarVertices();
            TGrafoDirigido grafoCopiaConAristasInvertidas = invertirGrafo();  //O(V+A)
//        LinkedList<TVertice> vertices = new LinkedList<>(grafoCopiaConAristasInvertidas.getVertices().values());
            TVertice verticeFinal = grafoCopiaConAristasInvertidas.buscarVertice("Fin");
            LinkedList<TVertice> tareas = new LinkedList<>();
            if (verticeFinal != null) {
                verticeFinal.ordenParcial(tareas);
            }
            return tareas;
        }
        return new LinkedList<>();
    }

    @Override
    public TGrafoDirigido invertirGrafo() {
        LinkedList<TArista> aristasInversas = new LinkedList<>();                       //O(1)
        for (TArista arista : aristas) {                                                //O(A)
            aristasInversas.add(arista.aristaInversa());
        }
        LinkedList<TVertice> vertices = new LinkedList<>();                     //O(V)
        getVertices().keySet().forEach((v -> vertices.add(new TVertice((v))))); // Copia de los vértices para eliminar adyacencias anteriores.
        // Parte de cargar el grafo copia.
        return new TGrafoDirigido(vertices, aristasInversas);  //O(V+A)
    }

    @Override
    public boolean esConexo() {
        LinkedList<TVertice> verticesRecorrer = new LinkedList<>(vertices.values());    //O(V)
        TVertice v = verticesRecorrer.getFirst();                                       //O(1)
        LinkedList<TVertice> verticesVisitados = new LinkedList<>();                    //O(1)
        v.bpf(verticesVisitados);                                                       //O(A)
        for (TVertice verificarVisitado : verticesRecorrer) {                           //O(V)
            if (!verificarVisitado.getVisitado()) {                                     //O(1)
                return false;                                                           //O(1)
            }
        }
        //Invierto aristas del grafo.
        TGrafoDirigido grafoCopiaConAristasInvertidas = invertirGrafo();
        // Segunda Comprobación desde v, deberían llegar todos nuevamente.
        verticesRecorrer = new LinkedList<>(grafoCopiaConAristasInvertidas.getVertices().values());     //O(V)
        v = verticesRecorrer.getFirst();                                                                //O(1)
        grafoCopiaConAristasInvertidas.desvisitarVertices();
        verticesVisitados = new LinkedList<>();                    //O(1)
        v.bpf(verticesVisitados);                                                                       //O(A)
        for (TVertice verificarVisitado : verticesRecorrer) {                                           //O(V)
            if (!verificarVisitado.getVisitado()) {                                                     //O(1)
                return false;                                                                           //O(1)
            }
        }
        return true;                                                                                    //O(1)
    } //Total = O(A). Ya que V+A para A>=V.

    @Override
    public boolean tieneCiclo() {
        for (TVertice v : this.getVertices().values()) {
            if (v.tieneCiclo(new TCamino(v)) && !v.getVisitado()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clasificarArcos(Comparable verticeOrigen, LinkedList<TArista> arcosArbol, LinkedList<TArista> arcosRetroceso, LinkedList<TArista> arcosAvance, LinkedList<TArista> arcosCruzados) {
        desvisitarVertices();
        TVertice v = buscarVertice(verticeOrigen);

        if (v != null) {
            v.bpfConNumero(new int[1]);
            desvisitarVertices();
            v.clasificarArcos(arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
        }
    }

    public Collection<TVertice> caminoMasCorto(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        LinkedList<TVertice> camino = new LinkedList<>();
        TVertice origen = buscarVertice(etiquetaOrigen);
        if (origen != null) {
            desvisitarVertices();
            origen.caminoMasCorto(etiquetaDestino);
        }

        // Recolectar el camino.
        TVertice verticeActual = buscarVertice(etiquetaDestino); // Inicialmente destino.
        while (verticeActual != null) {
            camino.addFirst(verticeActual);
            verticeActual = verticeActual.getPredecesor();
        }

        return camino;
    }

    public TCamino caminoMasLargo(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TCaminos caminosPosibles = todosLosCaminos(etiquetaOrigen, etiquetaDestino);
        TCamino devolver = new TCamino(new TVertice(""));
        if (caminosPosibles != null) {
            for (TCamino c : caminosPosibles.getCaminos()) {
                if (devolver == null || devolver.getOtrosVertices().size() < c.getOtrosVertices().size()) {
                    devolver = c;
                }
            }
        }
        return devolver;
    }

    public TCamino caminoMenosCostoso(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TCamino caminoActual, mejorCamino = null;

        TVertice origen = buscarVertice(etiquetaOrigen);

        if (etiquetaOrigen.equals(etiquetaDestino)) {
            return new TCamino(origen);
        }

        if (origen != null) {
            desvisitarVertices();
            caminoActual = new TCamino(origen);
            mejorCamino = origen.caminoMenosCostoso(etiquetaDestino, caminoActual, mejorCamino);
        }

        return mejorCamino;
    }

    public LinkedList<TVertice> menosEscalas(Comparable origen, Comparable destino) {
        desvisitarVertices();
        TVertice v = buscarVertice(origen);
        LinkedList<TVertice> escalas = new LinkedList<>();
        if (v != null) {
            v.menosEscalas(destino);
            TVertice d = buscarVertice(destino);
            if (d != null) {
                TVertice aux = d.getPredecesor();
                escalas.add(d);
                while (aux != null) {
                    escalas.addFirst(aux);
                    aux = aux.getPredecesor();
                }

            }
        }
        return escalas;
    }

    public TCamino caminoMasCostoso(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        desvisitarVertices();
        TVertice origen = buscarVertice(etiquetaOrigen);
        TCamino peorCamino = new TCamino(origen);
        if (origen != null) {
            TCamino caminoActual = new TCamino(origen);
            peorCamino = origen.caminoMasCostoso(etiquetaDestino, caminoActual, peorCamino);
        }
        return peorCamino;
    }

    public Collection<Collection<TVertice>> componentesFuertes() {
        LinkedList<TVertice> visitados = new LinkedList<>();
        for (TVertice vertice : getVertices().values()) {
            if (!vertice.getVisitado()) {
                vertice.bpf(visitados);
            }
        }
        TGrafoDirigido gdi = invertirGrafo();
        LinkedList componentes = new LinkedList();
        for (Iterator<TVertice> it = visitados.descendingIterator(); it.hasNext();) {
            TVertice origen = gdi.getVertices().get(it.next().getEtiqueta());
            if (!origen.getVisitado()) {
                LinkedList<TVertice> abarcador = new LinkedList<>();
                origen.bpf(abarcador);
                componentes.add(new LinkedList<>(abarcador));
            }
        }
        return componentes;
    }
}

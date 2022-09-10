public class App {
    public static void main(String[] args) throws Exception {
        Grafo g = new Grafo();
        g.cargarGrafo("paises.txt");
        g.imprimirCamino("Montevideo", "Manama");
    }

}

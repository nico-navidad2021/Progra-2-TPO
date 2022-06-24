package imp;

import api.ConjuntoTDA;
import api.GrafoTDA;

public class GrafoMA implements GrafoTDA{

    static int n = 100; //Cantidad de vertices posibles
    int [][] MAdy; //Matriz de adyasencia
    int [] Etiqs; //Vector para el mapeo de indice con valor del vertice
    int cantNodos;

    @Override
    public void InicializarGrafo() {
        MAdy = new int [n][n];
        Etiqs = new int [n];
        cantNodos = 0;
    }

    @Override
    public void AgregarVertice(int v) {
        //Puedo tener mas de un vertice con el mismo valor ?
        
        Etiqs[cantNodos] = v;        
        
        //Lleno las coordinadas del vertice en cero (AKA no esta conectado a ningun otro vertice)
        for(int i=0;i<cantNodos;i++){
            MAdy[cantNodos][i] = 0;
            MAdy[i][cantNodos] = 0;
        }
        cantNodos++;
    }

    @Override
    public void EliminarVertice(int v) {
        //Esto unicamente cuando hay mas de un vertice
        //Reemplazo las coordenadas que ocupa el vertice que quiero eliminar por las del ultimo vertice
        
        int ind = Vert2Indice(v);
        for(int i=0;i<cantNodos;i++){
            MAdy[i][ind] = MAdy[i][cantNodos-1]; 
        }
        for(int i=0;i<cantNodos;i++){
            MAdy[ind][i] = MAdy[cantNodos-1][i]; 
        }
        Etiqs[ind] = Etiqs[cantNodos-1];
        cantNodos--;
    }

    @Override
    public void AgregarArista(int v1, int v2, int p) {
        int o = Vert2Indice(v1);
        int d = Vert2Indice(v2);
        MAdy[o][d] = p;
    }

    @Override
    public void EliminarArista(int v1, int v2) {
        int o = Vert2Indice(v1);
        int d = Vert2Indice(v2);
        MAdy[o][d] = 0;   
    }

    @Override
    public int PesoArista(int v1, int v2) {
        int o = Vert2Indice(v1);
        int d = Vert2Indice(v2);
        return MAdy[o][d];
    }

    @Override
    public ConjuntoTDA Vertices() {
        ConjuntoTDA Vert = new ConjuntoLD();
        Vert.InicializarConjunto();
        for(int i=0;i<cantNodos;i++){
            Vert.Agregar(Etiqs[i]);
        }
        return Vert;
    }

    @Override
    public boolean ExisteArista(int v1, int v2) {
        int o = Vert2Indice(v1);
        System.out.println(o);
        int d = Vert2Indice(v2);
        return (MAdy[o][d] != 0);           
    }
    
    private int Vert2Indice(int v){
        int i = cantNodos - 1;
        //Recorro el vector con el mapeo de indice-valor para ver si existe un vertice con el valor que busco
        while(i>=0 && Etiqs[i] != v){
            i--;
        }
        return i;
    }

}

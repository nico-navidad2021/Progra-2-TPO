package imp;

import api.ConjuntoTDA;
import api.GrafoTDA;
import imp.*;

public class GrafoMA implements GrafoTDA{

    static int n = 100; //Cantidad de vertices posibles
    int [][] MAdy; //Matriz de adyasencia
    int [] Etiqs; //Vector para el mapeo de indice con valor del vertice
    int cantVertices; //Contador para saber cuantos vertices tengo

    @Override
    public void InicializarGrafo() {
        MAdy = new int [n][n];
        Etiqs = new int [n];
        cantVertices = 0;
    }

    @Override
    public void AgregarVertice(int v) {
        Etiqs[cantVertices] = v;    //Pongo el valor del nuevo vertice en la ultima posiciond el array de mapeo 
        
        //Lleno las coordinadas del vertice en cero (AKA no esta conectado a ningun otro vertice)
        for(int i=0;i<cantVertices;i++){
            MAdy[cantVertices][i] = 0;
            MAdy[i][cantVertices] = 0;
        }
        cantVertices++;
    }

    @Override
    public void EliminarVertice(int v) {
        //El prerequisito de esto es que haya mas de un vertice
        //Reemplazo las coordenadas que ocupa el vertice que quiero eliminar por las del ultimo vertice
        
        int ind = Vert2Indice(v);
        for(int i=0;i<cantVertices;i++){
            MAdy[i][ind] = MAdy[i][cantVertices-1]; 
        }
        for(int i=0;i<cantVertices;i++){
            MAdy[ind][i] = MAdy[cantVertices-1][i]; 
        }
        Etiqs[ind] = Etiqs[cantVertices-1]; //Reemplazo el valor de la posicion del vertice a eliminar con el ultimo
        cantVertices--; //Reduzco la cantidad de nodos que tengo
    }

    @Override
    public void AgregarArista(int vertice_origen, int vertice_destino, int peso) {
        int indice_vertice_origen = Vert2Indice(vertice_origen);
        int indice_vertice_destino = Vert2Indice(vertice_destino);
        MAdy[indice_vertice_origen][indice_vertice_destino] = peso; //Actualizo la matriz con el nuevo peso
    }

    @Override
    public void EliminarArista(int vertice_origen, int vertice_destino) {
        int indice_vertice_origen = Vert2Indice(vertice_origen);
        int indice_vertice_destino = Vert2Indice(vertice_destino);
        MAdy[indice_vertice_origen][indice_vertice_destino] = 0; //Actualizo la matriz para eliminar la arista (0 = no hay arista)   
    }

    @Override
    public int PesoArista(int vertice_origen, int vertice_destino) {
        int indice_vertice_origen = Vert2Indice(vertice_origen);
        int indice_vertice_destino = Vert2Indice(vertice_destino);
        return MAdy[indice_vertice_origen][indice_vertice_destino]; //Devuelvo el valor de la coordenada la matriz
    }

    @Override
    public ConjuntoTDA Vertices() {
        ConjuntoTDA Vertices = new ConjuntoLD();
        Vertices.InicializarConjunto();
        for(int i=0;i<cantVertices;i++){
            Vertices.Agregar(Etiqs[i]); //Voy guardando en el Conjunto los valores del array de mapeo
        }
        return Vertices;
    }

    @Override
    public boolean ExisteArista(int vertice_origen, int vertice_destino) {
        int indice_vertice_origen = Vert2Indice(vertice_origen);
        int indice_vertice_destino = Vert2Indice(vertice_destino);
        return (MAdy[indice_vertice_origen][indice_vertice_destino] != 0); //Si el valor de la posicion que estoy evaluando es distinto de 0
    }
    
    @Override
    public void MostrarAdyacencias(){

        System.out.print("   "+(Etiqs[0])+" ");

        for(int x =1;x<cantVertices;x++){
            System.out.print((Etiqs[x])+" ");
        }

        System.out.println();

        for (int i = 0;i<cantVertices;i++){
            System.out.print((Etiqs[i])+"| ");
            for (int z = 0;z<cantVertices;z++){
                System.out.print(MAdy[i][z]+" "); //Uso dos FOR, uno para iterar las filas y otro las columnas
            }   
            System.out.println();
        }
    }

    private int Vert2Indice(int v){
        int i = cantVertices - 1;
        //Recorro el array de mapeo indice-valor para ver si existe un vertice con el valor que busco
        while(i>=0 && Etiqs[i] != v){
            i--;
        }
        return i;
    }

}

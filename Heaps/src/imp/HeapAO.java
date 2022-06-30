package imp;

import api.HeapTDA;

public class HeapAO implements HeapTDA {

    boolean tipo_heap; //Indicador tipos de Heap (true = max, false = min)
    int[] Heap; //Arreglo para almacenar todos los elementos del heap
    int indice; //Contador para indicar la proxima posicion para insertar

    private int padre(int pos){
        return (pos-1)/2;
    }

    private int hijoIzq(int pos){
        return (2*pos)+1;
    }

    private int hijoDer(int pos){
        return (2*pos)+2;
    }

    private boolean esHoja(int pos){
        if((pos > (int)((indice-1)/2) && pos <= (indice-1))  || (indice == 1)){
            return true;    
        }
        return false;
    }

    private void intercambiar(int pos1, int pos2){
        int aux = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = aux;
    }

    @Override
    public void InicializarHeap(Boolean tipo) {
        tipo_heap = tipo;
        indice = 0;
        Heap = new int[14];
    }

    @Override
    public int Primero() {
        return Heap[0];
    }

    @Override
    public void Insertar(int elemento) {
        Heap[indice] = elemento;
        if(tipo_heap == true){
            int pos_actual = indice;
            while(Heap[pos_actual] > Heap[padre(pos_actual)]){
                intercambiar(pos_actual, padre(pos_actual));
                pos_actual = padre(pos_actual);
            }
            indice++;
        }else{
            int pos_actual = indice;
            while(Heap[pos_actual] < Heap[padre(pos_actual)]){
                intercambiar(pos_actual, padre(pos_actual));
                pos_actual = padre(pos_actual);
            }
            indice++;
        }     
    }

    private void maxHeapify(int pos){
        if(esHoja(pos)){
            return;
        }else{
            //Si la posicion en la que me encuentro es mayor a cualquiera de los hijos
            if(Heap[pos] < Heap[hijoIzq(pos)] || Heap[pos] < Heap[hijoDer(pos)]){
                if(Heap[hijoIzq(pos)] > Heap[hijoDer(pos)]){
                    intercambiar(pos, hijoIzq(pos));
                    maxHeapify(hijoIzq(pos));
                }else{
                    intercambiar(pos, hijoDer(pos));
                    maxHeapify(hijoDer(pos));
                }
            }
        }
    }

    private boolean tieneHijoDer(int pos){
        if( (2*pos)+2 > indice-1){
            return false;
        }else{
            return true;
        }
    }

    private boolean tieneHijoIzq(int pos){
        if( (2*pos)+1 > indice-1){
            return false;
        }else{
            return true;
        }
    }

    private void minHeapify(int pos){
        //Caso base
        if(esHoja(pos)){
            return;
        }else{
            
            if(tieneHijoDer(pos) && tieneHijoIzq(pos)){
                /* Si es mayor a cualquiera de los hijos comparo los hijos e interambio por el menor.
                 * Luego llamo nuevamente a la funcion para seguir ordenando
                */
                if(Heap[pos] > Heap[hijoIzq(pos)] || Heap[pos] > Heap[hijoDer(pos)]){
                    if(Heap[hijoIzq(pos)] < Heap[hijoDer(pos)]){
                        intercambiar(pos, hijoIzq(pos));
                        minHeapify(hijoIzq(pos));
                    }else{
                        intercambiar(pos, hijoDer(pos));
                        minHeapify(hijoDer(pos));
                    }
                } 
            }else if(!tieneHijoDer(pos)){
                if(Heap[pos] > Heap[hijoIzq(pos)]){
                    intercambiar(pos, hijoIzq(pos));
                    minHeapify(hijoIzq(pos));
                }
            }else{
                if(Heap[pos] > Heap[hijoDer(pos)]){
                    intercambiar(pos, hijoDer(pos));
                    minHeapify(hijoDer(pos));
                }
            }
        }
    }

    //Funcion para debugear
    private void MostrarLista(int[] x){
        for (int i=0; i<indice;i++){
            System.out.println("Elementos "+i+" del array "+x[i]+' ');
        }
    }

    @Override
    public void Eliminar() {
        /* Subo el ultimo elemento a la raiz, 
        la posicion donde estaba el ultimo elemento la remplazo con 0 y reduzco el indice.
        Por ultimo ardeno usando el heapify correspondiente */
        if(tipo_heap == true){
            int ultimo_elemento = Heap[indice-1];
            Heap[0] = ultimo_elemento;
            Heap[indice-1] = 0;
            indice--; 
            maxHeapify(0);
        }else{
            int ultimo_elemento = Heap[indice-1];
            Heap[0] = ultimo_elemento;
            Heap[indice-1] = 0;
            indice--;
            minHeapify(0);
        }
    }

    @Override
    public boolean HeapVacio() {
        return indice == 0;
    }
    
}

package imp;

import api.HeapTDA;

public class HeapAO implements HeapTDA {

    String tipo_heap;
    int[] Heap;
    int indice;

    private int padre(int pos){
        return pos/2;
    }

    private int hijoIzq(int pos){
        return (2*pos);
    }

    private int hijoDer(int pos){
        return (2*pos)+1;
    }

    private boolean esHoja(int pos){
        if(pos > (indice/2) && pos <= indice){
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
    public void InicializarHeap(String tipo) {
        tipo_heap = tipo;
        indice = 0;
        Heap = new int[14];

    }

    @Override
    public int Primero() {
        return Heap[0];
    }

    @Override //Funca barbaro
    public void Insertar(int elemento) {
        Heap[indice] = elemento;
        if(tipo_heap == "max"){
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

    // private void maxHeapify(int pos){
    //     if(esHoja(pos)){
    //         return;
    //     }else{
    //         //Si la posicion en la que me encuentro es mayor a ambos hijos
    //         if(Heap[pos] < Heap[hijoIzq(pos)] || Heap[pos] < Heap[hijoDer(pos)]){
    //             if(Heap[hijoIzq(pos)] > Heap[hijoDer(pos)]){
    //                 intercambiar(pos, hijoIzq(pos));
    //                 maxHeapify(hijoIzq(pos));
    //             }else{
    //                 intercambiar(pos, hijoIzq(pos));
    //                 maxHeapify(hijoIzq(pos));
    //             }
    //         }
    //     }
    // }


    @Override
    public void Eliminar() {
        if(tipo_heap == "max"){
            Heap[0] = Heap[indice];
            indice--;
            // maxHeapify(0);
        }else{

        }
        
    }

    @Override
    public boolean HeapVacio() {
        return indice == 0;
    }
    
}

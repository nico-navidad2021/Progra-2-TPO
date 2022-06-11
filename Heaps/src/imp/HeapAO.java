package imp;

import api.HeapTDA;

public class HeapAO implements HeapTDA {

    class Heap{
        String tipo_heap;
        int[] Heap;
        int tamano;
    }

    Heap heap;

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
        if(pos > (heap.tamano/2) && pos <= heap.tamano){
            return true;    
        }
        return false;
    }

    private void intercambiar(int pos1, int pos2){
        int aux = heap.Heap[pos1];
        heap.Heap[pos1] = heap.Heap[pos2];
        heap.Heap[pos2] = aux;
    }

    @Override
    public void InicializarHeap(String tipo) {
        heap.tamano = 0;
        heap.tipo_heap = tipo;
        heap.Heap = new int[14];
        
    }

    @Override
    public int Primero() {
        return heap.Heap[0];
    }

    @Override
    public void Insertar(int x) {
        if(heap.tamano == 0) heap.tamano = 1;
        heap.Heap[heap.tamano] = x;
        if(heap.tipo_heap == "max"){
            int pos_actual = heap.tamano;
            while(heap.Heap[pos_actual] >= heap.Heap[padre(pos_actual)]){
                intercambiar(pos_actual, padre(pos_actual));
                pos_actual = padre(pos_actual);
            }
            heap.tamano++;
        }else{
            int pos_actual = heap.tamano;
            while(heap.Heap[pos_actual] <= heap.Heap[padre(pos_actual)]){
                intercambiar(pos_actual, padre(pos_actual));
                pos_actual = padre(pos_actual);
            }
            heap.tamano++;
        }     
    }

    private void maxHeapify(int pos){
        if(esHoja(pos)){
            return;
        }else{
            //Si la posicion en la que me encuentro es mayor a ambos hijos
            if(heap.Heap[pos] < heap.Heap[hijoIzq(pos)] || heap.Heap[pos] < heap.Heap[hijoDer(pos)]){
                if(heap.Heap[hijoIzq(pos)] > heap.Heap[hijoDer(pos)]){
                    intercambiar(pos, hijoIzq(pos));
                    maxHeapify(hijoIzq(pos));
                }else{
                    intercambiar(pos, hijoIzq(pos));
                    maxHeapify(hijoIzq(pos));
                }
            }
        }
    }


    @Override
    public void Eliminar() {
        if(heap.tipo_heap == "max"){
            heap.Heap[0] = heap.Heap[heap.tamano];
            heap.tamano--;
            maxHeapify(0);
        }else{

        }
        
    }

    @Override
    public boolean HeapVacio() {
        return heap.tamano == 0;
    }
    
}

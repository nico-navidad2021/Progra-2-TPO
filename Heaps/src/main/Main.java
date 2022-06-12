package main;

import api.HeapTDA;
import imp.HeapAO;

public class Main {
    public static void main(String[] args) throws Exception {
        HeapTDA heap = new HeapAO();
        heap.InicializarHeap("max");
        heap.Insertar(10);
        heap.Insertar(50);
        heap.Insertar(40);
        heap.Insertar(60);
        heap.Insertar(41);
        MostrarHeap(heap);
        System.out.println("Fin");
    }

    public static void MostrarHeap(HeapTDA heap){
        HeapTDA heap_aux = new HeapAO();
        heap_aux = heap;
        while(!heap_aux.HeapVacio()){
            System.out.println(heap_aux.Primero());
            heap_aux.Eliminar();
        }
    }
}

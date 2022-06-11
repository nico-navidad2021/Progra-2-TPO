package main;

import api.HeapTDA;
import imp.HeapAO;

public class Main {
    public static void main(String[] args) throws Exception {
        HeapTDA heap = new HeapAO();
        heap.InicializarHeap("min");
        heap.Insertar(10);
        heap.Insertar(50);
        heap.Insertar(40);
        heap.Insertar(60);
        heap.Insertar(41);
        System.out.println(heap);
    }

    public void MostrarHeap(HeapTDA heap){

    }
}

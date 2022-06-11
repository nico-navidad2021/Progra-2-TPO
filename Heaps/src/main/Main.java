package main;

import api.HeapTDA;
import imp.HeapAO;

public class Main {
    public static void main(String[] args) throws Exception {
        HeapTDA heap = new HeapAO();
        heap.InicializarHeap("max");
        heap.Insertar(10);
        heap.Insertar(5);
        heap.Insertar(44);
        heap.Insertar(33);
        heap.Insertar(22);
        System.out.println(heap);
    }

    public void MostrarHeap(HeapTDA heap){

    }
}

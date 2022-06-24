package main;

import api.ConjuntoTDA;
import api.GrafoTDA;
import imp.GrafoMA;
import imp.ConjuntoLD;

public class Main {
    public static void main(String[] args) throws Exception {
        GrafoTDA grafo = new GrafoMA();
        grafo.InicializarGrafo();

        grafo.AgregarVertice(1);
        grafo.AgregarVertice(2);
        grafo.AgregarVertice(3);
        grafo.AgregarVertice(4);

        grafo.AgregarArista(1, 2, 7);
        grafo.AgregarArista(1, 4, 2);
        grafo.AgregarArista(1, 3, 7);

        grafo.AgregarArista(2, 1, 1);
        grafo.AgregarArista(2, 4, 1);

        grafo.AgregarArista(3, 2, 2);

        grafo.AgregarArista(4, 3, 1);
        
        MostrarGrafo(grafo);

    }


    public static int CantElemConjunto(ConjuntoTDA conjunto){
        int cant = 0;

        ConjuntoTDA conjunto_2 = Copiar(conjunto);

        while(!conjunto_2.ConjuntoVacio()){
            int elem = conjunto_2.Elegir();
            cant++;
            conjunto_2.Sacar(elem);
        }
        return cant;
    }

    static ConjuntoTDA Copiar(ConjuntoTDA original){
        ConjuntoTDA copia = new ConjuntoLD();
        copia.InicializarConjunto();
        ConjuntoTDA aux = new ConjuntoLD();
        aux.InicializarConjunto();
        
        while(!original.ConjuntoVacio()){
            int elemento = original.Elegir();
            aux.Agregar(elemento);
            original.Sacar(elemento);
        }
        
        while(!aux.ConjuntoVacio()){
            int elemento = aux.Elegir();
            original.Agregar(elemento);
            copia.Agregar(elemento);
            aux.Sacar(elemento);
        }

        return copia;
    }


    public static int[] Conjunto2Array(ConjuntoTDA conjunto){
        int [] elementos = new int[CantElemConjunto(conjunto)];
        for(int i = 0;i< CantElemConjunto(conjunto);i++){
            int elem = conjunto.Elegir();
            elementos[i] = elem;
            conjunto.Sacar(elem);
        }
        return elementos;
    }
    
    public static void MostrarGrafo(GrafoTDA grafo){
        ConjuntoTDA vertices = grafo.Vertices();
        // int[] nodos = Conjunto2Array(vertices);
        
        ConjuntoTDA vertices_aux = Copiar(vertices);

        int cantidadElementos = CantElemConjunto(vertices);

        System.out.println(cantidadElementos);

        for(int i = 0;i<cantidadElementos;i++){
            for(int z = 0;z<cantidadElementos;z++){
                int elem = vertices.Elegir();
                int elem_2 = vertices.Elegir();

                vertices.Sacar(elem);
                vertices.Sacar(elem_2);

                System.out.println("Evaluo: "+elem+"   "+elem_2);
                if(grafo.ExisteArista(elem, elem_2)){
                    System.out.println("Arista entre "+elem+" y "+elem_2+": "+grafo.PesoArista(elem, elem_2));
                }
                    
            }
        }


        // for(int i = 0; i<CantElemConjunto(vertices);i++){
        //     for(int z = 1; z<CantElemConjunto(vertices);z++){
        //         if(grafo.ExisteArista(nodos[i], nodos[z])){
        //             System.out.println("De "+nodos[i]+" a "+nodos[z]+" hay "+grafo.PesoArista(nodos[i], nodos[z]));
        //         }
        //     }   
        // }


        
        

    }


}

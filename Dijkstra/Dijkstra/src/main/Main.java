package main;

import api.ConjuntoTDA;
import api.DiccionarioSimpleTDA;
import api.GrafoTDA;
import imp.GrafoMA;
import imp.ConjuntoLD;
import imp.DicSimpleL;

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
        
        System.out.println("Grafo original:");
        grafo.MostrarAdyacencias();

        GrafoTDA grafo_dijkstra =  CaminoMinimoDijkstra(grafo,4);
        grafo_dijkstra.MostrarAdyacencias();
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

    static void Mostrar(ConjuntoTDA conjunto){
        ConjuntoTDA aux = Copiar(conjunto);
        while(!aux.ConjuntoVacio()){
            int elemento = aux.Elegir();
            System.out.print(elemento+" ");
            aux.Sacar(elemento);
        }
        System.out.println();
    }

    public static DiccionarioSimpleTDA Obtener_aristas(GrafoTDA grafo,int vertice_origen){
        //Obtengo todas la aristas y sus direcciones segun grafo recibido por parametro
        DiccionarioSimpleTDA aristas = new DicSimpleL();
        aristas.InicializarDiccionario();
        
        int canti_vertices = CantElemConjunto(grafo.Vertices());
        ConjuntoTDA vertices = grafo.Vertices();

        for(int z = 0;z<canti_vertices+1;z++){
            int vertice_destino = vertices.Elegir();

            System.out.print("Existe arista entre "+vertice_origen+" y "+vertice_destino);

            if(grafo.ExisteArista(vertice_origen, vertice_destino)){
                System.out.println("     Si");
                aristas.Agregar(vertice_destino, grafo.PesoArista(vertice_origen, vertice_destino));
            }else{
                System.out.println("     No");
            }
            vertices.Sacar(vertice_destino);
            z++;
        }
        return aristas;
    }
    
    static void MostrarDiccionario(DiccionarioSimpleTDA diccionario){
        DiccionarioSimpleTDA aux = diccionario;
        ConjuntoTDA claves = diccionario.Claves();

        while(!claves.ConjuntoVacio()){
            int clave = claves.Elegir();
            System.out.println("Clave: "+clave+"   Valor: "+aux.Recuperar(clave));
            claves.Sacar(clave);
        }
    }
    
    static int ObtenerMenorArista(DiccionarioSimpleTDA aristas, ConjuntoTDA vertices_pendientes, ConjuntoTDA vertices_visitados){
        ConjuntoTDA claves = aristas.Claves();
        int vertice = 0;
        int peso_menor_arista = Integer.MAX_VALUE;

        MostrarDiccionario(aristas);


        while(!claves.ConjuntoVacio()){
            int clave = claves.Elegir();
            
            System.out.println(clave+" ya fue visitado ?"+vertices_visitados.Pertenece(clave));
            int peso_evaluado = aristas.Recuperar(clave);

            if(peso_menor_arista >= peso_evaluado && vertices_visitados.Pertenece(clave)){
                System.out.println("Claves dentro del obtenerMenorArista: ");
                MostrarDiccionario(aristas);
                peso_menor_arista = peso_evaluado;
                vertice = clave;
            }
            claves.Sacar(clave);
        }
        return vertice;
    }

    public static GrafoTDA CaminoMinimoDijkstra(GrafoTDA grafo_original, int origen){
        GrafoTDA grafo_dijkstra = new GrafoMA();
        grafo_dijkstra.InicializarGrafo();
        grafo_dijkstra.AgregarVertice(origen);

        ConjuntoTDA vertices_pendientes = grafo_original.Vertices();
        vertices_pendientes.Sacar(origen);

        ConjuntoTDA vertices_visitados = new ConjuntoLD();
        vertices_visitados.InicializarConjunto();
        vertices_visitados.Agregar(origen);

        int vertice_evaluado = origen;

        while(!vertices_pendientes.ConjuntoVacio()){
            System.out.println("Vertice evaluado: "+vertice_evaluado);
            DiccionarioSimpleTDA aristas = Obtener_aristas(grafo_original, vertice_evaluado);
            MostrarDiccionario(aristas);

            int menor = ObtenerMenorArista(aristas, vertices_pendientes,vertices_visitados);
            System.out.println("Vertice con menor arista: "+menor);
            System.out.println();
            grafo_dijkstra.AgregarVertice(menor);
            grafo_dijkstra.AgregarArista(vertice_evaluado, menor, aristas.Recuperar(menor));

            vertices_visitados.Agregar(menor);
            vertice_evaluado = menor;
            vertices_pendientes.Sacar(menor);


        }


        return grafo_dijkstra;
    }



}

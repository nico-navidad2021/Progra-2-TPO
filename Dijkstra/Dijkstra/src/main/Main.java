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

        GrafoTDA grafo_dijkstra =  CaminoMinimoDijkstra(grafo,2);
        grafo_dijkstra.MostrarAdyacencias();
    }

    public static int CantElemConjunto(ConjuntoTDA conjunto){
        //Aumento un contador a raiz de la cantidad de elementos del conjunto recibido por parametro
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
        //Genero una copia del conjunto recibido usando conjuntos auxiliares y asi evitar la destruccion del conjunto original
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

    static void Mostrar(ConjuntoTDA conjunto){
        //Recorro una copia del conjunto original y voy mostrando los elementos por pantalla
        ConjuntoTDA aux = Copiar(conjunto);
        while(!aux.ConjuntoVacio()){
            int elemento = aux.Elegir();
            System.out.print(elemento+" ");
            aux.Sacar(elemento);
        }
        System.out.println();
    }

    public static DiccionarioSimpleTDA Obtener_aristas(GrafoTDA grafo,int vertice_origen){
        //Devulevo un diccionario con todas la aristas y su vertice de detino segun grafo y vertice de origen recibidos por parametro
        DiccionarioSimpleTDA aristas = new DicSimpleL();
        aristas.InicializarDiccionario();
        
        ConjuntoTDA vertices = grafo.Vertices(); //Me traigo todos los vertices del grafo

        while(!vertices.ConjuntoVacio()){
            int vertice_destino = vertices.Elegir();
            if(grafo.ExisteArista(vertice_origen, vertice_destino)){
                //Si existe arista entre vertice de origen y el de destino, agrego como KEY el vertice de detino y VALUE el peso de esa arista 
                aristas.Agregar(vertice_destino, grafo.PesoArista(vertice_origen, vertice_destino));
            }
            vertices.Sacar(vertice_destino);
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
    
    static int ObtenerVerticeMenorArista(DiccionarioSimpleTDA aristas, ConjuntoTDA vertices_pendientes, ConjuntoTDA vertices_visitados){
        //Devuelvo el vertice del diccionario que recibo por parametro que tenga el menor peso en su arista
        
        //Claves = Verices
        //Clave = Vertice
        
        ConjuntoTDA claves = aristas.Claves(); //Todas las claves del diccionario
        int vertice = 0;
        int peso_menor_arista = Integer.MAX_VALUE; //Uso esta variable para ir comparando el peso de la arista que vaya evaluando,
                                                    //Integer.MAX_VALUE devuelve 2147483647, este es el maximo valor que un Integer puede tener

        // MostrarDiccionario(aristas);//DEBUG

        while(!claves.ConjuntoVacio()){ //Itero hasta quedarme sin claves
            int clave = claves.Elegir(); 
            int peso_evaluado = aristas.Recuperar(clave); //Peso del vertice

            //Si el peso del vertice que estoy evaluando es menor al que yo consideraba como menor, lo establezco como nuevo menor
            // Tambien guardo el vertice que corresponde a este nuevo peso minimo
            if(peso_menor_arista >= peso_evaluado && !vertices_visitados.Pertenece(clave)){
                peso_menor_arista = peso_evaluado;
                vertice = clave;
            }
            claves.Sacar(clave);
        }
        return vertice;
    }

    public static GrafoTDA CaminoMinimoDijkstra(GrafoTDA grafo_original, int origen){
        GrafoTDA grafo_dijkstra = new GrafoMA(); //Nuevo grafo resultanto
        grafo_dijkstra.InicializarGrafo();
        grafo_dijkstra.AgregarVertice(origen); //El vertice que reciba por parametro siempre va a ser el primer vertice de mi nuevo grafo

        ConjuntoTDA vertices_pendientes = grafo_original.Vertices(); //Conjunto para monitorear que verices me faltan por evaluar
        vertices_pendientes.Sacar(origen);

        ConjuntoTDA vertices_visitados = new ConjuntoLD(); //Conjunto para monitorear que verices ya evalue
        vertices_visitados.InicializarConjunto();
        vertices_visitados.Agregar(origen);

        int vertice_evaluado = origen; //Vertice del cual voy a evaluar sus aristas 

        while(!vertices_pendientes.ConjuntoVacio()){
            DiccionarioSimpleTDA aristas = Obtener_aristas(grafo_original, vertice_evaluado); //Me traigo todas sus aristas
            MostrarDiccionario(aristas);
            int menor = ObtenerVerticeMenorArista(aristas, vertices_pendientes,vertices_visitados); //Obtengo el vertice con menor arista
            grafo_dijkstra.AgregarVertice(menor); //Agrego el menor de los nodos al grafo resultante
            grafo_dijkstra.AgregarArista(vertice_evaluado, menor, aristas.Recuperar(menor)); //Establezco la arista entre los nodos

            vertices_visitados.Agregar(menor);
            vertice_evaluado = menor;
            vertices_pendientes.Sacar(menor);
        }
        return grafo_dijkstra;
    }
}

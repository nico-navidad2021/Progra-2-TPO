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
        return (2*pos)+1;
    }

    private int hijoDer(int pos){
        return (2*pos)+2;
    }

    private boolean esHoja(int pos){
        // System.out.println("pos "+pos);
        // System.out.println("indice "+indice);
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

    private void maxHeapify(int pos){
        if(esHoja(pos)){
            return;
        }else{
            //Si la posicion en la que me encuentro es mayor a ambos hijos
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
        // System.out.println("Valor a analizar:"+Heap[pos]);
        if(esHoja(pos)){
            // System.out.println("Es hoja");
            return;
        }else{
            // System.out.println("No es hoja");

            //Si la posicion en la que me encuentro es mayor a ambos hijos
            
            
            // System.out.println(Heap[pos]+" tiene hijo izquierdo ? "+tieneHijoIzq(pos));
            // System.out.println(Heap[pos]+" tiene hijo derecho ? "+tieneHijoDer(pos));
            
            
            // System.out.println("Hijo izquierdo de "+Heap[pos]+": "+Heap[hijoIzq(pos)]);
            // System.out.println("Hijo derecho de "+Heap[pos]+": "+Heap[hijoDer(pos)]);

            
            if(tieneHijoDer(pos) && tieneHijoIzq(pos)){
                // System.out.println(Heap[pos]+" tiene hijo izquierdo y derecho...");
                if(Heap[pos] > Heap[hijoIzq(pos)] || Heap[pos] > Heap[hijoDer(pos)]){
                    if(Heap[hijoIzq(pos)] < Heap[hijoDer(pos)]){
                        
                        // System.out.println("Intercambio "+Heap[pos]+" por hijo izquierdo: "+Heap[hijoIzq(pos)]);
                        
                        intercambiar(pos, hijoIzq(pos));
                        System.out.println("");
                        minHeapify(hijoIzq(pos));
                    }else{
    
                        // System.out.println("Intercambio "+Heap[pos]+" por hijo derecho: "+Heap[hijoDer(pos)]);
    
    
                        intercambiar(pos, hijoDer(pos));
                        System.out.println("");

                        minHeapify(hijoDer(pos));
                    }
                } 
            }else if(!tieneHijoDer(pos)){
                // System.out.println(Heap[pos]+" solamente tiene hijo izquierdo...");
                if(Heap[pos] > Heap[hijoIzq(pos)]){
                    // System.out.println("Intercambio "+Heap[pos]+" por hijo izquierda: "+Heap[hijoIzq(pos)]);
                    intercambiar(pos, hijoIzq(pos));
                    System.out.println("");

                    minHeapify(hijoIzq(pos));
                }
                
            }else{
                // System.out.println(Heap[pos]+" solamente tiene hijo derecho...");
                if(Heap[pos] > Heap[hijoDer(pos)]){
                    // System.out.println("Intercambio "+Heap[pos]+" por hijo derecho: "+Heap[hijoDer(pos)]);
                    intercambiar(pos, hijoDer(pos));
                    System.out.println("");

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
        if(tipo_heap == "max"){
            int ultimo_elemento = Heap[indice-1];
            Heap[0] = ultimo_elemento;
            Heap[indice-1] = 0;
            indice--;
            maxHeapify(0);
            // MostrarLista(Heap);
        }else{
            // System.out.println("Heap antes de la eliminacion");
            MostrarLista(Heap);
            int ultimo_elemento = Heap[indice-1];
            // System.out.println("Se va a elimina el elemento "+Heap[0]+" y va a ser reemplazado por "+ ultimo_elemento);
            Heap[0] = ultimo_elemento;
            Heap[indice-1] = 0;
            indice--;
            // MostrarLista(Heap);
            minHeapify(0);
            // MostrarLista(Heap);
        }
        
    }

    @Override
    public boolean HeapVacio() {
        return indice == 0;
    }
    
}

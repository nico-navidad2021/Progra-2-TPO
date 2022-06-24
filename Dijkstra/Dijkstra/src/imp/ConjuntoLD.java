package imp;

import api.ConjuntoTDA;

public class ConjuntoLD implements ConjuntoTDA{
    
    class Nodo{
        int valor;
        Nodo sig;
    }

    Nodo c;

    @Override
    public void InicializarConjunto() {
        c = null;
    }

    @Override
    public boolean ConjuntoVacio() {
        return (c == null);
    }

    @Override
    public void Agregar(int x) {
        if(!Pertenece(x)){
            Nodo nuevo = new Nodo();
            nuevo.valor = x;
            nuevo.sig = c;
            c = nuevo;    
        }        
    }

    @Override
    public void Sacar(int x) {

        if(c != null){
            if(c.valor == x){
                c = c.sig;
            }else{
                Nodo viajero = c;
                while((viajero.sig != null) && (viajero.sig.valor != x)){
                    viajero = viajero.sig;
                }
                if(viajero.sig != null){
                    viajero.sig = viajero.sig.sig;
                }
            }
        }
        
    }

    @Override
    public int Elegir() {
        return c.valor;
    }

    @Override
    public boolean Pertenece(int x) {
        Nodo viajero = c;
        
        if(viajero == null){
            return false;
        }else{
            while((viajero != null) && (viajero.valor != x)){
                viajero = viajero.sig;
            }
        }
        return (viajero != null);
    }
    
}

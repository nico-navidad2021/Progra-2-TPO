package imp;

import api.ConjuntoTDA;
import api.DiccionarioSimpleTDA;

public class DicSimpleL implements DiccionarioSimpleTDA{

    class NodoClave{
        int clave;
        int valor;
        NodoClave sigClave;
    }

    NodoClave origen;

    @Override
    public void InicializarDiccionario() {
        origen = null;
    }

    @Override
    public void Agregar(int key, int value) {
        NodoClave aux = Clave2NodoClave(key);
        if(aux == null){
            aux = new NodoClave();
            aux.clave = key;
            aux.sigClave = origen;
            origen = aux;
        }
        aux.valor = value;
    }

    @Override
    public void Eliminar(int key) {
        if(origen != null){
            if(origen.clave == key){
                origen = origen.sigClave;
            }else{
                NodoClave viajero = origen;
                while(viajero != null && viajero.sigClave.clave != key){
                    viajero = viajero.sigClave;
                }
                if(viajero.sigClave != null){
                    viajero.sigClave = viajero.sigClave.sigClave;
                }
            }
        }
        
    }

    @Override
    public int Recuperar(int clave) {
        NodoClave aux = Clave2NodoClave(clave);
        return aux.valor;
    }

    @Override
    public ConjuntoTDA Claves() {
        ConjuntoTDA claves = new ConjuntoLD();
        claves.InicializarConjunto();
        
        NodoClave aux = origen;

        while(aux!=null){
            claves.Agregar(aux.clave);
            aux = aux.sigClave;
        }
        
        return claves;
    }
    
    private NodoClave Clave2NodoClave(int clave){
        NodoClave aux = origen;
        while(aux != null && aux.clave != clave){
            aux = aux.sigClave;
        }
        return aux;
    }
}

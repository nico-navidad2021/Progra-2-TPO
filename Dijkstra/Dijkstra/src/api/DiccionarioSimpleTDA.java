package api;

public interface DiccionarioSimpleTDA {
    void InicializarDiccionario();
    void Agregar(int key, int value);
    void Eliminar(int key);
    int Recuperar(int clave);
    ConjuntoTDA Claves();
}

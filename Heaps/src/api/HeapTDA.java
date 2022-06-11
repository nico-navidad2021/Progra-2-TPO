package api;

public interface HeapTDA {
    void InicializarHeap(String tipo);
    int Primero();
    void Insertar(int x);
    void Eliminar();
    boolean HeapVacio();
}

package api;

public interface HeapTDA {
    void InicializarHeap(Boolean tipo);
    int Primero();
    void Insertar(int x);
    void Eliminar();
    boolean HeapVacio();
}

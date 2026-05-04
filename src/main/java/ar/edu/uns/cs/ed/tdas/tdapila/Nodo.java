package ar.edu.uns.cs.ed.tdas.tdapila;
import ar.edu.uns.cs.ed.tdas.Position;
public class Nodo<E> implements Position<E> {

    private E element;
    private Nodo<E> next;

    public Nodo(E element, Nodo<E> next) {
        this.element = element;
        this.next = next;
    }


    public Nodo<E> getNext() {
        return next;
    }

    public void setNext(Nodo<E> next) {
        this.next = next;
    }

    @Override
    public E element() {

        return element;
    }

    
}

package ar.edu.uns.cs.ed.tdas.tdalista;
import ar.edu.uns.cs.ed.tdas.Position;
public class NodoDE<E> implements Position<E> {

    private E element;
    private NodoDE<E> next;
    private NodoDE<E> prev; 
    
    public NodoDE(E element, NodoDE<E> next, NodoDE<E> prev) {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }
    public NodoDE(E element){
        this.element= element;
        this.next = null;
        this.prev = null;
    }

    public NodoDE<E> getNext() {
        return next;
    }

    public void setNext(NodoDE<E> next) {
        this.next = next;
    }

    public NodoDE<E> getPrev() {
        return prev;
    }

    public void setPrev(NodoDE<E> prev) {
        this.prev = prev;
    }

    public void SetElement(E aux){
        element = aux;
    }


    @Override
    public E element() {
        return element;
    }

    
}

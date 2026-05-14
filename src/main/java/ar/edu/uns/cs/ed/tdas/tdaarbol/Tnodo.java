package ar.edu.uns.cs.ed.tdas.tdaarbol;
import ar.edu.uns.cs.ed.tdas.*;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import ar.edu.uns.cs.ed.tdas.excepciones.*  ;
public class Tnodo<E> implements Position<E> {
    private E element;
    private Tnodo<E> padre;
    private PositionList<Tnodo<E>> children;

    public Tnodo(E element, Tnodo<E> padre) {
        this.element = element;
        this.padre = padre;
        this.children = new ListaDE<Tnodo<E>>();
    }

    @Override
    public E element(){
        return element;
    }

    public Tnodo<E> getParent() {
        return padre;
    }

    public PositionList<Tnodo<E>> getHijos() {
        return children;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setFather(Tnodo<E> padre) {
        this.padre = padre;
    }
   

    
}

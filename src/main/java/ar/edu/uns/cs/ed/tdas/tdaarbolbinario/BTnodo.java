package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;
import ar.edu.uns.cs.ed.tdas.*;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import ar.edu.uns.cs.ed.tdas.excepciones.*  ;
public class BTnodo<E> implements Position<E> {
    private E element;
    private BTnodo<E> padre,left,right;

    public BTnodo(E element, BTnodo<E> padre) {
        this.element = element;
        this.padre = padre;
        this.left = null;
        this.right = null;
    }
    public BTnodo(E element, BTnodo<E> padre,BTnodo<E> left, BTnodo<E> right) {
        this.element = element;
        this.padre = padre;
        this.left = left;
        this.right = right;
    }

    @Override
    public E element(){
        return element;
    }

    public BTnodo<E> getParent() {
        return padre;
    }

    public BTnodo<E> getLeft() {
        return left;
    }

    public BTnodo<E> getRight() {
        return right;
    }
    
    public void setElement(E element) {
        this.element = element;
    }

    public void setFather(BTnodo<E> padre) {
        this.padre = padre;
    }
    public void setLeft(BTnodo<E> left) {
        this.left = left;
    }
    public void setRight(BTnodo<E> right) {
        this.right = right;
    }
   

    
}

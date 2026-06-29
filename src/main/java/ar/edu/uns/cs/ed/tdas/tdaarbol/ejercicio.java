package ar.edu.uns.cs.ed.tdas.tdaarbol;
import ar.edu.uns.cs.ed.tdas.*;
import java.util.Iterator;

public class ejercicio {
    public <E> void cambiarRotulo (Tree<E> arbol, E e, E f){
        Iterator<Position<E>> it = arbol.positions().iterator();
        Position<E> aux= null;
        while(it.hasNext()){
            aux = it.next();
            if (aux.element().equals(e)){
                arbol.replace(aux, f);
            }
        }
    }

}

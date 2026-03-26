package ar.edu.uns.cs.ed.tdas.tdaarbol;
import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.Position;

/**
 * Interface Conjunto
 * Es la versión extendida de la interfaz presentada por Goodrich y Tamassia en su cuarta edición. 
 * En esta interfaz se incluyen las operaciones necesarias para modificar el árbol.
 * @author Cátedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computación, UNS.
 */
public interface conjunto<E> {
public int size();
public int capacity();
public boolean isEmpty();
public E get(int i);
public void put(E elem);    
public boolean pertenece(E elem);
public conjunto<E> interseccion(conjunto<E> c);

}

	
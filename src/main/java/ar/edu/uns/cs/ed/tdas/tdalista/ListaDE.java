package ar.edu.uns.cs.ed.tdas.tdalista;
import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
public class ListaDE<E> implements PositionList<E> {

    private NodoDE<E> head;
	private NodoDE<E> tail;
    private int size;

    public ListaDE() {
        head= new NodoDE<E>(null, null, null);
		tail= new NodoDE<E>(null, null, null);
		head.setNext(tail);
		tail.setPrev(head);
        size = 0;
        }
    
	private NodoDE<E> checkPosition(Position<E> p) throws InvalidPositionException{
		NodoDE<E> toRet = null;
		if (p == null || isEmpty()) {
			throw new InvalidPositionException("Posicion invalida!");
		}
		try {
			toRet = (NodoDE<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Error de casteo!");
		}
		return toRet;
	}
    /**
	 * Consulta la cantidad de elementos de la lista.
	 * @return Cantidad de elementos de la lista.
	 */
	public int size(){
        return size;
    };
	
	/**
	 * Consulta si la lista está vacía.
	 * @return Verdadero si la lista está vacía, falso en caso contrario.
	 */
	public boolean isEmpty(){
        return size == 0;
    };
	
	/**
	 * Devuelve la posición del primer elemento de la lista. 
	 * @return Posición del primer elemento de la lista.
	 * @throws EmptyListException si la lista está vacía.
	 */
	public Position<E> first()throws EmptyListException{
		if(isEmpty())throw new EmptyListException(null);
		return head.getNext();
    }
	
	/**
	 * Devuelve la posición del último elemento de la lista. 
	 * @return Posición del último elemento de la lista.
	 * @throws EmptyListException si la lista está vacía.
	 * 
	 */
	public Position<E> last()throws EmptyListException{
		if(isEmpty())throw new EmptyListException(null);
		return tail.getPrev();
    }
	
	/**
	 * Devuelve la posición del elemento siguiente a la posición pasada por parámetro.
	 * @param p Posición a obtener su elemento siguiente.
	 * @return Posición del elemento siguiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si el posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde al último elemento de la lista.
	 */
	public Position<E> next(Position<E> p)throws InvalidPositionException,BoundaryViolationException{
		NodoDE<E> nodo = checkPosition(p);
		if(nodo==tail.getPrev())throw new BoundaryViolationException(null);
		return nodo.getNext();
	}
	
	/**
	 * Devuelve la posición del elemento anterior a la posición pasada por parámetro.
	 * @param p Posición a obtener su elemento anterior.
	 * @return Posición del elemento anterior a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde al primer elemento de la lista.
	 */
	public Position<E> prev(Position<E> p)throws InvalidPositionException,BoundaryViolationException{
		NodoDE<E> nodo = checkPosition(p);
		if(nodo==head.getNext())throw new BoundaryViolationException(null);
		return nodo.getPrev();
	}
	/**
	 * Inserta un elemento al principio de la lista.
	 * @param element Elemento a insertar al principio de la lista.
	 */
	public void addFirst(E element){
		NodoDE<E> nodo = new NodoDE<E>(element, null, null);
		nodo.setNext(head.getNext());
		nodo.setPrev(head);
		head.getNext().setPrev(nodo);
		head.setNext(nodo);
		size++;
	}
	
	/**
	 * Inserta un elemento al final de la lista.
	 * @param element Elemento a insertar al final de la lista.
	 */
	public void addLast(E element){
		NodoDE<E> nodo = new NodoDE<E>(element, null, null);
		nodo.setNext(tail);
		nodo.setPrev(tail.getPrev());
		tail.getPrev().setNext(nodo);
		tail.setPrev(nodo);
		size++;
	}
	
	/**
	 * Inserta un elemento luego de la posición pasada por parámatro.
	 * @param p Posición en cuya posición siguiente se insertará el elemento pasado por parámetro.
	 * @param element Elemento a insertar luego de la posición pasada como parámetro.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */
	public void addAfter(Position<E> p, E element)throws InvalidPositionException{
		NodoDE<E> nodo = checkPosition(p);
		NodoDE<E> aux = new NodoDE<E>(element, nodo.getNext(), nodo);
		nodo.getNext().setPrev(aux);
		nodo.setNext(aux);
		size++;
	}
	
	/**
	 * Inserta un elemento antes de la posición pasada como parámetro.
	 * @param p Posición en cuya posición anterior se insertará el elemento pasado por parámetro. 
	 * @param element Elemento a insertar antes de la posición pasada como parámetro.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */
	public void addBefore(Position<E> p, E element)throws InvalidPositionException{
		NodoDE<E> nodo = checkPosition(p);
		NodoDE<E> aux = new NodoDE<E>(element, nodo, nodo.getPrev());
		nodo.getPrev().setNext(aux);
		nodo.setPrev(aux);
		size++;
	}
	
	/**
	 * Remueve el elemento que se encuentra en la posición pasada por parámetro.
	 * @param p Posición del elemento a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */	
	public E remove(Position<E> p)throws InvalidPositionException{
		NodoDE<E> nodo = checkPosition(p);
		E toRet = nodo.element();
		nodo.getPrev().setNext(nodo.getNext());
		nodo.getNext().setPrev(nodo.getPrev());
		nodo.setNext(null);
		nodo.setPrev(null);
		size--;
		return toRet;
	}

	/**
	
	 * Establece el elemento en la posición pasados por parámetro. Reemplaza el elemento que se encontraba anteriormente en esa posición y devuelve el elemento anterior.
	 * @param p Posición a establecer el elemento pasado por parámetro.
	 * @param element Elemento a establecer en la posición pasada por parámetro.
	 * @return Elemento anterior.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.	 
	 */
	public E set(Position<E> p, E element)throws InvalidPositionException{
		NodoDE<E> nodo = checkPosition(p);
		E toRet = nodo.element();
		nodo.SetElement(element);
		return toRet;
	}
	
	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * @return Un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator(){
		Iterator<E> aux = new iterador<E>(this);
		return aux;
	}
	
	
	/**
	 * Devuelve una colección iterable de posiciones.
	 * @return Una colección iterable de posiciones.
	 */
	
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toRet = new ListaDE<>();
		if (!isEmpty()) {
			try {
				Position<E> pos = first();
				while (pos != last()) {
					toRet.addLast(pos);
					pos = next(pos);
				}
				toRet.addLast(pos);
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		}
		return toRet;
	}
	
	public void Ej2 (E e1, E e2) throws EmptyListException{

    // insertar e1 como segundo
    NodoDE<E> first = head.getNext();
    NodoDE<E> nodo1 = new NodoDE<E>(e1, first.getNext(), first);

    first.setNext(nodo1);
    nodo1.getNext().setPrev(nodo1);

    size++;

    // insertar e2 como anteultimo
    NodoDE<E> last = tail.getPrev();

    NodoDE<E> nodo2 = new NodoDE<E>(e2, tail, last);

    last.setNext(nodo2);
    tail.setPrev(nodo2);

    size++;
	}

	public boolean e1estaenlista (E e1, PositionList<E> lista){
		boolean toRet = false;
		Iterator<E> it = lista.iterator();
		while (it.hasNext()&&!toRet) {
			E elemento = it.next();
			toRet = elemento.equals(e1);
		}
		return toRet;
	}

	public int cantvecese1 (E e1, PositionList<E> lista){
		int cont= 0;
		Iterator<E> it = lista.iterator();
		while(it.hasNext()){
			if(it.next().equals(e1))cont++;
		}
		return cont;
	}

	public boolean xlomenosveces (E e1, PositionList<E> lista,int contador){
		int cont= 0;
		Iterator<E> it = lista.iterator();
		while(it.hasNext()&&cont<=contador){
			if(it.next()==e1)cont++;
		}
		return cont>=contador;
	}

	public PositionList<E> repetirelems (PositionList<E> l){
		PositionList<E> toRet = new ListaDE<>();
		for(E elem:l){
			toRet.addLast(elem);
			toRet.addLast(elem);
		}
		return toRet;
	}

	//Ejercicio 5:
// Escriba un método tal que dadas dos PositionList<Character> l1 y l2 elimine de l2 todos los elementos que también
// están en l1. Este método debe retornar un iterable con todos los elementos eliminados.

	public Iterable<E> eliminarComunes(PositionList<E> l1, PositionList<E> l2) {
		PositionList<E> eliminados = new ListaDE<>();
		PositionList<E> posicionesARemover = new ListaDE<>();
		
		// Collect all positions from l2 that have elements in l1
		for (Position<E> pos : l2.positions()) {
			if (e1estaenlista(pos.element(), l1)) {
				posicionesARemover.addLast(pos.element());
			}
		}
		for (Position<E> pos : posicionesARemover.positions()) {
			eliminados.addLast(l2.remove(pos));
		}
		
		return eliminados;
	}
    
	public PositionList<E> IntercalarListas(PositionList<E> l1,PositionList<E> l2){
		PositionList<E> toRet = new ListaDE<>();

		Iterator<E> it1 = l1.iterator();
		Iterator<E> it2 = l2.iterator();

		while(it1.hasNext()&&it2.hasNext()){
			E aux1= it1.next();
			E aux2= it2.next();
			toRet.addLast(aux1);
			toRet.addLast(aux2);
		}
		
		while(it1.hasNext()&&!it2.hasNext()){
			E aux1= it1.next();
			toRet.addLast(aux1);
		}
		
		while(!it1.hasNext()&&it2.hasNext()){
			E aux2= it2.next();
			toRet.addLast(aux2);
		}

		return toRet;
	}
// Implemente un método eliminar(L1,L2), que modifique la lista L1 de la siguiente manera: primero deberá eliminar de
// la misma todas las apariciones de los elementos contenidos en L2, luego deberá insertar al final de la misma todos
// los elementos de L2 pero en orden inverso al que aparecen en L2. Puede considerar que L2 no tiene elementos
// repetidos. No se debe modificar el estado interno de la lista L2.

	public void eliminarl1l2(PositionList<E> l1, PositionList<E> l2){
		

	}

	public PositionList<E> CloneProfundidad(){
		PositionList<E>  toRet = new ListaDE<>();
		for(E elem:this){
			toRet.addLast(elem);
		}
		return toRet;
	}
// Implemente el método atp, que dado un elemento e, inserta el
// elemento adelante de cada uno de los elementos ya existentes en la lista.
// Excepto cuando el elemento que ya está en la lista es igual a e. Si la lista está
// vacía no hay que hacer nada. Implemente cualquier método auxiliar que
// utilice (incluso si son métodos del TDA).
	public void atp(E elem){
			NodoDE<E> puntero = this.head.getNext();
			while (puntero!=this.tail){
				if(!(puntero.element().equals(elem))){
					NodoDE<E> nuevo = new NodoDE<E>(elem, puntero.getNext(), puntero);
					puntero.getNext().setPrev(nuevo);
					puntero.setNext(nuevo);
					size++;
				}
				puntero.getNext();
			}	
	}

	/**
	 * Crea una copia superficial de la lista sin el elemento especificado.
	 * @param elem Elemento a excluir de la copia.
	 * @return Una nueva lista que es copia de la lista actual sin el elemento especificado.
	 * @throws EmptyListException si la lista está vacía.
	 */
	public PositionList<E> copiarSinElem(E elem) throws EmptyListException {
		if (this.size == 0) throw new EmptyListException(null);
		
		PositionList<E> copia = new ListaDE<>();
		
		// Iterar sobre los elementos de la lista actual
		for (E element : this) {
			// Si el elemento no es igual al elemento a excluir, agregarlo a la copia
			if (!element.equals(elem)) {
				copia.addLast(element);
			}
		}
		
		return copia;
	}

	public int eliminarDesdePosicion(Position<E> p, int k) throws InvalidPositionException, BoundaryViolationException {
		NodoDE<E> nodo = checkPosition(p);
		int eliminados = 0;
		
		for (int i = 0; i < k; i++) {
			if (nodo == tail.getPrev()) {
				break; // No hay más elementos para eliminar
			}
			nodo = nodo.getNext(); // Mover al siguiente nodo
			remove(nodo); // Eliminar el nodo actual
			eliminados++;
		}
		
		return eliminados;
	}

	public void duplicarElementos(PositionList<E> lista)throws EmptyListException{
		if(isEmpty())throw new EmptyListException("lista vacia");
		for(Position<E> pos:lista.positions()){
			lista.addBefore(pos, pos.element());
		}
	}

	// Mueve los últimos k elementos al frente conservando el orden.
	// Retorna un iterable con los elementos que NO fueron movidos.
	// Ejemplo: (a,b,c,d,e) con k=2 → lista queda (d,e,a,b,c), retorna (a,b,c)
	public Iterable<E> moverAlFrente(int k) {
		PositionList<E> noMovidos = new ListaDE<>();

		if (k <= 0 || isEmpty()) {
			for (E elem : this) noMovidos.addLast(elem);
			return noMovidos;
		}

		if (k >= size) return noMovidos; // mover todo = sin cambio neto, nada queda afuera

		// Navegar hasta el pivot: último nodo que NO se mueve (posición size-k)
		NodoDE<E> pivot = head.getNext();
		for (int i = 1; i < size - k; i++) {
			pivot = pivot.getNext();
		}

		// Recolectar los elementos no movidos para el retorno
		NodoDE<E> cur = head.getNext();
		NodoDE<E> kStart = pivot.getNext(); // primer nodo del grupo a mover
		while (cur != kStart) {
			noMovidos.addLast(cur.element());
			cur = cur.getNext();
		}

		// Referencias clave
		NodoDE<E> kEnd     = tail.getPrev();  // último nodo del grupo a mover
		NodoDE<E> firstElem = head.getNext(); // primer nodo actual de la lista

		// Rearmar: head → [k elementos] → [resto] → tail
		head.setNext(kStart);
		kStart.setPrev(head);

		kEnd.setNext(firstElem);
		firstElem.setPrev(kEnd);

		pivot.setNext(tail);
		tail.setPrev(pivot);

		return noMovidos;
	}
}

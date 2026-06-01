package ar.edu.uns.cs.ed.tdas.tdaarbol;
import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import ar.edu.uns.cs.ed.tdas.tdamapeo.*;
/**
 * Clase Arbol
 * Es la implementación de la interfaz Tree utilizando nodos con referencias a sus hijos y a su padre.
 * @author Cátedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computación, UNS.
 */ 
public class Arbol<E> implements Tree<E> {
    private Tnodo<E> root;
    private int size;
    public Arbol() {
        root = null;
        size = 0;
    }

/**
	 * Consulta la cantidad de nodos en el árbol.
	 * @return Cantidad de nodos en el árbol.
	 */
	public int size(){
        return size;
    }
	
	/**
	 * Consulta si el árbol está vacío.
	 * @return Verdadero si el árbol está vacío, falso en caso contrario.
	 */
	public boolean isEmpty(){
        return size == 0;
    }
	
	/**
	 * Reemplaza el elemento almacenado en la posición dada por el elemento pasado por parámetro. Devuelve el elemento reemplazado.
	 * @param v Posición de un nodo.
	 * @param e Elemento a reemplazar en la posición pasada por parámetro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public E replace(Position<E> v, E e)throws InvalidPositionException{
		Tnodo<E> nodo = checkposition(v);
		E oldElement = nodo.element();
		nodo.setElement(e);
		return oldElement;
	}
	
	// Método auxiliar para verificar la validez de una posición y convertirla a Tnodo
	private Tnodo<E> checkposition(Position<E> v) throws InvalidPositionException {
		if (v == null || isEmpty()) {
			throw new InvalidPositionException("Posición inválida");
		}
		try {
			return (Tnodo<E>) v;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Posición no pertenece a este árbol");
		}
	}
	/**
	 * Devuelve la posición de la raíz del árbol.
	 * @return Posición de la raíz del árbol.
	 * @throws EmptyTreeException si el árbol está vacío.
	 */
	public Position<E> root()throws EmptyTreeException {
		if(isEmpty())throw new EmptyTreeException("El árbol está vacío");
		return root;
	}
	
	/**
	 * Devuelve la posición del nodo padre del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Posición del nodo padre del nodo correspondiente a la posición dada.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde a la raíz del árbol.
	 */
	public Position<E> parent(Position<E> v)throws InvalidOperationException, BoundaryViolationException{
		Tnodo<E> nodo = checkposition(v);
		if (nodo == root) {
			throw new BoundaryViolationException("La raíz no tiene padre");
		}
		return nodo.getParent();
	}
	
	/**
	 * Devuelve una colección iterable de los hijos del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Colección iterable de los hijos del nodo correspondiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public Iterable<Position<E>> children(Position<E> v){
		Tnodo<E> nodo = checkposition(v);
		PositionList<Position<E>> list = new ListaDE<>();
		for(Tnodo<E> nod:nodo.getHijos()){
			list.addLast(nod);
		}
		return list;
	}
	
	/**
	 * Consulta si una posición corresponde a un nodo interno.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isInternal(Position<E> v){
		Tnodo<E> nodo = checkposition(v);
		return nodo.getHijos().size() > 0;
	}
	
	/**
	 * Consulta si una posición dada corresponde a un nodo externo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isExternal(Position<E> v){
		Tnodo<E> nodo = checkposition(v);
		return nodo.getHijos().isEmpty();
	}
	
	/**
	 * Consulta si una posición dada corresponde a la raíz del árbol.
	 * @param v Posición de un nodo.
	 * @return Verdadero, si la posición pasada por parámetro corresponde a la raíz del árbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		Tnodo<E> nodo = checkposition(v);
		return nodo == root;
	}
	
	/**
	 * Crea un nodo con rótulo e como raíz del árbol.
	 * @param E Rótulo que se asignará a la raíz del árbol.
	 * @throws InvalidOperationException si el árbol ya tiene un nodo raíz.
	 */
	public void createRoot(E e) throws InvalidOperationException {
		if (root != null) {
			throw new InvalidOperationException("El árbol ya tiene una raíz");
		}
		root = new Tnodo<>(e, null);
		size = 1;

	}
	
	/**
	 * Agrega un nodo con rótulo e como primer hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param padre Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		Tnodo<E> nodo = checkposition(p);
		Tnodo<E> nuevoHijo = new Tnodo<>(e, nodo);
		nodo.getHijos().addFirst(nuevoHijo);
		size++;
		return nuevoHijo;
	}
	
	/**
	 * Agrega un nodo con rótulo e como último hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		Tnodo<E> padre = checkposition(p);
		Tnodo<E> nuevoHijo = new Tnodo<>(e, padre);
		padre.getHijos().addLast(nuevoHijo);
		size++;
		return nuevoHijo;
	}
	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará delante de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param rb Posición del nodo que será el hermano derecho del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e)throws InvalidPositionException {
		Tnodo<E> padre = checkposition(p);
		Tnodo<E> hermanoderecho = checkposition(rb);
		if(hermanoderecho.getParent()!=padre)throw new InvalidPositionException(null);
		Tnodo<E> nodonuevo = new Tnodo<E>(e, padre);
		Iterator<Position<Tnodo<E>>> it = padre.getHijos().positions().iterator();
		boolean agregado = false;

		while(it.hasNext()&&!agregado){
			Position<Tnodo<E>> pos = it.next();
			if(pos.element()==hermanoderecho){
				agregado=true;
				padre.getHijos().addBefore(pos, nodonuevo);
				size++;
			}
		}
		return nodonuevo;
	}

	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará a continuación de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param lb Posición del nodo que será el hermano izquierdo del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e){
		Tnodo<E> padre = checkposition(p);
		Tnodo<E> hermanoderecho = checkposition(lb);
		if(hermanoderecho.getParent()!=padre)throw new InvalidPositionException(null);
		Tnodo<E> nodonuevo = new Tnodo<E>(e, padre);
		Iterator<Position<Tnodo<E>>> it = padre.getHijos().positions().iterator();
		boolean agregado = false;

		while(it.hasNext()&&!agregado){
			Position<Tnodo<E>> pos = it.next();
			if(pos.element()==hermanoderecho){
				agregado=true;
				padre.getHijos().addAfter(pos, nodonuevo);
				size++;
			}
		}
		return nodonuevo;
		
	}	
	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo externo. 
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo externo, o el árbol está vacío.
	 */
	public void removeExternalNode (Position<E> p)throws InvalidPositionException{
		Tnodo<E> nodo= checkposition(p);
		if(!nodo.getHijos().isEmpty()){
			throw new InvalidPositionException("El nodo no es externo");
		}
		if ( nodo == root() ) {
			root = null; 
			size = 0; 
			nodo.setElement(null); 
			return;
		}
		Tnodo<E> padre = nodo.getParent();
		PositionList<Tnodo<E>> hijospadre = padre.getHijos();
		boolean encontre = false;
		Position<Tnodo<E>> pp = null;
		Iterator<Position<Tnodo<E>>> it = hijospadre.positions().iterator();
		while(it.hasNext()&&!encontre){
			pp = it.next();
			if(pp.element()==nodo){
				encontre = true;
			}
		}
		if(!encontre)throw new InvalidPositionException(" “p no aparece en la lista de hijos de su padre: ¡no eliminé!”" );
		hijospadre.remove(pp);
		nodo.setElement(null);
		nodo.setFather(null);
		size--;
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol, únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo interno o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
	public void removeInternalNode (Position<E> p)throws InvalidPositionException{
		Tnodo<E> nodo = checkposition(p);
		if(nodo.getHijos().isEmpty())throw new InvalidPositionException("no corresponde a un nodo interno");
		if(nodo==root){
			Position<Tnodo<E>> pp = null;
			if(nodo.getHijos().size()>1){
				throw new InvalidPositionException("null");
			}else if(nodo.getHijos().size()==1){
				Iterator<Position<Tnodo<E>>> it = nodo.getHijos().positions().iterator();
				while(it.hasNext()){
					pp=it.next();
				}			
			root = pp.element();
			nodo.setElement(null);
			pp.element().setFather(null);
			size--;
			return;
			}
		}
		Iterable<Position<Tnodo<E>>> ithijosnodo = nodo.getHijos().positions();
		Iterator<Position<Tnodo<E>>> ithijospadre = nodo.getParent().getHijos().positions().iterator();
		Position<Tnodo<E>> puntero = null;
		boolean encontre = false;
		while (ithijospadre.hasNext()&&!encontre){
			puntero = ithijospadre.next();
			if(puntero.element()==nodo){
				encontre=true;
			}
		}
		for(Position<Tnodo<E>> pos :ithijosnodo){
			nodo.getParent().getHijos().addBefore(puntero, pos.element());
			pos.element().setFather(nodo.getParent());
		}
		nodo.getParent().getHijos().remove(puntero);
		puntero.element().setElement(null);
		size--;
		return;
	}
	
	/**
	 * Elimina el nodo referenciado por una posición dada. Si se trata de un nodo interno, los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol, únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
	public void removeNode (Position<E> p)throws InvalidPositionException{
		if(isInternal(p))removeInternalNode(p);
		if(isExternal(p))removeExternalNode(p);
	}
	
	/**
	 * Devuelve un iterador de los elementos almacenados en el árbol en preorden.
	 * @return Iterador de los elementos almacenados en el árbol.
	 */
	public Iterator<E> iterator(){
		PositionList<E> l = new ListaDE<>();
		for (Position<E> p : positions() )
		l.addLast(p.element() );
	return l.iterator(); 
    }
	
	/**
	 * Devuelve una colección iterable de las posiciones de los nodos del árbol.
	 * @return Colección iterable de las posiciones de los nodos del árbol.
	 */
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> l = new ListaDE<Position<E>>();
// Si el árbol no está vacío, hago un recorrido preorden desde la raíz:
		if( !isEmpty() )
		pre(root, l);
		return l;
		}
// Tpositions(n) → O(n)
	private void pre(Tnodo<E> v, PositionList<Position<E>> l) {
	l.addLast( v ); // La visita de v consiste de “encolar” v en l
	for( Tnodo<E> h : v.getHijos()) // para cada hijo h de v hacer
	pre( h, l ); // preorden de h
}

// Este método deberá eliminar del árbol receptor del mensaje a la posición p siempre que p sea el último hijo
// (de izq a der) de su padre. La raíz no se considera último hijo, en este caso el método deberá lanzar
// InvalidOperationException. Si la posición p es inválida el método deberá lanzar
// InvalidPositionException.

	public void eliminarUltimoHijo(Position<E> p)throws InvalidPositionException,InvalidOperationException{
		Tnodo<E> nodo = checkposition(p);
		if (nodo==root)throw new InvalidOperationException(null);
		Iterator<Position<Tnodo<E>>> ithijospadre = nodo.getParent().getHijos().positions().iterator();
		Position<Tnodo<E>> puntero = null;
		while(ithijospadre.hasNext()){
			puntero = ithijospadre.next();
		}
		if (puntero.element()==nodo){
			for(Position<Tnodo<E>> pos : nodo.getHijos().positions()){
				nodo.getParent().getHijos().addBefore(puntero, pos.element());
			}
			nodo.getParent().getHijos().remove(puntero);
			nodo.setFather(null);
			size--;
		}
	}

// EJ3		Programe un método con la siguiente signatura: public Map<Character, Integer>
// cantidadRepeticiones(Tree<Character> t). Este método deberá retornar un mapeo con cada uno de los
// caracteres del árbol y la cantidad de veces que aparece cada carácter en el árbol. Resuelva este problema utilizando
// un recorrido en preorden.

   
	public Map<Character,Integer> cantidadRepeticiones(Tree<Character> t){
		Map<Character,Integer> toRet = new Mapeo<>();
		preOrder(t.root(),t,toRet);
		return toRet;
	}

	private  void preOrder(Position<Character> nodo,Tree<Character> t,Map<Character,Integer> m){
		Integer val = m.get(nodo.element());
		if(val==null){
			m.put(nodo.element(),1);
		}else{
			m.put(nodo.element(), val + 1);
		}
		for(Position<Character> pos: t.positions()){
			preOrder(pos, t, m);
		}
	}


//EJ5 	Escriba un método tal que dado un árbol genérico a y un elemento e, elimine de a todas las apariciones de e.
// Compare los elementos por equivalencia. El método debe retornar la cantidad de eliminaciones realizadas
	public int eliminarapariciones(E elem,Tree<E> arb){
		int toRet=0;
		for(Position<E> pos : arb.positions()){
			if (pos.element().equals(elem)) {
				arb.removeNode(pos);
				toRet++;
			}
		}
	return toRet;
	}

// EJ4		Dado un árbol a de Strings y un String s, programe un método tal que retorne un Iterable con las
// posiciones del árbol en las que aparece el String s. Para resolver este problema implemente un recorrido en
// postorden

	public Iterable<Position<String>> posArbol(String s, Tree<String> a){
		PositionList<Position<String>> toRet = new ListaDE<>();
		PosOrden(a.root(),s,a,toRet);
		return toRet;
	}

	private void PosOrden(Position<String> nodo,String s, Tree<String> a, PositionList<Position<String>> toRet){
		for(Position<String> pos : a.children(nodo)){
			PosOrden(pos,s,a,toRet);
		}
		if(nodo.element().equals(s))toRet.addLast(nodo);
	}


	public boolean pertenece(Arbol<Integer> a, Integer n){
		boolean toRet = false;
		Iterator<Integer> it = a.iterator();
		while(it.hasNext()&&!toRet){
			Integer i = it.next();
			toRet= i.equals(n);
		}
		return toRet;
	}

// 	Suponiendo que cuenta con una clase Arbol<E> que implementa la interfaz Tree<E>
// vista en clase. Esta clase utiliza la implementación de lista de hijos y enlace al
// padre. Agregue un método a esta clase cuya signatura sea public int
// sizeSubarbol(Position<E> p). Este método deberá retornar el tamaño del subárbol
// con raíz p del árbol receptor del mensaje. Si utiliza otros métodos del TDA Arbol
// deberá implementarlos. Se debe lanzar InvalidPositionException cuando
// corresponda
	public int sizeSubarbol(Position<E> p){
		checkposition(p); // Validar la posición
		return contarSubarbol(p);
	}
	
	private int contarSubarbol(Position<E> p){
		int count = 1; // Contar el nodo actual
		for(Position<E> hijo : children(p)){
			count += contarSubarbol(hijo);
		}
		return count;
	}

// 	Suponiendo que cuenta con una clase Arbol<E> que implementa la interfaz Tree<E>
// vista en clase. Esta clase utiliza la implementación de lista de hijos y enlace al
// padre. Agregue un método a esta clase cuya signatura sea public
// Map<Position<E>, Integer> mapSizeSubarboles( ). Este método deberá retornar
// un mapeo entre posiciones del árbol y el tamaño del subárbol que tiene a esa
// posición como raíz. Si utiliza otros métodos del TDA Arbol deberá implementarlos.
// Se debe lanzar InvalidPositionException cuando corresponda.

	public Map<Position<E>, Integer> mapSizeSubarboles(){
		Map<Position<E>,Integer> toRet = new Mapeo<>();
		visita(toRet,this.root);	
		return toRet;
	}

	private void visita(Map<Position<E>,Integer> toRet, Position<E> nodo){

		for(Position<E> pos : children(nodo)){
			visita(toRet,pos);
		}
		toRet.put(nodo,this.sizeSubarbol(nodo));
	}

// 	Problema 3
// Suponiendo que cuenta con una clase Arbol<E> que implementa la interfaz Tree<E>
// vista en clase. Esta clase utiliza la implementación de lista de hijos y enlace al
// padre. Agregue un método a esta clase cuya signatura sea public int
// podarSubarbol(Position<E> p). Este método deberá eliminar el subarbol con raíz
// p del árbol receptor del mensaje. Retorna el tamaño del subárbol eliminado. Si
// utiliza otros métodos del TDA Arbol deberá implementarlos. Se debe lanzar
// InvalidPositionException cuando corresponda.

public int podarSubarbol(Position<E> p)throws InvalidPositionException{
	Tnodo<E> nodo = checkposition(p);
	int toRet = sizeSubarbol(p);
	
	Iterator<Position<Tnodo<E>>> it = nodo.getParent().getHijos().positions().iterator();
	Position<Tnodo<E>> puntero = null;
	boolean encontre = false;
	while(it.hasNext()&&!encontre){
		puntero = it.next();
		encontre=puntero.element()==nodo;
	}
	nodo.getParent().getHijos().remove(puntero);
	size=size - toRet;

	return toRet;
}


}

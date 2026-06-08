package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;
import java.util.Dictionary;
import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import ar.edu.uns.cs.ed.tdas.tdamapeo.*;
import ar.edu.uns.cs.ed.tdas.tdadiccionario.*;
import ar.edu.uns.cs.ed.tdas.*;
public class ArbolBinario<E> implements BinaryTree<E>{
    protected BTnodo<E> root;
    protected int size;

    public ArbolBinario() {
        this.root = null;
        this.size = 0;
    }

    protected BTnodo<E> checkPosition(Position<E> v) throws InvalidPositionException {
    if(size==0||v==null)throw new InvalidPositionException("Posición nula o árbol vacío");
        try {
            BTnodo<E> nodo = (BTnodo<E>) v;
            return nodo;
        } catch (ClassCastException e) {
            throw new InvalidPositionException("La posición no es de tipo BTnodo");
        }
    }
	/**
	 * Devuelve la posición del hijo izquierdo de v.
	 * @param v Posición de un nodo.
	 * @return Posición del hijo izquierdo de v.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si v no tiene hijo izquierdo.
	 */
	public Position<E> left(Position<E> v){
        BTnodo<E> nodo = checkPosition(v);
        if (nodo.getLeft() == null) throw new BoundaryViolationException("No tiene hijo izquierdo");
        return nodo.getLeft();
    }
	
	/**
	 * Devuelve la posición del hijo derecho de v.
	 * @param v Posición de un nodo.
	 * @return Posición del hijo derecho de v.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si v no tiene hijo derecho.
	 */
	public Position<E> right(Position<E> v){
         BTnodo<E> nodo = checkPosition(v);
        if (nodo.getRight() == null) throw new BoundaryViolationException("No tiene hijo derecho");
        return nodo.getRight();
    }
	

	/**
	 * Testea si v tiene un hijo izquierdo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si v tiene un hijo izquierdo y falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.	
	 */
	public boolean hasLeft(Position<E> v){
        BTnodo<E> nodo = checkPosition(v);
        return nodo.getLeft() != null;
    }
	
	
	/**
	 * Testea si v tiene un hijo derecho.
	 * @param v Posición de un nodo.
	 * @return Verdadero si v tiene un hijo derecho y falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.	
	 */
	public boolean hasRight(Position<E> v){
        BTnodo<E> nodo = checkPosition(v);
        return nodo.getRight() != null;
    }

	/**
	 * Agrega un nodo con rótulo r como hijo izquierdo de un nodo dado.
	 * @param r Rótulo del nuevo nodo.
	 * @param v Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 * @throws InvalidOperationException si v ya tiene un hijo izquierdo.
	 */
	public Position<E> addLeft(Position<E> v, E r){
        BTnodo<E> nodo = checkPosition(v);
        if (nodo.getLeft() != null) throw new InvalidOperationException("Ya tiene hijo izquierdo");
        BTnodo<E> nuevo = new BTnodo<>(r, nodo);
        nodo.setLeft(nuevo);
        size++;
        return nuevo;
    }


	/**
	 * Agrega un nodo con rótulo r como hijo derecho de un nodo dado.
	 * @param r Rótulo del nuevo nodo.
	 * @param v Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 * @throws InvalidOperationException si v ya tiene un hijo derecho.
	 */
	public Position<E> addRight(Position<E> v, E r){
        BTnodo<E> nodo = checkPosition(v);
        if (nodo.getRight() != null) throw new InvalidOperationException("Ya tiene hijo derecho");
        BTnodo<E> nuevo = new BTnodo<>(r, nodo);
        nodo.setRight(nuevo);
        size++;
        return nuevo;
    }

	/**
	 * Inserta a los árboles T1 y T2 como subárboles hijos de la hoja v (izquierdo y derecho respectivamente).
	 * @param v Posición de una hoja del árbol.
	 * @param T1 Árbol binario a insertar como hijo izquierdo de v.
	 * @param T2 Árbol binario a insertar como hijo derecho de v. 
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío, o v no corresponde a una hoja.
	 */
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2){
        BTnodo<E> nodo = checkPosition(r);
        if (hasLeft(nodo) || hasRight(nodo)) throw new InvalidPositionException("No es una hoja");
        if (!T1.isEmpty()) {
            BTnodo<E> leftChild = (BTnodo<E>) T1.root();
            nodo.setLeft(leftChild);
            leftChild.setFather(nodo);
        }
        if (!T2.isEmpty()) {
            BTnodo<E> rightChild = (BTnodo<E>) T2.root();
            nodo.setRight(rightChild);
            rightChild.setFather(nodo);
        }
        size += T1.size() + T2.size();
    }

    @Override
    public int size() {
            return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public Iterable<Position<E>> positions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'positions'");
    }

    @Override
    public E replace(Position<E> v, E e) {
            BTnodo<E> nodo = checkPosition(v);
            E old = nodo.element();
            nodo.setElement(e);
            return old;
        // TODO Auto-generated method stub
        }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        BTnodo<E> nodo = checkPosition(v);
        if (nodo == root) throw new BoundaryViolationException("La raíz no tiene padre");
        return nodo.getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) {
        BTnodo<E> nodo = checkPosition(v);
        PositionList<Position<E>> hijos = new ListaDE<>();
        if (nodo.getLeft() != null) hijos.addLast(nodo.getLeft());
        if (nodo.getRight() != null) hijos.addLast(nodo.getRight());
        return hijos;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        BTnodo<E> nodo = checkPosition(v);
        return nodo.getLeft() != null || nodo.getRight() != null;
    }

    @Override
    public boolean isExternal(Position<E> v) {
        BTnodo<E> nodo = checkPosition(v);
        return nodo.getLeft() == null && nodo.getRight() == null;
    }

    @Override
    public boolean isRoot(Position<E> v) {
        BTnodo<E> nodo = checkPosition(v);
        return nodo == root;
    }

    @Override
    public void createRoot(E e) {
        if (!isEmpty()) throw new InvalidPositionException("El árbol ya tiene una raíz");
        root = new BTnodo<E>(e, null, null, null);
        size = 1;
    }

    @Override
    public Position<E> addFirstChild(Position<E> p, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFirstChild'");    
    }

    @Override
    public Position<E> addLastChild(Position<E> p, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLastChild'");
    }

    @Override
    public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBefore'");
    }

    @Override
    public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAfter'");
    }

    @Override
    public void removeExternalNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeExternalNode'");
    }

    @Override
    public void removeInternalNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeInternalNode'");
    }

    @Override
    public void removeNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeNode'");
    }

// Agregue un método a la clase árbol binario programada en el inciso anterior tal que recorra el árbol en pre-orden y
// retorne un diccionario donde sus entradas tengan como clave al rótulo del padre y como valor a los rótulos de cada
// uno de sus hijos. Los rótulos ubicados en hojas del árbol no deben pertenecer al diccionario, de esta forma en el
// diccionario no pueden existir valores nulos
    public <K,V> Dictionary<K,V> preOrdenDiccionario(Position<E> v) {
        BTnodo<E> nodo = checkPosition(v);
        //hace
        visitar(BTnodo<E> nodo,Dictionary<K,V> dic);

    }

    private <K,V> void visitarPre(BTnodo<E> nodo,Dictionary<K,V> dic){
        
    }
}

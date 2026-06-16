package ar.edu.uns.cs.ed.tdas.tdapila;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.tdacola.*;
public class PilaEnlazada<E> implements Stack<E> {
    protected Nodo<E> head;
    protected int cant;

    public PilaEnlazada() {
        head = null;
        cant = 0;
    }
	
    public PilaEnlazada(E element) {
        head = new Nodo<>(element, null);
        cant = 1;
    }
    /**
	 * Consulta la cantidad de elementos de la pila.
	 * @return Cantidad de elementos de la pila.
	 */
	public int size(){
        return cant;
    }
	/**
	 * Consulta si la pila está vacía.
	 * @return Verdadero si la pila está vacía, falso en caso contrario.
	 */
	public boolean isEmpty(){
        return cant==0;
    }

	/**
	 * Examina el elemento que se encuentra en el tope de la pila.
	 * @return Elemento que se encuentra en el tope de la pila.
	 * @throws EmptyStackException si la pila está vacía. 
	 */
	public E top() throws EmptyStackException{
        if (cant==0)throw new EmptyStackException( "Pila Vacia");
        return head.element();
    }

	/**
	 * Inserta un elemento en el tope de la pila.
	 * @param element Elemento a insertar.
	 */
	public void push(E element){
        Nodo<E> aux = new Nodo<E>(element, head);
        head = aux;
        cant++;
    }

	/**
	 * Remueve el elemento que se encuentra en el tope de la pila.
	 * @return Elemento removido.
	 * @throws EmptyStackException si la pila está vacía. 
	 */
	public E pop() throws EmptyStackException{
        if (cant==0)throw new EmptyStackException("Pila Vacia");
        E aux = head.element();
        head= head.getNext();
        cant--;
        return aux;


    }

    public void eliminarNegativos(Stack<Integer> pila){
        Queue<Integer> aux = new ColaEnlazada<>();
        while(!pila.isEmpty()){
            Integer in = pila.pop();
            
        }
    }
    public Queue<Integer> invertirCola(Queue<Integer> cola){
        Stack<Integer> aux = new PilaEnlazada<>();
        Queue<Integer> toRet=new ColaEnlazada<>();
        Queue<Integer> colaux = new ColaEnlazada<>();
        Integer i;
        while(!cola.isEmpty()){
            i=cola.dequeue();
            aux.push(i);
            colaux.enqueue(i);
        }

        while(!aux.isEmpty()){
            toRet.enqueue(aux.pop());
            cola.enqueue(colaux.dequeue());
        }

        return toRet;


            
    }
    
}

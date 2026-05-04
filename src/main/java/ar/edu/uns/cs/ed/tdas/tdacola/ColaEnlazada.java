package ar.edu.uns.cs.ed.tdas.tdacola;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class ColaEnlazada<E> implements Queue<E> {
    private Nodo<E> head;
    private Nodo<E> tail;
    private int cant;

    public ColaEnlazada() {
        head = null;
        tail = null;
        cant = 0;
    }
	/**
	 * Devuelve la cantidad de elementos en la cola.
	 * @return Cantidad de elementos en la cola.
	 */
	public int size(){
        return cant;
    }
	
	/**
	 * Consulta si la cola está vacía.
	 * @return Verdadero si la cola está vacía, falso en caso contrario.
	 */
	public boolean isEmpty(){
        return cant == 0;
    }
	
	/**
	 * Inspecciona el elemento que se encuentra en el frente de la cola.
	 * @return Elemento que se encuentra en el frente de la cola.
	 * @throws EmptyQueueException si la cola está vacía.
	 */
	public E front(){
        if (isEmpty()) {
            throw new EmptyQueueException("La cola está vacía");
        }
        return head.element();
    }
	
	/**
	 * Inserta un elemento en el fondo de la cola.
	 * @param element Nuevo elemento a insertar.
	 */
	public void enqueue(E element){
        
        Nodo<E> nodo = new Nodo<>(element, null);
        if(head==null){
            head = nodo;
            cant++;
            tail = nodo;
        } else{
            tail.setNext(nodo);
            tail = nodo;
            cant++;
        }
        
    }

	/**
	 * Remueve el elemento en el frente de la cola.
	 * @return Elemento removido.
	 * @throws EmptyQueueException si la cola está vacía.
	 */
	public E dequeue() throws EmptyQueueException{
        if(cant==0) throw new EmptyQueueException("La cola está vacía");
        E aux = head.element();
        if(cant==1){
            head=null;
            tail=null;
            cant--;
        } else{
        head = head.getNext();
        cant--;
        
        }return aux;


    }

    
}

package ar.edu.uns.cs.ed.tdas.tdacola;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class ColaconArreglo<E> implements Queue<E>{
    private E[] arreglo;
    private int head;
    private int tail;
    public ColaconArreglo(int capacidad) {
        arreglo = (E[]) new Object[capacidad];
        head = 0;
        tail = 0;
    }   
    public ColaconArreglo() {
        this(20); // Capacidad inicial por defecto
    }

    /**
	 * Devuelve la cantidad de elementos en la ColaconArreglo.
	 * @return Cantidad de elementos en la ColaconArreglo.
	 */
	public int size(){
	return ((arreglo.length- head + tail) % arreglo.length);
    }   

    
	/**
	 * Consulta si la ColaconArreglo está vacía.
	 * @return Verdadero si la ColaconArreglo está vacía, falso en caso contrario.
	 */
	public boolean isEmpty(){
    return head == tail;
    }
	
	/**
	 * Inspecciona el elemento que se encuentra en el frente de la ColaconArreglo.
	 * @return Elemento que se encuentra en el frente de la ColaconArreglo.
	 * @throws EmptyQueueException si la ColaconArreglo está vacía.
	 */
	public E front()throws EmptyQueueException{
        if(isEmpty()){
        throw new EmptyQueueException("La cola está vacía");
        }
        return arreglo[head];
    }
	
	/**
	 * Inserta un elemento en el fondo de la cola.
	 * @param element Nuevo elemento a insertar.
	 */
	public void enqueue(E element){
        arreglo[tail] = element;
        tail = (tail + 1) % arreglo.length;
        if(tail == head){
            // La cola está llena, se necesita redimensionar
            E[] nuevoArreglo = (E[]) new Object[arreglo.length * 2];
            for(int i = 0; i < arreglo.length; i++){
                nuevoArreglo[i] = arreglo[(head + i) % arreglo.length];
            }
            arreglo = nuevoArreglo;
            head = 0;
            tail = arreglo.length / 2;
        }
    }

	/**
	 * Remueve el elemento en el frente de la cola.
	 * @return Elemento removido.
	 * @throws EmptyQueueException si la cola está vacía.
	 */
	public E dequeue()throws EmptyQueueException{
        if(isEmpty()){
        throw new EmptyQueueException("La cola está vacía");
        }
        E aux = arreglo[head];
        arreglo[head] = null; // Ayuda al recolector de basura
        head = (head + 1) % arreglo.length;
        return aux;
    }
    
}

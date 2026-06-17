package ar.edu.uns.cs.ed.tdas.tdacola;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.tdamapeo.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.tdas.tdapila.*;

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

    public void eliminarDuplicados(Queue<Integer> cola){
        if(cola.isEmpty())return;
        Map<Integer,Integer> mapeo = new MapeoConHashAbierto<>();
        Integer i;
        Queue<Integer> aux =new ColaEnlazada<>(); 
        while(!cola.isEmpty()){
            i=cola.dequeue();
            if(mapeo.get(i)==null){
                mapeo.put(i, i);
                aux.enqueue(i);
            }
        }
        while(!aux.isEmpty()){
            cola.enqueue(aux.dequeue());
        }
    }
        
    
    public static boolean esPalindromo(Queue<Character> q)throws EmptyQueueException{
        if(q.isEmpty())throw new EmptyQueueException("");
        boolean toRet=true;
        Stack<Character> pila = new PilaEnlazada<>();
        Queue<Character> aux = new ColaEnlazada<>();
        Character c = null;
        while(!q.isEmpty()){
            c = q.dequeue();
            if(pila.top().equals(c)){
                pila.pop();
            }else{
            pila.push(c);
            }
            aux.enqueue(c);
        }
        toRet=pila.isEmpty();
         while(!aux.isEmpty()){
            q.enqueue(aux.dequeue());
         }
         return toRet;
    }   
}


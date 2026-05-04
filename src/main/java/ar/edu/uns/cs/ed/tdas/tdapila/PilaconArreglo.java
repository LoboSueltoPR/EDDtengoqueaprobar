package ar.edu.uns.cs.ed.tdas.tdapila;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
public class PilaconArreglo<E> implements Stack<E>{
    E[] arreglo;
    int cant;
    public PilaconArreglo(int capacidad){
        this.arreglo=(E[]) new Object[capacidad];
        this.cant=0;
    }

    public PilaconArreglo(){
        this(20);
    }
    @Override
    public int size() {
        return cant;
    }

    @Override
    public boolean isEmpty() {
        return cant==0;
    }

    @Override
    public E top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("La pila está vacía.");
        }
        return arreglo[cant-1];
    }

    @Override
    public void push(E element) {
        if(cant==arreglo.length){
            E[] nuevoArreglo=(E[]) new Object[arreglo.length*2];
            for (int i = 0; i < arreglo.length; i++) {
                nuevoArreglo[i]=arreglo[i];
            }
            arreglo=nuevoArreglo;
        }
        arreglo[cant++]=element;
    }


    public Stack<E> intercalar(Stack<E> pila1, Stack<E> pila2){
        Stack<E> aux=new PilaconArreglo<>();
        while(!pila1.isEmpty() || !pila2.isEmpty()){
            if(!pila1.isEmpty()){
                aux.push(pila1.pop());
            }
            if(!pila2.isEmpty()){
                aux.push(pila2.pop());
            }
        }
        return aux;
    }

    

    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("La pila está vacía.");
        }
        cant--;
        E aux=arreglo[cant];
        arreglo[cant]=null;
        return aux;
    }  
    
    
    public static <E> Stack<E> InvertirPila(Stack<E> pila){
        Stack<E> aux = new PilaconArreglo<>();
        while (!pila.isEmpty()) {
            E elem=pila.pop();
            aux.push(elem);
        }
        return aux;
    }
    
    public  void InvertirPilaSinPerderContenido(Stack<E> pila){
        Stack<E> aux= new PilaconArreglo<>();
        Stack<E> aux2= new PilaconArreglo<>();
        while(!pila.isEmpty()){
            aux.push(pila.pop());
        }
        while(!aux.isEmpty()){
            aux2.push(pila.pop());
        }
        
        while(!aux2.isEmpty()){
            pila.push(pila.pop());
        }
    } 
}

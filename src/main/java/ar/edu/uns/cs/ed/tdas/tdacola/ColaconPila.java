package ar.edu.uns.cs.ed.tdas.tdacola;

import java.util.Stack;

public class ColaconPila<E> implements Queue<E>{

    private Stack<E> stackEntrada;
    private Stack<E> stackSalida;

    public ColaconPila(){
        stackEntrada = new Stack<>();
        stackSalida = new Stack<>();
    }

    private void moverElementos(){
        if(stackSalida.isEmpty()){
            while(!stackEntrada.isEmpty()){
                stackSalida.push(stackEntrada.pop());
            }
        }
    }

    @Override
    public int size() {
        return stackEntrada.size() + stackSalida.size();
    }

    @Override
    public boolean isEmpty() {
        return stackEntrada.isEmpty() && stackSalida.isEmpty();
    }

    @Override
    public E front() {
        if(isEmpty())
            return null;

        moverElementos();
        return stackSalida.peek();
    }

    @Override
    public void enqueue(E element) {
        stackEntrada.push(element);
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            return null;

        moverElementos();
        return stackSalida.pop();
    }

}
package ar.edu.uns.cs.ed.tdas.conjunto;

import ar.edu.uns.cs.ed.tdas.tdaarbol.conjunto;

public class   ConjuntoArreglo<E>   implements conjunto<E> {

    private E[] arreglo;
    private int cantidad;

    public ConjuntoArreglo(int capacidad) {
        this.arreglo = (E[]) new Object[capacidad];
        this.cantidad = 0;
    }
    @Override
    public int size() {
        return this.cantidad;
    }

    @Override
    public int capacity() {
        return this.arreglo.length;
    }

    @Override
    public boolean isEmpty() {
        return this.cantidad==0;
    }

    @Override
    public E get(int i) {
        return this.arreglo[i];
    }

    @Override
    public void put(E elem) {
       arreglo[cantidad++]=elem;
     }

    @Override
    public boolean pertenece(E elem) {
        for (int i = 0; i < cantidad; i++) {
            if (arreglo[i].equals(elem)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean perteneceRecursivo(E elem,int i){
        if(i>=cantidad) return false;
        if(arreglo[i].equals(elem)) return true;
        return perteneceRecursivo(elem,i+1);
    }


    @Override
    public conjunto<E> interseccion(conjunto<E> c) {
        conjunto<E> aux=new ConjuntoArreglo<E>(cantidad);
        for(int i=0;i<this.cantidad;i++){
            if(c.pertenece(arreglo[i]))aux.put(arreglo[i]);
        }
        return aux;
    }        

}

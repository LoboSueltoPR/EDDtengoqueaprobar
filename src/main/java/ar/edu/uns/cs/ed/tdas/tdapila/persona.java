package ar.edu.uns.cs.ed.tdas.tdapila;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.tdapila.*;
public class persona {
    String nombre;
    int edad;

    public persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "persona{" + "nombre=" + nombre + ", edad=" + edad + '}';
    }

    public void invertir(persona[] arreglo) {
        Stack<persona> aux=new PilaconArreglo<>();
        for(int i=0;i<arreglo.length;i++){
            aux.push(arreglo[i]);
        }
        for(int i=0;i<arreglo.length;i++){
            arreglo[i]=aux.pop();
        }
    }
    
    
}

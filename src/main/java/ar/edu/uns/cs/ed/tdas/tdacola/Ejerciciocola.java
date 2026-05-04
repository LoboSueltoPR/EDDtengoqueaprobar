package ar.edu.uns.cs.ed.tdas.tdacola;
import ar.edu.uns.cs.ed.tdas.tdapila.*;
public class Ejerciciocola {

    public Queue<Integer> ColaNumerosEnteros(Queue<Integer> ColaEntera){
        Queue<Integer> ColaAuxiliar = new ColaconArreglo<>(ColaEntera.size());
        while(!ColaEntera.isEmpty()){
            int numero = ColaEntera.front();
            ColaEntera.dequeue();
            if(numero % 2 == 0){
                ColaAuxiliar.enqueue(numero);
            }
        }
        return ColaAuxiliar;    
    }

    public static  int AparicionesC(Queue<Stack<Character>> ColadePilas, Character C ){
        int aux=0;
        while (!ColadePilas.isEmpty()) {
            Stack<Character> pila = ColadePilas.dequeue();
            while (!pila.isEmpty()) {
                Character cha = pila.pop();
                if(cha.equals(C))aux++;
            }
            
        }
        return aux;
    }

    public static boolean  ApareceC(Queue<Stack<Character>> ColadePilas, Character C ){
        boolean encontre=false;
            while (!ColadePilas.isEmpty() && !encontre  ) {
                Stack<Character> pila = ColadePilas.dequeue();
                while (!pila.isEmpty() && !encontre) {
                    Character cha = pila.pop();
                    encontre=cha.equals(C);
                }
            }
        return encontre;
    }

    


}

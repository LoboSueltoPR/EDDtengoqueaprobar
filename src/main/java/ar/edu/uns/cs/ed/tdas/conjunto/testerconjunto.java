package ar.edu.uns.cs.ed.tdas.conjunto;

public class testerconjunto {
    public static void main(String[] args) {
        conjunto<Integer> c1=new ConjuntoArreglo<Integer>(10);
        conjunto<Integer> c2=new ConjuntoArreglo<Integer>(10);
        c1.put(1);
        c1.put(2);
        c1.put(3);
        c1.put(4);
        c1.put(5);
        c2.put(3);
        c2.put(4);
        c2.put(5);
        c2.put(6);
        c2.put(7);
        
        conjunto<Integer> interseccion=c1.interseccion(c2);
        
        for(int i=0;i<interseccion.size();i++){
            System.out.println(interseccion.get(i));
        }
    }

}

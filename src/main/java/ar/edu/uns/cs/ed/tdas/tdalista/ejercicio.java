package ar.edu.uns.cs.ed.tdas.tdalista;
import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.*; 

public class ejercicio {
    
    
	// public PositionList<Integer> intercalarl1yl2(PositionList<Integer> l1,PositionList<Integer> l2){
    //     PositionList<Integer> toReturn = new ListaDE<Integer>();
        
    //         Position<Integer> p1 = l1.first();
    //         Position<Integer> p2 = l2.first();
    //         while(p1!=null && p2!=null){
    //             toReturn.addLast(p1.element());
    //             toReturn.addLast(p2.element());
                
    //             try {
    //                 if(p1!=null) {
    //                     p1 = l1.next(p1);
    //                 }
    //                 if(p2!=null) {
    //                     p2 = l2.next(p2);
    //                 }
    //             }  catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
    //         }


    // }

    public <E> int CantEenL(PositionList<E> l,E e){
        int toRet = 0;
        for(E elem:l){
            if(elem.equals(e)) toRet++;
        }
        return toRet;
    }

    public <E> boolean estaEenL(PositionList<E> l,E e){
        boolean toRet = false;
        for(E elem:l){
            if(elem.equals(e)){
                toRet=true;
                break;
            };
        }
        return toRet;
    }

    public <E> boolean dosListIguales(PositionList<E> l1,PositionList<E> l2){
        boolean toRet=false;
        if(l1.size()!=l2.size()){
            toRet = false;
        }else{
            if(!(l1.isEmpty()&&l2.isEmpty())){
                Iterator<E> it1 = l1.iterator();
                Iterator<E> it2 = l2.iterator();
                E elem1;
                E elem2;
                while(it1.hasNext()&& toRet){
                    elem1 = it1.next();
                    elem2 = it2.next();
                    if(! elem1.equals(elem2))toRet=false;
                }
            }
        }
    return toRet;
    }

    // public void atp(E elem){
	// 	Iterator<Position<E>> it = this.positions().iterator();
	// 	if(isEmpty())return;
	// 	while(it.hasNext()){
	// 		Position<E> e=it.next();
	// 		if(e.element()!=elem)addAfter(e, elem);
	// 	}
public <E> Iterable<E> eliminarComunes(PositionList<E> l1, PositionList<E> l2) {
		PositionList<E> eliminados = new ListaDE<>();
		PositionList<E> posicionesARemover = new ListaDE<>();
		
		// Collect all positions from l2 that have elements in l1
		for (Position<E> pos : l2.positions()) {
			if (e1estaenlista(pos.element(), l1)) {
				posicionesARemover.addLast(pos.element());
			}
		}
		for (Position<E> pos : posicionesARemover.positions()) {
			eliminados.addLast(l2.remove(pos));
		}
		
		return eliminados;
	}
    public <E> boolean e1estaenlista (E e1, PositionList<E> lista){
		boolean toRet = false;
		Iterator<E> it = lista.iterator();
		while (it.hasNext()&&!toRet) {
			E elemento = it.next();
			toRet = elemento.equals(e1);
		}
		return toRet;
	}
//los pares van en el orden q entrar, los impares van al revez
    public statis Queue<Integer> FiltraryReordenar(PositionList<Integer> lista){ 
        Queue<Integer> toRet = new LinkedQueue<>();
        Stack<Integer> impares = new LinkedStack<>();
        for(Integer elem:lista){
            if(elem%2==0){
                toRet.enqueue(elem);
            }else{
                impares.push(elem);
            }
        }
        while(!impares.isEmpty()){
            toRet.enqueue(impares.pop());
        }
        return toRet;


        
    }

    public boolean Submulticonjunto (PositionList<Character> l1, PositionList<Character> l2){
        boolean toRet = true;
        for(Character elem:l1){
            if(!e1estaenlista(elem,l2)){
                toRet=false;
                break;
            }
            
        }
        return toRet;
    }

    
}



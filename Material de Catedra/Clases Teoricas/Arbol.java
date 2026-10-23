package ar.edu.uns.cs.ed.tdas.tdaarbol;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.tdamapeo.MapeoConHash;

public class Arbol<E> implements Tree<E>{
	protected TNodo<E> root;
	protected int size;
	
	//Problema 1
	public int sizeSubarbol(Position<E> p) {
		TNodo<E> nodo = this.checkPosition(p);
		
		return sizeSubArbolCont(nodo);
		
		
	}
	
	private int sizeSubArbolCont(TNodo<E> nodo) {
		int resultado = 1;
		for (TNodo<E> hijo : nodo.getChildren())
				resultado = resultado + sizeSubArbolCont(hijo);
		return resultado;
	}
	
	//Problema 2
	public Map<Position<E>, Integer> mapSizeSubArboles() {
		
		TNodo<E> nodo = root; //no uso checkPosition porque si es null no quiero excepción
		Map<Position<E>, Integer> m = new MapeoConHash<Position<E>, Integer>();
		if (root != null)
			mapSizeSubArbolesCont(nodo, m);
		return m;
	}
	
	private int mapSizeSubArbolesCont(TNodo<E> nodo, Map<Position<E>, Integer> m){
		int resultado = 1;
		for (TNodo<E> hijo : nodo.getChildren())
				resultado = resultado + sizeSubArbolCont(hijo);
		m.put(nodo,resultado);
		return resultado;
	
	}
	//Problema 3
	public int podarSubarbol(Position<E> p) {
		TNodo<E> nodo = this.checkPosition(p);
		
		int resultado = sizeSubArbolCont(nodo);
		
		if (nodo==root) {
			root = null;
			size = 0;					
			return resultado;
		}
		buscarYEliminar(nodo);
		size = size - resultado;
		return resultado;
	}
	
	private void buscarYEliminar(TNodo<E> nodo) {
		TNodo<E> padre = nodo.getParent();
		ListaDoblementeEnlazada<TNodo<E>> listaHijos = padre.getChildren();
		Iterator<Position<TNodo<E>>> iteradorPosHijos = listaHijos.positions().iterator();
		boolean encontre = false; 
		Position<TNodo<E>> pos = null; // O(1)
		while (iteradorPosHijos.hasNext() && !encontre) { // iteraciones = grado del padre
			pos = iteradorPosHijos.next(); // O(1) // Cond=O(1)
			if ( pos.element() == nodo ) 
				encontre = true; 
		}
		if ( encontre == false ) // Exageracion! // O(1)
			throw new InvalidPositionException("Arbol roto: el padre de p no conoce a p");
		listaHijos.remove(pos);
				
		/*for (Position<TNodo<E>> hijo: listaHijos.positions()) {
			if (hijo.element() == nodo) {
				listaHijos.remove(hijo);
				return;
			}
		}*/
		
	}


	private TNodo<E> checkPosition (Position<E> p) {
		TNodo<E> resultado = null;
		if (p == null) {
			throw new InvalidPositionException("Posición nula.");
		}

		if (this.isEmpty()) {
			throw new InvalidPositionException("Posición inválida");
		}

		try {
			resultado = (TNodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Posición inválida");
		}

		return resultado;
	}

    //Continua implementación TDA arbol
}


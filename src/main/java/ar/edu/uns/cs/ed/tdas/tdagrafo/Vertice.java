package ar.edu.uns.cs.ed.tdas.tdagrafo;
import ar.edu. uns.cs.ed.tdas.*;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
public class Vertice<V,E> implements Vertex<V> {
private V rotulo;
private PositionList<Arco<V,E>> adyacentes;
private Position<Vertice<V,E>> posicionEnNodos;

public Vertice( V rotulo ) {
this.rotulo = rotulo;
adyacentes = new ListaDE<Arco<V,E>>();
}

public V element() { 
    return rotulo; 
}
// Setters y getters

public void setRotulo(V nuevoRotulo) { 
    this.rotulo = nuevoRotulo;
 }
public PositionList<Arco<V,E>> getAdyacentes() { 
    return adyacentes;
}
public void setPosicionEnNodos(Position<Vertice<V,E>> p ) { 
    this.posicionEnNodos = p;
}
public Position<Vertice<V,E>> getPosicionEnNodos()
{ 
    return posicionEnNodos;
}

}

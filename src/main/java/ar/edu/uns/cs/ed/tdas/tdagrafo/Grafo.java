package ar.edu.uns.cs.ed.tdas.tdagrafo;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
// TODO: importar lo que necesites (excepciones, ListaDE, PositionList, Position)

/**
 * Implementación de Graph (grafo no dirigido) mediante lista de adyacencias.
 */
public class Grafo<V, E> implements Graph<V, E> {

    // TODO 0. Campos + constructor
    // - Dos PositionList: una de Vertice<V,E> (todos los vértices del grafo)
    //   y otra de Arco<V,E> (todos los arcos). Usá ListaDE.
    // - Constructor: inicializa esas dos listas vacías.

    private PositionList<Vertice<V,E>> vertices;
    private PositionList<Arco<V,E>> arcos;

    public Grafo() {
        this.vertices = new ListaDE<>();
        this.arcos = new ListaDE<>();
    }

    
    // TODO 1. checkVertex(Vertex<V> v) -> Vertice<V,E>  (privado)
    // - Si v es null -> throw new InvalidVertexException(...)
    // - Si no, castealo a Vertice<V,E> (try/catch ClassCastException -> misma excepción)
    // - Devolvé el objeto ya casteado.
    
    // TODO 1. checkEdge(Edge<E> e) -> Arco<V,E>  (privado)
    // - Mismo patrón que checkVertex pero con InvalidEdgeException y Arco<V,E>.

    private Vertice<V,E> checkVertex(Vertex<V> v){
        if (v == null) {
            throw new InvalidVertexException("El vértice es nulo.");
        }
        try {
            return (Vertice<V,E>) v;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("El vértice no es válido.");
        }
    }
    private Arco<V,E> checkEdge(Edge<E> e){
        if (e == null) {
            throw new InvalidEdgeException("El arco es nulo.");
        }
        try {
            return (Arco<V,E>) e;
        } catch (ClassCastException ex) {
            throw new InvalidEdgeException("El arco no es válido.");
        }
    }

        @Override
        public Iterable<Vertex<V>> vertices() {
            // TODO 4. Armá una PositionList nueva, recorré la lista global de vértices
            // y copiá cada uno con addLast. Devolvé esa lista.
            PositionList<Vertex<V>> listaVertices = new ListaDE<>();
            for (Vertice<V,E> vertice : vertices) {
                listaVertices.addLast(vertice);
            }
            return listaVertices;
        }
    @Override
    public Iterable<Edge<E>> edges() {
        // TODO 4. Igual que vertices() pero con la lista global de arcos.
        PositionList<Edge<E>> toRet = new ListaDE<>();

        for(Arco<V,E> arco: arcos){
            toRet.addLast(arco);
        }

        return toRet;
    }

    @Override
    public Iterable<Edge<E>> incidentEdges(Vertex<V> v) {
        // TODO 4. Validar v con checkVertex. Armá una PositionList nueva,
        // recorré la lista de adyacentes de v y copiá cada arco con addLast.
        Vertice<V,E> ver = checkVertex(v);
        PositionList<Edge<E>> toRet = new ListaDE<>();
        for(Arco<V,E> vert:ver.getAdyacentes()){
            toRet.addLast(vert);
        }
        return toRet;
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        // TODO 5. Validar v y e con checkVertex/checkEdge.
        // Si v == arco.getV1() -> devolver arco.getV2().
        // Si v == arco.getV2() -> devolver arco.getV1().
        // Si no es ninguno de los dos -> throw InvalidEdgeException (no incide en v).
        Vertice<V,E> ver = checkVertex(v);
        Arco<V,E> arc = checkEdge(e);
        if(ver==arc.getV1())return arc.getV2();
        if(ver==arc.getV2())return arc.getV1();
        throw new InvalidEdgeException("No incide en V");
    }

    @Override
    public Vertex<V>[] endvertices(Edge<E> e) {
        // TODO 6. Validar e con checkEdge.
        // Devolver un array { arco.getV1(), arco.getV2() }.
        Arco<V,E> arc = checkEdge(e);
        Vertex<V>[] arr = new Vertice[2];
        arr[0]= arc.getV1();
        arr[1]=arc.getV2();
        return arr;
    }

    @Override
    public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
        // TODO 7. Validar v y w con checkVertex.
        // Recorrer los adyacentes de v y ver si alguno tiene a w como el otro extremo
        // (podés usar opposite, o comparar directo contra v1/v2 del arco).
        Vertice<V,E> ver = checkVertex(v);
        Vertice<V,E> ady = checkVertex(w);
        for(Arco<V,E> arc : ver.getAdyacentes()){
            if(arc.getV1().equals(ver)){
                if(arc.getV2().equals(ady))return true;
            }
            if(arc.getV2().equals(ver)){
                if(arc.getV1().equals(ady))return true;
            }
        }
        return false;
    }

    @Override
    public V replace(Vertex<V> v, V x) {
        // TODO 8. Validar v con checkVertex. Guardar el rótulo viejo (element()),
        // setear el nuevo con setRotulo(x), devolver el viejo.
        throw new UnsupportedOperationException("Unimplemented method 'replace'");
    }

    @Override
    public E replace(Edge<E> e, E x) {
        // TODO 8. Mismo patrón que el replace de arriba pero con checkEdge y el Arco.
        throw new UnsupportedOperationException("Unimplemented method 'replace'");
    }

    @Override
    public Vertex<V> insertVertex(V x) {
        // TODO 2. Crear new Vertice<V,E>(x).
        // Agregarlo al final de la lista global de vértices.
        // Guardar en el vértice la Position que te devuelve ese addLast
        // (setPosicionEnNodos) para poder borrarlo después en O(1).
        // Devolver el vértice.
        throw new UnsupportedOperationException("Unimplemented method 'insertVertex'");
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) {
        // TODO 3. Validar v y w con checkVertex.
        // Crear new Arco<V,E>(e, vv, ww).
        // Agregarlo a la lista global de arcos -> guardar esa posición en el arco
        // (setPosicionEnArcos).
        // Agregarlo a la lista de adyacentes de vv -> guardar esa posición
        // (setPosicionEnIv1).
        // Agregarlo a la lista de adyacentes de ww -> guardar esa posición
        // (setPosicionEnIv2).
        // Devolver el arco. Ojo con no mezclar las 3 posiciones entre sí.
        throw new UnsupportedOperationException("Unimplemented method 'insertEdge'");
    }

    @Override
    public V removeVertex(Vertex<V> v) {
        // TODO 10. Validar v con checkVertex.
        // Antes de tocar nada: copiá los arcos incidentes de v a una lista aparte
        // (si iterás la lista de adyacentes de v mientras removeEdge la va vaciando,
        // se rompe el iterador).
        // Llamar removeEdge sobre cada uno de esos arcos copiados.
        // Recién ahí, sacar v de la lista global de vértices usando su posición
        // guardada (getPosicionEnNodos).
        // Devolver el rótulo viejo.
        throw new UnsupportedOperationException("Unimplemented method 'removeVertex'");
    }

    @Override
    public E removeEdge(Edge<E> e) {
        // TODO 9. Validar e con checkEdge.
        // Usar las 3 posiciones guardadas en el arco (getPosicionEnArcos,
        // getPosicionEnIv1, getPosicionEnIv2) para hacer remove(posicion) en la
        // lista global de arcos y en las listas de adyacentes de v1 y v2.
        // Devolver el rótulo viejo.
        throw new UnsupportedOperationException("Unimplemented method 'removeEdge'");
    }

}

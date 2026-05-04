package ar.edu.uns.cs.ed.tdas.tdamapeo;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.*;
public class MapeoConLista<K,V>  implements Map<K,V>{

    protected PositionList<Entrada<K,V>> S;
    private int size;
    public MapeoConLista(){
        S = new ListaDE<Entrada<K,V>>();
        size=0;
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public V get(K key) throws InvalidKeyException{
        if( key == null ) throw new InvalidKeyException("Clave nula");
        // Para cada posición p de la lista S hacer:
        for( Position<Entrada<K,V>> p : S.positions() ) {
            // Si la clave de la entrada en la posición p es key:
            if( p.element().getKey().equals( key ) ) {
                // Retornar el valor de la entrada en la posición p
                return p.element().getValue();
            }
        }
        return null; // Si salí del for-each entonces no encontré una entrada con clave key
    }

    public V put( K key, V value ) {
    if( key == null ) throw new InvalidKeyException("Clave nula");
    // Para cada posición p de la lista S hacer:
    for( Position<Entrada<K,V>> p : S.positions() ) {
    // Si la clave de la entrada en la posición p es key:
    if( p.element().getKey().equals( key ) ) {
    // Salvar el valor de la entrada en aux
    V aux = p.element().getValue();
    // Setear el nuevo valor de la entrada a value
    p.element().setValue( value );
    // Retornar el viejo valor
    return aux;
    }
    } // Si salí del for-each entonces no encontré una entrada con clave key
    S.addLast(new Entrada<K,V>(key, value) ); // Inserto una nueva entrada (key,value)
    size++;
    return null; // Retorno null para indicar que inserté una nueva entrada
    }

    public V remove(K key) throws InvalidKeyException{
        if( key == null ) throw new InvalidKeyException("Clave nula");
        // Para cada posición p de la lista S hacer:
        for( Position<Entrada<K,V>> p : S.positions() ) {
            // Si la clave de la entrada en la posición p es key:
            if( p.element().getKey().equals( key ) ) {
                // Salvar el valor de la entrada en aux
                V aux = p.element().getValue();
                // Eliminar la entrada en la posición p
                S.remove(p);
                size--;
                // Retornar el valor de la entrada eliminada
                return aux;
            }
        }
        return null; // Si salí del for-each entonces no encontré una entrada con clave key
    }
    public Iterable<K> keys(){
        PositionList<K> keys = new ListaDE<K>();
        for( Position<Entrada<K,V>> p : S.positions() ) {
            keys.addLast( p.element().getKey() );
        }
        return keys;
    }
    public Iterable<V> values(){
        PositionList<V> values = new ListaDE<V>();
        for( Position<Entrada<K,V>> p : S.positions() ) {
            values.addLast( p.element().getValue() );
        }
        return values;
    }
    public Iterable<Entry<K,V>> entries(){
            PositionList<Entry<K,V>> entries = new ListaDE<Entry<K,V>>();
            for( Position<Entrada<K,V>> p : S.positions() ) {
                entries.addLast( p.element() );
            }
            return entries;
    }
}

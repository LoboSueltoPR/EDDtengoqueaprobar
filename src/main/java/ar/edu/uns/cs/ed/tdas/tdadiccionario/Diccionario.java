package ar.edu.uns.cs.ed.tdas.tdadiccionario;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
public class Diccionario<K, V> implements Dictionary<K, V> {   
    private PositionList<Entry<K, V>> entries;
    private int size;

    public Diccionario() {
        entries = new ListaDE<Entry<K, V>>();
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Entry<K, V> find(K key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("Key is null");
        }
        for (Entry<K, V> entry : entries) {
            if (entry.getKey().equals(key)) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> findAll(K key) {
        return null;
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        return null;
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> e) {
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        return null;
    }
}

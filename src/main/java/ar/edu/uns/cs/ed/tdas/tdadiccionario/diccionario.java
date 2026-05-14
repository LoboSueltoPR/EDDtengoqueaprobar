package ar.edu.uns.cs.ed.tdas.tdadiccionario;

public class Diccionario<K, V> implements Dictionary<K, V> {
    
    
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Entry<K, V> find(K key) {
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

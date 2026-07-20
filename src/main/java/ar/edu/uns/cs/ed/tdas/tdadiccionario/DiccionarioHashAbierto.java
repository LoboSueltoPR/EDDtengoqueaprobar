package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEntryException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Entrada;

public class DiccionarioHashAbierto<K, V> implements Dictionary<K, V> {

    protected static int BUCKETS;
    protected PositionList<Entrada<K, V>>[] array;
    protected int cantElems;

    @SuppressWarnings("unchecked")
    public DiccionarioHashAbierto() {
        BUCKETS = 11;
        array = new PositionList[BUCKETS];
        for (int i = 0; i < BUCKETS; i++) {
            array[i] = new ListaDE<>();
        }
        cantElems = 0;
    }

    public int size() {
        return cantElems;
    }

    public boolean isEmpty() {
        return cantElems == 0;
    }

    public Entry<K, V> find(K key) {
        if (key == null) throw new InvalidKeyException("Clave nula!");
        int bucket = hash(key);
        for (Entry<K, V> e : array[bucket]) {
            if (e.getKey().equals(key)) return e;
        }
        return null;
    }

    public Iterable<Entry<K, V>> findAll(K key) {
        if (key == null) throw new InvalidKeyException("Clave nula!");
        PositionList<Entry<K, V>> toRet = new ListaDE<>();
        int bucket = hash(key);
        for (Entry<K, V> e : array[bucket]) {
            if (e.getKey().equals(key)) toRet.addLast(e);
        }
        return toRet;
    }

    public Entry<K, V> insert(K key, V value) {
        if (key == null) throw new InvalidKeyException("Clave nula!");
        Entrada<K, V> nueva = new Entrada<>(key, value);
        int bucket = hash(key);
        array[bucket].addLast(nueva);
        cantElems++;
        return nueva;
    }

    public Entry<K, V> remove(Entry<K, V> e) {
        if (e == null || e.getKey() == null) throw new InvalidEntryException("Entrada invalida!");
        int bucket = hash(e.getKey());
        for (Position<Entrada<K, V>> pos : array[bucket].positions()) {
            if (pos.element() == e) {   // igualdad de referencia: buscamos el objeto exacto
                try {
                    array[bucket].remove(pos);
                } catch (InvalidPositionException ex) {
                    ex.printStackTrace();
                }
                cantElems--;
                return e;
            }
        }
        throw new InvalidEntryException("La entrada no se encuentra en el diccionario!");
    }

    public Iterable<Entry<K, V>> entries() {
        PositionList<Entry<K, V>> toRet = new ListaDE<>();
        for (int i = 0; i < BUCKETS; i++) {
            for (Entry<K, V> e : array[i]) {
                toRet.addLast(e);
            }
        }
        return toRet;
    }

    protected int hash(K key) {
        return Math.abs(key.hashCode() % BUCKETS);
    }

    public boolean todasConValor(K k, V v) throws InvalidKeyException{
        int llave = hash(k);
        boolean toRet=true;

        for(Entry<K,V> entrada : array[llave]){
            if(entrada.getValue().equals(v)){
                toRet=false;
                break;
            }
        }
        return toRet;
    }

    public boolean iguales(K k1, K k2)throws InvalidKeyException{
        if(k1==null||k2==null)throw new InvalidKeyException("llave invalida");
        int llave1 = hash(k1);
        int llave2 = hash(k2);
        boolean toRet=true;
        boolean encontre = false;
        Iterator<Entrada<K,V>> it1= array[llave1].iterator();
        Iterator<Entrada<K,V>> it2= array[llave1].iterator();
        while(it1.hasNext()&&toRet){
            Entrada<K,V> puntero = it1.next();
            if(puntero.getKey().equals(k1)){
                while(it2.hasNext()&&!encontre){
                    Entrada<K,V> puntero2 = it2.next();
                    if(puntero2.getKey().equals(puntero.getKey())){
                        if(puntero.getValue().equals(puntero2.getValue())){
                            encontre=true;
                            
                        }
                    }
                }
            }
            toRet=encontre;
        }
        return toRet;
    }

    // Método que recibe clave k y entero e, retorna true si hay al menos e entradas con clave k.
    public boolean ej(K key,int e){
        int contador = 0;
        int llave = hash(key);
        Iterator<Entrada<K,V>> it = array[llave].iterator();
        while(it.hasNext()&&contador<e){
            Entrada<K,V> puntero = it.next();
            if(puntero.getKey().equals(key))contador++;
        }
        return contador==e;
    }
    
}

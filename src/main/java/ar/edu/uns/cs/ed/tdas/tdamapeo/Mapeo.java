package ar.edu.uns.cs.ed.tdas.tdamapeo;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.*;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import javafx.util.Pair; 
public class Mapeo<K,V> implements Map<K,V> {
    private Entrada<K,V> [] A;
    private int n; //n es size, la cantidad de entradas en el mapeo
    private static int N=20000; //N es la cantidad de buckets
    public Mapeo() {
        this.n = 0;
        A = (Entrada<K,V> []) new Entrada[N];
    }
    
    
    
    /**
	 * Consulta el número de entradas del mapeo.
	 * @return Número de entradas del mapeo.
	 */
	public int size(){
        return n;
    }
	
	/**
	 * Consulta si el mapeo está vacío.
	 * @return Verdadero si el mapeo está vacío, falso en caso contrario.
	 */
	public boolean isEmpty(){
        return n == 0;
    }
	private int hash(K key) {
        return Math.abs(key.hashCode()) % N;
    }
    private void CheckKey(K key) throws InvalidKeyException {
        if( key == null ) throw new InvalidKeyException("Clave nula");
    }
	/**
	 * Busca una entrada con clave igual a una clave dada y devuelve el valor asociado, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Valor de la entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V get(K key)throws InvalidKeyException{
        CheckKey(key);
        int i = hash(key);
        if(A[i] == null) return null;
        return A[i].getValue();
    }
	
	/**
	 * Si el mapeo no tiene una entrada con clave key, inserta una entrada con clave key y valor value en el mapeo y devuelve null. 
	 * Si el mapeo ya tiene una entrada con clave key, entonces remplaza su valor y retorna el viejo valor.
	 * @param key Clave de la entrada a crear. 
	 * @param value Valor de la entrada a crear. 
	 * @return Valor de la vieja entrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V put(K key, V value)throws InvalidKeyException{
        CheckKey(key);
        int i = hash(key);
        if(A[i] == null) {
            A[i] = new Entrada<K,V>(key, value);
            n++;
            return null;
        } else {
            V toRet = A[i].getValue();
            A[i].setValue(value);
            return toRet;
        }
    }
	
	/**
	 * Remueve la entrada con la clave dada en el mapeo y devuelve su valor, o nulo si no fue encontrada.
	 * @param e Entrada a remover.
	 * @return Valor de la entrada removida.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V remove(K key)throws InvalidKeyException{
        CheckKey(key);
        int i = hash(key);
        if(A[i] == null) return null;
        V toRet = A[i].getValue();
        A[i] = null;
        n--;
        return toRet;
    }
    

	/**
	 * Retorna una colección iterable con todas las claves del mapeo.
	 * @return Colección iterable con todas las claves del mapeo.
	 */
	public Iterable<K> keys(){
        PositionList<K> keys = new ListaDE<K>();
        for(int i = 0; i < A.length; i++) {
            if(A[i] != null) {
                keys.addLast(A[i].getKey());
            }
        }
        return keys;
    }
	
	/**
	 * Retorna una colección iterable con todas los valores del mapeo.
	 * @return Colección iterable con todas los valores del mapeo.
	 */
	public Iterable<V> values(){
        PositionList<V> values = new ListaDE<V>();
        for(int i = 0; i < A.length; i++) {
            if(A[i] != null) {
                values.addLast(A[i].getValue());
            }
        }
        return values;
    }
	
	/**
	 * Retorna una colección iterable con todas las entradas del mapeo.
	 * @return Colección iterable con todas las entradas del mapeo.
	 */
	public Iterable<Entry<K,V>> entries(){
        PositionList<Entry<K,V>> entradas = new ListaDE<Entry<K,V>>();
        for(int i = 0; i < A.length; i++) {
            if(A[i] != null) {
                entradas.addLast(A[i]);
            }
        }
        return entradas;
    }

    public PositionList<Pair<Integer,Integer>> coincidan(Map<Pair<Integer,Integer>> m1,Map<Pair<Integer,Integer>> m2){
            
    }
}

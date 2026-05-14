package ar.edu.uns.cs.ed.tdas.tdamapeo;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.*;
public class MapeoConHashAbierto<K,V> implements Map<K,V> {
    private PositionList<Entrada<K,V>> [] A;
    private int n; //n es size, la cantidad de entradas en el mapeo
    private static int N=13; //N es la cantidad de buckets, el tamaño del arreglo de buckets
    public MapeoConHashAbierto() {
        this.n = 0;
        A = (PositionList<Entrada<K,V>> []) new ListaDE[N]; // Arreglo de buckets
        for (int i = 0; i < N; i++) {
            A[i] = new ListaDE<Entrada<K,V>>();
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % N;
    }
/**
	 * Busca una entrada con clave igual a una clave dada y devuelve el valor asociado, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Valor de la entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	
    public V get(K key)throws InvalidKeyException{
        if( key == null ) throw new InvalidKeyException("Clave nula");
        return A[hash(key)].get(key);
    }
    
	/**
	 * Si el mapeo no tiene una entrada con clave key, inserta una entrada con clave key y valor value en el mapeo y devuelve null. 
	 * Si el mapeo ya tiene una entrada con clave key, entonces remplaza su valor y retorna el viejo valor.
	 * @param key Clave de la entrada a crear. 
	 * @param value Valor de la entrada a crear. 
	 * @return Valor de la vieja entrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V put(K key, V value) throws InvalidKeyException{
        if( key == null ) throw new InvalidKeyException("Clave nula");
        n++;
        return A[hash(key)].put(key, value);
    }
	
	/**
	 * Remueve la entrada con la clave dada en el mapeo y devuelve su valor, o nulo si no fue encontrada.
	 * @param e Entrada a remover.
	 * @return Valor de la entrada removida.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	public V remove(K key) throws InvalidKeyException{
        if( key == null ) throw new InvalidKeyException("Clave nula");
        if (A[hash(key)].get(key) != null) n--;
        return A[hash(key)].remove(key);
    }
	
	/**
	 * Retorna una colección iterable con todas las claves del mapeo.
	 * @return Colección iterable con todas las claves del mapeo.
	 */
	public Iterable<K> keys(){
        PositionList<K> keys = new ListaDE<K>();
        for (int i = 0; i < N; i++) {
            for (K key : A[i].keys()) {
                keys.addLast(key);
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
        for (int i = 0; i < N; i++) {
            for (V value : A[i].values()) {
                values.addLast(value);
            }
        }
        return values;
    }

	/**
	 * Retorna una colección iterable con todas las entradas del mapeo.
	 * @return Colección iterable con todas las entradas del mapeo.
	 */
	public Iterable<Entry<K,V>> entries(){
        PositionList<Entry<K,V>> entries = new ListaDE<Entry<K,V>>();
        for (int i = 0; i < N; i++) {
            for (Entry<K,V> entry : A[i].entries()) {
                entries.addLast(entry);
            }
        }
        return entries;
    }

}

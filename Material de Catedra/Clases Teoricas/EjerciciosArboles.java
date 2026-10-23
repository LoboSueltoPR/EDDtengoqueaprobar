import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdaarbol.*;

public class EjerciciosArboles {
	
	public static void main(String[] args) {
		//armar casos de prueba
	}
	
	public static <E> void intercambioMasivo(Arbol<E> t, E e, E f){
		if (e!=null) {
			for (Position<E> pos: t.positions()) {
				if (e.equals(pos.element())){
					t.replace(pos, f);
				}
			}
		}
	}
	
	public static <E> void insertarMasivo( E e, int x, Tree<E> A ) {
		insertarCondicional(1, e, x, A, A.root());
	}
	
	public static <E> void insertarCondicional(int nivelActual, E e, int x, Tree<E> A, Position<E> raiz) {
		if (nivelActual == x) {
			A.addFirstChild(raiz, e);
		}
		else {
			for (Position<E> elem: A.children(raiz)) {
				insertarCondicional(nivelActual+1, e, x, A,elem);
			}
				
		}

	}


}

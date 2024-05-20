package model;
import java.lang.Exception;

public class ListaSimples<T> {
	private NoListaSimples<T> inicio = null;
	
	public void append(T elemento) {
		NoListaSimples<T> buffer = new NoListaSimples<>(elemento);
		if (this.inicio == null) {
			this.inicio = buffer;
		} else {
			this.last().setProximo(buffer);
		}
	}
	public NoListaSimples<T> get (int index) throws IllegalArgumentException {
		int i = 0;
		if (this.inicio == null) {
			throw new IllegalArgumentException("Nao existe item na lista.");
		}
		NoListaSimples<T> buffer = this.inicio;
		for (i = 0; i < index; i++) {
			if (buffer.getProximo() == null) {
				break;
			}
			buffer = buffer.getProximo();
		}
		if (i < index) {
			throw new IllegalArgumentException("O indice informado nao existe.");
		}
		return buffer;
	}
	public int index(T elemento) throws IllegalArgumentException {
		if (this.inicio == null) {
			throw new IllegalArgumentException("Nao existe item na lista.");
		}
		int index = 0;
		if (this.inicio.getValor() == elemento) {
			return index;
		}
		NoListaSimples<T> buffer = this.inicio;
		do {
			if (buffer.getValor() == elemento) {
				return index;
			}
			buffer = buffer.getProximo();
			index++;
		} while (buffer != null);
		throw new IllegalArgumentException("Item nao encontrado.");
	}
	public void insert (int index, T elemento) throws IllegalArgumentException {
		if (index ==0) {
			NoListaSimples<T> buffer_novo = new NoListaSimples<>(elemento);
			if (this.inicio != null) {
				NoListaSimples<T> buffer_inicio = this.inicio;
				buffer_novo.setProximo(buffer_inicio);
				this.inicio = buffer_novo;
			} else {
				this.inicio = buffer_novo;
			}
		} else {
			this.insert( this.get( --index), elemento ); 
		}
	}
	public void insert(NoListaSimples<T> item, T elemento) throws IllegalArgumentException {
		NoListaSimples<T> buffer_novo = new NoListaSimples<>(elemento);
		NoListaSimples<T> buffer_proximo = item.getProximo();
		item.setProximo(buffer_novo);
		buffer_novo.setProximo(buffer_proximo);
	}
	public NoListaSimples<T> last() throws IllegalArgumentException {
		if (this.inicio == null) {
			throw new IllegalArgumentException("Nao existe item na lista.");
		}
		NoListaSimples<T> buffer = this.inicio;
		while (buffer.getProximo() != null) {
			buffer = buffer.getProximo();
		}
		return buffer;		
	}
	public void remove(int index) {
		if (index == 0) {
			this.inicio.setValor(null);
			if (this.inicio.getProximo() == null) {
				this.inicio = null;
			} else {
				NoListaSimples<T> buffer = this.inicio.getProximo();
				this.inicio.setProximo(null);
				this.inicio = buffer;
			}
			return;
		}
		NoListaSimples<T> buffer_anterior = this.get(index - 1);
		NoListaSimples<T> item = buffer_anterior.getProximo();
		NoListaSimples<T> buffer_proximo = item.getProximo();
		buffer_anterior.setProximo(buffer_proximo);
		item.setProximo(null);
		item.setValor(null);
	}
	public int total() {
		if (this.inicio == null) {
			return 0;
		}
		NoListaSimples<T> buffer = this.inicio;
		int total_elementos = 0;
		
		do {
			total_elementos++;
			buffer = buffer.getProximo();
		} while (buffer != null);
		return total_elementos;
	}
	@Override
	public String toString() {
		if (this.inicio == null) {
			return "[]";
		}
		StringBuilder builder = new StringBuilder("[");
		NoListaSimples<T> buffer = this.inicio;
		builder.append(buffer.getValor());
		while ( buffer.getProximo() != null) {
			builder.append(",");
			buffer = buffer.getProximo();
			builder.append(buffer.getValor());
		}
		builder.append("]");
		return builder.toString();
	}
	public Arvore balancear(Arvore balanceada, ListaSimples lista, int inicio, int fim) {
		if (inicio > fim) {
			return null;
		}
		int meio = (inicio + fim) / 2;
		System.out.println("Meio="+meio);
		balanceada.add((int) get(meio).getValor());
		balancear(balanceada, lista, inicio, meio-1);
		balancear(balanceada, lista, meio+1, fim);
		return balanceada;
	}
}
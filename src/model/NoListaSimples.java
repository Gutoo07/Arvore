package model;

public class NoListaSimples<T> {
	private NoListaSimples<T> proximo;
	private T valor;
	
	public NoListaSimples (T valor) {
		this.valor = valor;
		this.proximo = null;
	}
	public void setValor(T valor) {
		this.valor = valor;
	}
	public T getValor() {
		return this.valor;
	}
	public void setProximo(NoListaSimples<T> proximo) {
		this.proximo = proximo;
	}
	public NoListaSimples<T> getProximo() {
		return this.proximo;
	}
	@Override
	public String toString() {
		return this.valor.toString();
	}
}

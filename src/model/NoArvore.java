package model;

public class NoArvore<T> {
	private T valor;
	private NoArvore<T> maior;
	private NoArvore<T> menor;
	
	public NoArvore(T valor) {
		this.valor = valor;
		this.maior = null;
		this.menor = null;
	}
	public T getValor() {
		return this.valor;
	}
	public void setValor(T valor) {
		this.valor = valor;
	}
	public NoArvore<T> getMaior() {
		return this.maior;
	}
	public NoArvore<T> getMenor() {
		return this.menor;
	}
	public void setMaior(NoArvore<T> maior) {
		this.maior = maior;
	}
	public void setMenor(NoArvore<T> menor) {
		this.menor = menor;
	}
	@Override
	public String toString() {
		return this.valor.toString();
	}
}

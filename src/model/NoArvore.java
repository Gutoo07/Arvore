package model;

public class NoArvore<T> {
	private T valor;
	private NoArvore<T> maior;
	private NoArvore<T> menor;
	private NoArvore<T> raiz;
	private int balanceamento;
	
	public NoArvore(T valor) {
		this.valor = valor;
		this.maior = null;
		this.menor = null;
		this.raiz = null;
		this.balanceamento = 0;
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
	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}
	public int getBalanceamento() {
		return this.balanceamento;
	}
	public void setRaiz(NoArvore<T> raiz) {
		this.raiz = raiz;
	}
	public NoArvore<T> getRaiz() {
		return this.raiz;
	}
	@Override
	public String toString() {
		return this.valor.toString();
	}
}

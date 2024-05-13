package model;
import model.ListaSimples;
import model.NoArvore;

public class Arvore<T extends Comparable> {
	private NoArvore<T> raiz;
	private int tamanho = 0;
	
	//add:  adiciona novo elemento na arvore
	//ordem: retorna uma lista encadeada dos elementos ordenados
	//preOrdem: retorna uma lista encadeada dos elementos na pre-ordem
	//posOrdem: retorna uma lista encadeada dos elementos na pos-ordem
	
	public Arvore() {
		this.raiz = null;
	}
	public void add(T valor) {
		NoArvore<T> novo = new NoArvore<T>(valor);
		tamanho++;
		if ( raiz == null) {
			this.raiz = novo;
			return;
		}
		
		NoArvore<T> atual = this.raiz;
		while(true) {
			if(novo.getValor().compareTo(atual.getValor()) == -1) {
				if (atual.getMenor() != null) {
					atual = atual.getMenor();
				} else {
					atual.setMenor(novo);
					break;
				}
			} else {
				if (atual.getMaior() != null) {
					atual = atual.getMaior();
				} else {
					atual.setMaior(novo);
					break;
				}
			}
		}
	}
	public ListaSimples ordem() {
		ListaSimples lista = new ListaSimples();
		NoArvore<T> atual = this.raiz;
		ordem(atual, lista);
		return lista;
	}
	private void ordem(NoArvore<T> atual, ListaSimples lista) {
		if (atual != null) {
			ordem(atual.getMenor(), lista);
			lista.append(atual.getValor());
			ordem(atual.getMaior(), lista);
		}
	}
}









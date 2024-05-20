package model;

public class Arvore<T extends Comparable> {
	private NoArvore<T> raiz;
	private int tamanho = 0;
			
	public Arvore() {
		this.raiz = null;
	}
	
	public void add(T valor) {
		NoArvore<T> novo = new NoArvore<T>(valor);
		tamanho++;
		if (raiz == null) {
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
	public ListaSimples preOrdem() {
		ListaSimples lista = new ListaSimples();
		NoArvore<T> atual = this.raiz;
		preOrdem(atual, lista);
		return lista;
	}
	private void preOrdem(NoArvore<T> atual, ListaSimples lista) {
		if (atual != null) {
			lista.append(atual.getValor());
			preOrdem(atual.getMenor(), lista);
			preOrdem(atual.getMaior(), lista);
		}
	}
	public ListaSimples posOrdem() {
		ListaSimples lista = new ListaSimples();
		NoArvore<T> atual = this.raiz;
		posOrdem(atual, lista);
		return lista;
	}
	private void posOrdem(NoArvore<T> atual, ListaSimples lista) {
		if (atual != null) {
			posOrdem(atual.getMenor(), lista);
			posOrdem(atual.getMaior(), lista);
			lista.append(atual.getValor());
		}
	}	
	public boolean remove(T valor) {
		//buscar o nó na árvore
		NoArvore<T> atual = this.raiz;
		NoArvore<T> paiAtual = null;
		while (atual != null) {
			if (atual.getValor().equals(valor)) {
				break;
			} else if (valor.compareTo(atual.getValor()) == -1) {
				//valor procurado é menor que o atual
				paiAtual = atual;
				atual = atual.getMenor();
			} else {
				paiAtual = atual;
				atual = atual.getMaior();
			}
		}
		//verifica se existe o Nó
		if (atual == null) {
			return false;
		}
		//Nó tem 2 filhos ou Nó tem somente filho à direita
		if (atual.getMaior() != null) {
			NoArvore<T> substituto = atual.getMaior();
			NoArvore<T> paiSubstituto = atual;
			while (substituto.getMenor() != null) {
				paiSubstituto = substituto;
				substituto = substituto.getMenor();
			}
			substituto.setMenor(atual.getMenor());
			if (paiAtual != null) {
				//verificar se é a raiz
				if (atual.getValor().compareTo(paiAtual.getValor()) == -1 ) {
					//atual < paiAtual
					paiAtual.setMenor(substituto);
				} else {
					paiAtual.setMaior(substituto);
				}
			} else {
				//se não tem paiAtual, então é a raiz
				this.raiz = substituto;
				paiSubstituto.setMenor(null);
				this.raiz.setMaior(paiSubstituto);
				this.raiz.setMenor(atual.getMenor());
			}
			//removeu no Nó da árvore
			if (substituto.getValor().compareTo(paiSubstituto.getValor()) == -1) {
				//substituto < paiSubstituto
				paiSubstituto.setMenor(null);
				substituto.setMaior(paiSubstituto);
			} else {
				paiSubstituto.setMaior(null);
			}
		} else if (atual.getMenor() != null) {
			//tem filho só à esquerda
			NoArvore<T> substituto = atual.getMenor();
			NoArvore<T> paiSubstituto = atual;
			while (substituto.getMaior() != null) {
				paiSubstituto = substituto;
				substituto = substituto.getMaior();
			}
			if (paiAtual != null) {
				if (atual.getValor().compareTo(paiAtual.getValor()) == -1) {
					//atual < paiAtual
					paiAtual.setMenor(substituto);
				} else {
					paiAtual.setMaior(substituto);
				}
			} else {
				//se for a raiz
				this.raiz = substituto;
			}
			//removeu o Nó da árvore
			if (substituto.getValor().compareTo(paiSubstituto.getValor()) == -1) {
				paiSubstituto.setMenor(null);
			} else {
				paiSubstituto.setMaior(null);
			}
		} else { //não tem filho
			if (paiAtual != null) {
				if (atual.getValor().compareTo(paiAtual.getValor()) == -1) {
					//atual < paiAtual
					paiAtual.setMenor(null);
				} else {
					paiAtual.setMaior(null);
				}
			} else { //é a raiz
				this.raiz = null;
			}
		}
		return true;
	}
}
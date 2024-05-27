package model;
import model.NoArvore;
import model.ListaSimples;

public class Arvore<T extends Comparable> {
	private NoArvore<T> raiz;
	private int tamanho = 0;
	
	//add
	//altura(No<T>)
	//balancear(No<T>)
	//defineBalanceamento(No<T>)
	//ordem()
	//preOrdem()
	//posOrdem()
	//remove(T valor)
	//rotacaoEsquerda(No<T>)
	//rotacaoDireita(No<T>)
	//rotacaoDuplaEsquerda(No<T>)
	//rotacaoDuplaDireita(No<T>)
	
	public Arvore() {
		this.raiz = null;
	}
	
	public void add(T valor) {
		NoArvore<T> novo = new NoArvore<T>(valor);
		tamanho++;
		if (raiz == null) {
			this.raiz = novo;
			defineBalanceamento(this.raiz);
			return;
		}		
		NoArvore<T> atual = this.raiz;
		while(true) {
			if(novo.getValor().compareTo(atual.getValor()) == -1) {
				if (atual.getMenor() != null) {
					atual = atual.getMenor();
				} else {
					atual.setMenor(novo);
					novo.setRaiz(atual);
					defineBalanceamento(this.raiz);
					this.raiz = balancear(raiz);
					break;
				}
			} else {
				if (atual.getMaior() != null) {
					atual = atual.getMaior();
				} else {
					atual.setMaior(novo);
					novo.setRaiz(atual);
					defineBalanceamento(this.raiz);
					this.raiz = balancear(raiz);
					
					break;
				}
			}
		}
	}
	public int altura (NoArvore<T> atual) {
		if (atual == null) {
			//Se o nó for nulo , sua altura será -1
			return -1;
		}
		if (atual.getMaior() == null && atual.getMenor() == null) {
			//Se não tiver nenhum filho sua altura será 0
			return 0;
		} else if (atual.getMenor() == null) {
			//Se o nó tiver apenas um filho sua altura será 1 + a altura do nó filho
			return 1 + altura(atual.getMaior());
		} else if (atual.getMaior() == null) {
			//Mesma do passo anterior aqui
			return 1 + altura(atual.getMenor());
		} else {
			//Se ele tiver dois filhos, temos que verificar qual filho é mais "alto"
			if (altura(atual.getMenor()) > altura(atual.getMaior())) {
				//A altura do nó será a soma de 1 + a altura do filho mais alto
				return 1 + altura(atual.getMenor());
			} else {
				return 1 + altura(atual.getMaior());
			}
		}
	}
	public void defineBalanceamento(NoArvore<T> atual) {
		atual.setBalanceamento( altura( atual.getMenor() ) - altura( atual.getMaior() ) );
		if (atual.getMaior() != null) {
			defineBalanceamento(atual.getMaior());
		}
		if (atual.getMenor() != null) {
			defineBalanceamento(atual.getMenor());
		}
	}
	public NoArvore<T> rotacaoEsquerda(NoArvore<T> atual) {
		NoArvore aux = atual.getMaior();
		aux.setRaiz(atual.getRaiz());
		
		//tratamento para quando a árvore é gerada
		if (aux.getMenor() != null) {
			aux.getMenor().setRaiz(atual);
		}
		atual.setRaiz(aux);
		atual.setMaior(aux.getMenor());
		aux.setMenor(atual);
		
		if (aux.getRaiz() != null) {
			if (aux.getRaiz().getMaior() == atual) {
				aux.getRaiz().setMaior(aux);
			} else if (aux.getRaiz().getMenor() == atual) {
				aux.getRaiz().setMenor(aux);
			}
		}
		//atualiza o valor de balanceamento
		defineBalanceamento(aux);
		
		return aux;
	}
	public NoArvore rotacaoDireita(NoArvore<T> atual) {
		//Armazena o valor do nó da esquerda do atual
		NoArvore<T> aux = atual.getMenor();
		aux.setRaiz(atual.getRaiz());
		
		//tratamento para quando a árvore é degenerada
		if (aux.getMaior() != null) {
			aux.getMaior().setRaiz(atual);
		}
		atual.setRaiz(aux);
		//Joga o valor da direita do nó da esquerda atual, na esquerda do atual
		atual.setMenor(aux.getMaior());
		
		//troca o valor da direita do nó da esquerda pelo atual
		aux.setMaior(atual);
		if (aux.getRaiz() != null) { //Se aux não for a raiz principal
			//configura o pai pra apontar pro novo nó
			if (aux.getRaiz().getMaior() == atual) {
				aux.getRaiz().setMaior(aux);
			} else if (aux.getRaiz().getMenor() == atual) {
				aux.getRaiz().setMenor(aux);
			}
		}
		defineBalanceamento(aux); //atualiza o valor de balanceamento
		return aux; //retorna o valor do nó da esquerda que é o novo pai
	}
	public NoArvore rotacaoDuplaDireita(NoArvore<T> atual) {
		//Armazena o valor do filho da esquerda
		NoArvore<T> aux = atual.getMenor();
		
		//Faz uma rotacao para a esquerda no filho da esquerda
		atual.setMenor(rotacaoEsquerda(aux));
		
		//Faz uma rotacao para a direita no atual/pai com o filho da esquerda já rodado
		NoArvore<T> aux2 = rotacaoDireita(atual);
		return aux2;
	}
	public NoArvore rotacaoDuplaEsquerda(NoArvore<T> atual) {
		NoArvore<T> aux = atual.getMaior();
		atual.setMaior(rotacaoDireita(aux));
		NoArvore<T> aux2 = rotacaoEsquerda(atual);
		return aux2;
	}
	public void balancear() {
		this.raiz = balancear(this.raiz);
	}
	public NoArvore<T> balancear(NoArvore<T> atual) {
		if (atual.getBalanceamento() == 2 && atual.getMenor().getBalanceamento() >= 0) {
			atual = rotacaoDireita(atual);
		} else if (atual.getBalanceamento() == -2 && atual.getMaior().getBalanceamento() <= 0) {
			atual = rotacaoEsquerda(atual);
		} else if (atual.getBalanceamento() == 2 && atual.getMenor().getBalanceamento() < 0) {
			atual = rotacaoDuplaDireita(atual);
		} else if (atual.getBalanceamento() == -2 && atual.getMaior().getBalanceamento() > 0) {
			atual = rotacaoDuplaEsquerda(atual);
		}
		
		if (atual.getMaior() != null) {
			balancear(atual.getMaior());
		}
		if (atual.getMenor() != null) {
			balancear(atual.getMenor());
		}
		
		return atual;
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
		//buscar o nÃ³ na Ã¡rvore
		NoArvore<T> atual = this.raiz;
		NoArvore<T> paiAtual = null;
		while (atual != null) {
			if (atual.getValor().equals(valor)) {
				break;
			} else if (valor.compareTo(atual.getValor()) == -1) {
				//valor procurado Ã© menor que o atual
				paiAtual = atual;
				atual = atual.getMenor();
			} else {
				paiAtual = atual;
				atual = atual.getMaior();
			}
		}
		//verifica se existe o NÃ³
		if (atual == null) {
			return false;
		}
		//NÃ³ tem 2 filhos ou NÃ³ tem somente filho Ã  direita
		if (atual.getMaior() != null) {
			NoArvore<T> substituto = atual.getMaior();
			NoArvore<T> paiSubstituto = atual;
			while (substituto.getMenor() != null) {
				paiSubstituto = substituto;
				substituto = substituto.getMenor();
			}
			substituto.setMenor(atual.getMenor());
			if (paiAtual != null) {
				//verificar se Ã© a raiz
				if (atual.getValor().compareTo(paiAtual.getValor()) == -1 ) {
					//atual < paiAtual
					paiAtual.setMenor(substituto);
				} else {
					paiAtual.setMaior(substituto);
				}
			} else {
				//se nÃ£o tem paiAtual, entÃ£o Ã© a raiz
				this.raiz = substituto;
				paiSubstituto.setMenor(null);
				this.raiz.setMaior(paiSubstituto);
				this.raiz.setMenor(atual.getMenor());
			}
			//removeu no NÃ³ da Ã¡rvore
			if (substituto.getValor().compareTo(paiSubstituto.getValor()) == -1) {
				//substituto < paiSubstituto
				paiSubstituto.setMenor(null);
				substituto.setMaior(paiSubstituto);
			} else {
				paiSubstituto.setMaior(null);
			}
		} else if (atual.getMenor() != null) {
			//tem filho sÃ³ Ã  esquerda
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
			//removeu o NÃ³ da Ã¡rvore
			if (substituto.getValor().compareTo(paiSubstituto.getValor()) == -1) {
				paiSubstituto.setMenor(null);
			} else {
				paiSubstituto.setMaior(null);
			}
		} else { //nÃ£o tem filho
			if (paiAtual != null) {
				if (atual.getValor().compareTo(paiAtual.getValor()) == -1) {
					//atual < paiAtual
					paiAtual.setMenor(null);
				} else {
					paiAtual.setMaior(null);
				}
			} else { //Ã© a raiz
				this.raiz = null;
			}
		}
		return true;
	}
}
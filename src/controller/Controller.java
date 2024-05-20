package controller;
import model.Arvore;
import model.ListaSimples;

public class Controller {
	public Controller() {
		super();
	}
	public void teste() throws Exception {
		Arvore arvore = new Arvore();
		
		arvore.add(30);
		arvore.add(25);
		arvore.add(40);
		arvore.add(23);
		arvore.add(28);
		arvore.add(35);
		arvore.add(45);
		arvore.add(22);
		arvore.add(24);
		arvore.add(27);
		arvore.add(29);
		arvore.add(31);
		arvore.add(36);
		arvore.add(41);
		arvore.add(46);

		ListaSimples lista = arvore.ordem();	
		System.out.println(lista.toString());
		
		lista = arvore.preOrdem();
		System.out.println(lista.toString());
		System.out.println();
		
		/* Arvore balanceada = lista.balancear(lista);
		
		lista = balanceada.ordem();
		System.out.println(lista.toString());
		
		lista = balanceada.preOrdem();
		System.out.println(lista.toString()); */
		
		
		Arvore balanceada = new Arvore();
		balanceada.add(46);
		balanceada.add(45);
		balanceada.add(41);
		balanceada.add(40);
		balanceada.add(36);
		balanceada.add(35);
		balanceada.add(31);
		balanceada.add(30);
		balanceada.add(29);
		balanceada.add(28);
		balanceada.add(27);
		balanceada.add(25);
		balanceada.add(24);
		balanceada.add(23);
		balanceada.add(22);
		
		lista = balanceada.ordem();

		Arvore balanceada2 = new Arvore();
		lista.balancear(balanceada2, lista, 0, 15);
		
		lista = balanceada2.preOrdem();
		System.out.println(lista.toString());
	}
}
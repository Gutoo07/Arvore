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
		
		arvore.remove(23);
		
		lista = arvore.ordem();	
		System.out.println(lista.toString());
		
		arvore.remove(22);
		arvore.remove(46);
		
		lista = arvore.ordem();
		System.out.println(lista.toString());
		//Remove funcionando, agora vamos implementar o "pai"
		
		
	}
}
package controller;
import model.Arvore;
import model.ListaSimples;

public class Controller {
	public Controller() {
		super();
	}
	public String teste() throws Exception {
		Arvore arvore = new Arvore();
		
		arvore.add(10);
		arvore.add(8);
		arvore.add(18);
		
		ListaSimples lista = arvore.ordem();
		
		return lista.toString();
	}
}
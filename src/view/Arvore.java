package view;
import controller.Controller;

public class Arvore {
	public static void main(String args[]) {
		try {
			Controller obj = new Controller();
			obj.teste();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
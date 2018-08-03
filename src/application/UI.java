package application;

import xadrez.PecaXadrez;

public class UI {

	public static void imprimaTabuleiro(PecaXadrez[][] pecaXadrez) {
		for (int i = 0; i < pecaXadrez.length; i++) {
			System.out.print((8 - i) + " ") ;
			for (int j = 0; j < pecaXadrez.length; j++) {
				imprimaPeca(pecaXadrez[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void imprimaPeca(PecaXadrez peca) {
		if(peca == null) {
			System.out.print("-");
		}else {
			System.out.print(peca);
		}
		System.out.print(" ");
	}
}

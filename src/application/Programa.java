package application;

import xadrez.PartidaXadrez;

public class Programa {

	public static void main(String[] args) {
		
		PartidaXadrez partida = new PartidaXadrez();
		UI.imprimaTabuleiro(partida.getPecas());
		

	}

}

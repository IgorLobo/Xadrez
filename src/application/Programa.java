package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezExceptions;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		List<PecaXadrez> capturadas = new ArrayList<>();

		while(!partida.getChequeMate()) {
			try {
				UI.limparTela();
				UI.imprimaPartida(partida , capturadas);
				System.out.println("\nPeça:");
				PosicaoXadrez posInicial = UI.verificarPosicaoXadrez(sc);

				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(posInicial);
				UI.limparTela();
				UI.imprimaTabuleiro(partida.getPecas(), movimentosPossiveis);

				System.out.println("\nAlvo:");
				PosicaoXadrez posFinal = UI.verificarPosicaoXadrez(sc);

				PecaXadrez pecaCapturada = partida.moverPeca(posInicial, posFinal);
				
				if (pecaCapturada != null) {
					capturadas.add(pecaCapturada);
				}
			}
			catch(XadrezExceptions e){
				System.out.println(e.getMessage());
				System.out.println("Pressione ENTER para prosseguir.");
				sc.nextLine();
			}
			catch(InputMismatchException e){
				System.out.println(e.getMessage());
				System.out.println("Pressione ENTER para prosseguir.");
				sc.nextLine();
			}


		}
		UI.limparTela();
		UI.imprimaPartida(partida, capturadas);
	}

}

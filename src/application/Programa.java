package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezExceptions;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		while(true) {
		try {
			UI.limparTela();
			UI.imprimaTabuleiro(partida.getPecas());
			System.out.println("\nPeça:");
			PosicaoXadrez posInicial = UI.verificarPosicaoXadrez(sc);
			
			boolean[][] movimentosPossiveis = partida.movimentosPossiveis(posInicial);
			UI.limparTela();
			UI.imprimaTabuleiro(partida.getPecas(), movimentosPossiveis);
			
			System.out.println("\nAlvo:");
			PosicaoXadrez posFinal = UI.verificarPosicaoXadrez(sc);
			
			PecaXadrez pecaCapturada = partida.moverPeca(posInicial, posFinal);
		}
		catch(XadrezExceptions e){
			System.out.println(e.getMessage());
			sc.nextLine();
		}
		catch(InputMismatchException e){
			System.out.println(e.getMessage());
			sc.nextLine();
		}

		
		}
	}

}

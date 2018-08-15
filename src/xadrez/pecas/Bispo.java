package xadrez.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		
		
		
		p.setValues((posicao.getLinha()-1), posicao.getColuna()-1);
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha()-1, p.getColuna()-1);
		}
		if(getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
//-------------------------------

		p.setValues((posicao.getLinha())-1, (posicao.getColuna() + 1));
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha()-1, p.getColuna()+1);
		}
		if(getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
//--------------------------------

		p.setValues((posicao.getLinha()+1), (posicao.getColuna() + 1));
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha()+1, p.getColuna()+1);
		}
		if(getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

//------------------------------
		p.setValues((posicao.getLinha()+1), posicao.getColuna()-1);
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha()+1, p.getColuna()-1);
		}
		if(getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}

	@Override
	public String toString() {
		return "B";
	}
	
	
}

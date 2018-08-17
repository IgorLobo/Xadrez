package xadrez.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {
	
	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor,PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean testarTorreRoque(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null & p instanceof Torre && p.getCor() == getCor() && p.getContadorDeMovimentos() == 0;
	}
	
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p== null || p.getCor() != getCor();
		
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		// acima
		p.setValues(posicao.getLinha()-1, posicao.getColuna());
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna()-1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// direita
		p.setValues(posicao.getLinha(), posicao.getColuna()+1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// baixo
		p.setValues(posicao.getLinha()+1, posicao.getColuna());
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// diagonais
		p.setValues(posicao.getLinha()-1, posicao.getColuna()-1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//
		p.setValues(posicao.getLinha()-1, posicao.getColuna()+1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// 
		p.setValues(posicao.getLinha()+1, posicao.getColuna()+1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// 
		p.setValues(posicao.getLinha()+1, posicao.getColuna()-1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//#ROQUE
		if(getContadorDeMovimentos() == 0 & !partidaXadrez.getCheque()) {
			//lado REI
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna()+3);
			if(testarTorreRoque(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna()+1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna()+2);
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna()+2] = true;
				}
			}
		}
		//---
		if(getContadorDeMovimentos() == 0 & !partidaXadrez.getCheque()) {
			//lado RAINHA
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna()-4);
			if(testarTorreRoque(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna()-1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna()-2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna()-3);
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna()-2] = true;
				}
			}
		}
		return mat;
	}
}

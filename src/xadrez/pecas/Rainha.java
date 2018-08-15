package xadrez.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);

		// acima
		p.setValues((posicao.getLinha() - 1), posicao.getColuna());
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// esquerda
		p.setValues((posicao.getLinha()), (posicao.getColuna() - 1));
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// direita
		p.setValues((posicao.getLinha()), (posicao.getColuna() + 1));
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// abaixo
		p.setValues((posicao.getLinha() + 1), posicao.getColuna());
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValues((posicao.getLinha() - 1), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
//-------------------------------

		p.setValues((posicao.getLinha()) - 1, (posicao.getColuna() + 1));
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
//--------------------------------

		p.setValues((posicao.getLinha() + 1), (posicao.getColuna() + 1));
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

//------------------------------
		p.setValues((posicao.getLinha() + 1), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().verificarPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExistente(p) && verificarOponenteNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}

	@Override
	public String toString() {
		return "Q";
	}

}

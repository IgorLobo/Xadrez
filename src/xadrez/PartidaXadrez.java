package xadrez;

import boardgame.Tabuleiro;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		configuracaoInicial();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getColunas()][tabuleiro.getLinhas()];
		for (int i = 0; i < tabuleiro.getColunas(); i++) {
			for (int j = 0; j < tabuleiro.getLinhas(); j++) {
				mat[i][j]= (PecaXadrez) tabuleiro.peca(i,j);
			}
		}
		return mat;
	}
	
	private void colocarNovaPeca(char coluna, int linha,PecaXadrez peca) {
		tabuleiro.colocarPeca(peca,new PosicaoXadrez(coluna,linha).toPosition());
	}
	
	private void configuracaoInicial() {
		colocarNovaPeca('b', 6, new Torre(tabuleiro, Cor.BRANCO));
	}
}

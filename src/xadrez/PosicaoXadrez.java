package xadrez;

import boardgame.Posicao;

public class PosicaoXadrez {

	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezExceptions("Erro instanciando a posição: Posições válidas de a1 á h8. ");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	protected Posicao toPosition() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoXadrez fromPosition(Posicao posicao) {
		return new PosicaoXadrez((char)('a' + posicao.getColuna()),( 8 - posicao.getLinha()));
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
}

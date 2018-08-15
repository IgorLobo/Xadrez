package xadrez;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	private Cor cor;
	private int contadorDeMovimentos;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContadorDeMovimentos() {
		return contadorDeMovimentos;
	}
	
	public void aumentarMovimentos() {
		contadorDeMovimentos++;
	}
	
	public void diminuirMovimentos() {
		contadorDeMovimentos--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosition(posicao);
	}
	
	protected boolean verificarOponenteNaPosicao(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p!=null && p.getCor()!=cor;
	}
	
}

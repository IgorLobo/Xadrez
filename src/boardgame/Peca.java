package boardgame;

public class Peca {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	
	public Peca(Tabuleiro tabuleiro) {
		this.posicao = null;
		this.tabuleiro = tabuleiro;
	}
	
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	
	
	
}

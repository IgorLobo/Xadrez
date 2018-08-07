package boardgame;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas <1 || colunas <1) {
			throw new BoardExecptions("Erro ao criar o tabuleiro: Quantidade de linhas ou de colunas � menor que 1. ");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
			
	public Peca peca(int linha,int coluna) {
		if(!posicaoExistente(linha,coluna)) {
			throw new BoardExecptions("Esta posi��o n�o est� no tabuleiro!");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if(!posicaoExistente(posicao)) {
			throw new BoardExecptions("Esta posi��o n�o est� no tabuleiro!");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPeca(Peca peca,Posicao posicao) {
		if(verificarPeca(posicao)) {
			throw new BoardExecptions("J� existe uma pe�a nessa posi��o");
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean posicaoExistente(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas; 
		
	}
	public boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());	
	}
	
	public boolean verificarPeca(Posicao posicao) {
		if(!posicaoExistente(posicao)) {
			throw new BoardExecptions("Esta posi��o n�o est� no tabuleiro!");
		}
		return peca(posicao) != null;
	}
}

package xadrez;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.pecas.Rei;
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
	
	public PecaXadrez moverPeca(PosicaoXadrez posInicial, PosicaoXadrez posFinal) {
		Posicao pinicial = posInicial.toPosition();
		Posicao pfinal = posFinal.toPosition();
		validarPosInicial(pinicial);
		Peca pecaCapturada = movimentar(pinicial, pfinal);
		return (PecaXadrez)pecaCapturada;
	}
	
	private Peca movimentar(Posicao pinicial, Posicao pfinal) {
		Peca p  = tabuleiro.removerPeca(pinicial);
		Peca pecaCapturada = tabuleiro.removerPeca(pfinal);
		tabuleiro.colocarPeca(p, pfinal);
		return pecaCapturada;
	}

	private void validarPosInicial(Posicao pinicial) {
	if(!tabuleiro.verificarPeca(pinicial)) {
		throw new XadrezExceptions("Não existe peça na posição de origem");
	}
	if(!tabuleiro.peca(pinicial).movimentoPossivel(pinicial)) {
		throw new  XadrezExceptions("Não há movimentos possiveis.");
	}
		
	}

	
	
	private void colocarNovaPeca(char coluna, int linha,PecaXadrez peca) {
		tabuleiro.colocarPeca(peca,new PosicaoXadrez(coluna,linha).toPosition());
	}
	
	private void configuracaoInicial() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}

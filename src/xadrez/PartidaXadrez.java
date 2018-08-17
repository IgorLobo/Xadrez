package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	private int turno;
	private boolean cheque;
	private boolean chequeMate;
	private Cor jogadorAtual;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public int getTurno() {
		return turno;
	}

	public boolean getCheque() {
		return cheque;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		configuracaoInicial();
	}

	private void desfazerMovimento(Posicao pInicial, Posicao pFinal, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(pFinal);
		p.diminuirMovimentos();
		tabuleiro.colocarPeca(p, pInicial);

		if (pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, pFinal);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		// #ROQUE
		if (p instanceof Rei && pFinal.getColuna() == pInicial.getColuna() + 2) {
			Posicao origemT = new Posicao(pInicial.getLinha(), pInicial.getColuna() + 3);
			Posicao alvoT = new Posicao(pInicial.getLinha(), pInicial.getColuna() + 1);
			PecaXadrez Torre = (PecaXadrez) tabuleiro.removerPeca(origemT);
			tabuleiro.colocarPeca(Torre, alvoT);
			Torre.diminuirMovimentos();
		}
		if (p instanceof Rei && pFinal.getColuna() == pInicial.getColuna() - 2) {
			Posicao origemT = new Posicao(pInicial.getLinha(), pInicial.getColuna() - 4);
			Posicao alvoT = new Posicao(pInicial.getLinha(), pInicial.getColuna() - 1);
			PecaXadrez Torre = (PecaXadrez) tabuleiro.removerPeca(origemT);
			tabuleiro.colocarPeca(Torre, alvoT);
			Torre.diminuirMovimentos();
		}

	}

	public boolean getChequeMate() {
		return chequeMate;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getColunas()][tabuleiro.getLinhas()];
		for (int i = 0; i < tabuleiro.getColunas(); i++) {
			for (int j = 0; j < tabuleiro.getLinhas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez pinicial) {
		Posicao posicao = pinicial.toPosition();
		validarPosInicial(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaXadrez moverPeca(PosicaoXadrez posInicial, PosicaoXadrez posFinal) {
		Posicao pinicial = posInicial.toPosition();
		Posicao pfinal = posFinal.toPosition();
		validarPosInicial(pinicial);
		validarPosicaoAlvo(pinicial, pfinal);
		Peca pecaCapturada = movimentar(pinicial, pfinal);
		if (testarCheque(jogadorAtual)) {
			desfazerMovimento(pinicial, pfinal, pecaCapturada);
			throw new XadrezExceptions("Você se colocou em cheque, jogue novamente!");
		}
		cheque = (testarCheque(oponente(jogadorAtual))) ? true : false;

		if (testarChequeMate(oponente(jogadorAtual))) {
			chequeMate = true;
		} else {
			proximoTurno();
		}
		return (PecaXadrez) pecaCapturada;

	}

	private Peca movimentar(Posicao pinicial, Posicao pfinal) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(pinicial);
		p.aumentarMovimentos();
		Peca pecaCapturada = tabuleiro.removerPeca(pfinal);
		tabuleiro.colocarPeca(p, pfinal);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);

		}
		// #ROQUE
		if (p instanceof Rei && pfinal.getColuna() == pinicial.getColuna() + 2) {
			Posicao origenT = new Posicao(pinicial.getLinha(), pinicial.getColuna() + 3);
			Posicao alvoT = new Posicao(pinicial.getLinha(), pinicial.getColuna() + 1);
			PecaXadrez Torre = (PecaXadrez) tabuleiro.removerPeca(origenT);
			tabuleiro.colocarPeca(Torre, alvoT);
			Torre.aumentarMovimentos();
		}
		if (p instanceof Rei && pfinal.getColuna() == pinicial.getColuna() - 2) {
			Posicao origenT = new Posicao(pinicial.getLinha(), pinicial.getColuna() - 4);
			Posicao alvoT = new Posicao(pinicial.getLinha(), pinicial.getColuna() - 1);
			PecaXadrez Torre = (PecaXadrez) tabuleiro.removerPeca(origenT);
			tabuleiro.colocarPeca(Torre, alvoT);
			Torre.aumentarMovimentos();
		}
		return pecaCapturada;
	}

	private void validarPosInicial(Posicao pinicial) {
		if (!tabuleiro.verificarPeca(pinicial)) {
			throw new XadrezExceptions("Não existe peça na posição de origem");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(pinicial)).getCor()) {
			throw new XadrezExceptions("A peça escolhida não é sua");
		}
		if (!tabuleiro.peca(pinicial).aoMenosUmMovimento()) {
			throw new XadrezExceptions("Não há movimentos possiveis");
		}

	}

	private void validarPosicaoAlvo(Posicao pinicial, Posicao pfinal) {
		if (!tabuleiro.peca(pinicial).movimentoPossivel(pfinal)) {
			throw new XadrezExceptions("A peça escolhida não pode mover para a posiçao de destino");
		}

	}

	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private Cor oponente(Cor cor) {
		return (cor == cor.BRANCO) ? cor.PRETO : cor.BRANCO;
	}

	private PecaXadrez rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Não existe o Rei " + cor + " no tabuleiro");
	}

	private boolean testarCheque(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosition();
		List<Peca> pecasOponentes = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peca p : pecasOponentes) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testarChequeMate(Cor cor) {
		if (!testarCheque(cor)) {
			return false;
		}
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : lista) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat.length; j++) {
					if (mat[i][j]) {
						Posicao pInicial = ((PecaXadrez) p).getPosicaoXadrez().toPosition();
						Posicao pFinal = new Posicao(i, j);
						Peca pecaCapturada = movimentar(pInicial, pFinal);
						boolean testarCheque = testarCheque(cor);
						desfazerMovimento(pInicial, pFinal, pecaCapturada);
						if (!testarCheque) {
							return false;
						}
					}

				}
			}
		}
		return true;
	}

	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosition());
		pecasNoTabuleiro.add(peca);
	}

	private void configuracaoInicial() {
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));

		colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));
	}
}

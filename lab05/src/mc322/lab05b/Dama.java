package mc322.lab05b;

public class Dama extends Peca{

	public Dama(int x, int y, boolean isPreto) {
		super(x, y, isPreto);
	}

	/*
    Retorna: 
    0 se não houver nenhuma peça no caminho, 
    -1 se houver uma peça da mesma cor OU mais de uma peça no caminho, independente da cor,
    um inteiro 'n' se houver peça de cor distinta, indicando a distância até tal peça pela diagonal
	 */
	private int temPecaNaDiagonal(Peca[] trajeto) {
		int dist = 0;

		for(int i = 0; i < trajeto.length; i++) {
			if(trajeto[i] != null) {
				if(trajeto[i].cor() == this.cor()) return -1;
				else if(dist != 0) return -1;

				dist = i + 1;
			}
		}

		return dist;
	}

	@Override
	public int mover(Peca[] trajeto, String direcao){
		if(super.mover(trajeto, direcao) == -1) return -1;

		int diag = temPecaNaDiagonal(trajeto);
		if(diag == -1) return -1;

		if		(direcao.equals("SE"))
			this.setPosicao(x+trajeto.length, y-trajeto.length);
		else if	(direcao.equals("SW"))
			this.setPosicao(x-trajeto.length, y-trajeto.length);
		else if	(direcao.equals("NE"))
			this.setPosicao(x+trajeto.length, y+trajeto.length);
		else
			this.setPosicao(x-trajeto.length, y+trajeto.length);

		return diag;
	}

	@Override
	public char repr() {
		return isPreto ? 'P' : 'B';
	}
}
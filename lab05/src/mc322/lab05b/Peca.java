package mc322.lab05b;

public class Peca {
	protected int x, y;
	protected boolean isPreto;

	public Peca(int x, int y, boolean isPreto) {
		this.x = x;
		this.y = y;
		this.isPreto = isPreto;
	}

	public void setPosicao(int x, int y){
		this.x = x;
		this.y = y;
	}

	/* Método chamado para validar movimento das peças 
	 * Cada subclasse deve implementar sua versão 
	 * Retorna (nas subclasses):
	 * -1 se o movimento não for válido
	 * 0 se o movimento foi válido e nenhuma peça foi comida
	 * um inteiro n se o movimento foi válido e uma peça deve ser comida, distante 'n' casas da peça na diagonal 
	 */
	public int mover(Peca[] trajeto, String direcao) {
		if(trajeto.length <= 0) return -1;
		// Checa se tem peca no destino
		if(!(trajeto[trajeto.length - 1] == null)) return -1;

		return 0;
	}

	public boolean cor(){
		return isPreto;
	}

	public char repr() {
		return '_';
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean verificarMovimentoDiagonal(int xFim, int yFim) {
		if (Math.abs(yFim - y) == Math.abs(xFim - x))
			return true;
		else 
			return false;
	}

}

package mc322.lab06;

import mc322.lab06.componentes.Componente;
import mc322.lab06.componentes.Heroi;

public class Caverna implements ICaverna{
	private static Caverna instancia;
	
	private Sala[][] cave;
	private Heroi heroi = null;
	private int tamanho;
	
	private Caverna(int tamanho) {
		this.tamanho = tamanho;
		this.cave = new Sala[tamanho][tamanho];
	}
	
	public void setHeroi(Heroi h) {
		if(heroi != null)
			return;
		
		heroi = h;
	}
	
	/* Move um componente 'comp' para uma nova posicao (x, y) */
	public boolean moverPara(Componente comp, int x, int y) {
		if(!salaDentroDaCaverna(x, y))
			return false;
		
		int xOld = comp.getX(), yOld = comp.getY();
		
		getSalaEm(x, y).addComponente(comp);
		getSalaEm(xOld, yOld).removerComponente(comp.getNome());
		
		comp.setX(x);
		comp.setY(y);
		
		return true;
	}
	
	public void removerComponente(String tipo, int x, int y) {
		if(salaDentroDaCaverna(x, y))
			getSalaEm(x, y).removerComponente(tipo);
	}
	
	public Heroi getHeroi() {
		return heroi;
	}
	
	public int getTamanho() {
		return tamanho;
	}
	
	public Sala getSalaEm(int x, int y) {
		return cave[y][x];
	}
	
	public void setSalaEm(int x, int y, Sala s) {
		cave[y][x] = s;
	}
	
	/* Garante que so existe uma caverna no jogo */
	public static Caverna getInstance() {
		if(instancia == null) 
			instancia = new Caverna(4);
		
		return instancia;
	}
	
	public String toString() {
		String estado = "";
		for(int i = 0; i < tamanho; i++) {
			for(int j = 0; j < tamanho; j++) {
				estado += getSalaEm(j, i).toString();
			}
			estado += "\n";
		}
		
		return estado;
	}
	
	/* Verifica se a sala (x, y) esta dentro da caverna */
	public boolean salaDentroDaCaverna(int x, int y) {
		if (x >= 0 && x < tamanho && y >= 0 && y < tamanho)
			return true;
		return false;
	}
}

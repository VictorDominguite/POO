package mc322.lab06.componentes;

import mc322.lab06.Caverna;

public abstract class ComponenteDinamico extends Componente {
	protected boolean isAlive = false;
	
	public ComponenteDinamico(int x, int y, String nome, boolean ehPrimario) {
		super(x, y, nome, ehPrimario);
	}
	
	public ComponenteDinamico(int x, int y, String nome) {
		super(x, y, nome, false);
	}
	
	public void encontro(Componente encontrado) {
		
	}
	
	/* A entidade morre */
	public void morrer() {
		isAlive = false;
		Caverna.getInstance().getSalaEm(x, y).removerComponente(this.getNome());
		onDestroy();
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getForca() {
		return 0;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
}

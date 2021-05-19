package mc322.lab06.componentes;

import mc322.lab06.Caverna;
import mc322.lab06.Controle;
import mc322.lab06.ICaverna;

public abstract class Componente{
	protected int x, y;
	protected String nome;
	protected boolean ehPrimario;
	
	protected static final ICaverna cave = Caverna.getInstance();
	
	public Componente(int x, int y, String nome, boolean ehPrimario) {
		this.x = x;
		this.y = y;
		this.nome = nome;
		this.ehPrimario = ehPrimario;
	}
	
	public Componente(int x, int y, String nome) {
		this(x, y, nome, false);
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setX(int x) {
		Controle.addNotificacao("Nao eh possivel alterar a posicao de componentes nao dinamicos! ");
	}
	
	public void setY(int y) {
		Controle.addNotificacao("Nao eh possivel alterar a posicao de componentes nao dinamicos! ");
	}
	
	public boolean ehPrimario() {
		return ehPrimario;
	}
	
	/* Side-effects da destruição do componente (ex. retirar efeito das salas vizinhas) */
	public void onDestroy() {
		
	}
	
	public char repr() {
		return MontadorComponente.getRepr(nome);
	}
}

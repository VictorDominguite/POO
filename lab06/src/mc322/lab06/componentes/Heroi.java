package mc322.lab06.componentes;

import mc322.lab06.*;
import java.util.Random;

public class Heroi extends ComponenteDinamico {
	private int numFlechas, ourosColetados;
	private boolean flechaEquipada = false;
	private boolean emOuro = false;
	private boolean justKilledWumpus = false;
	
	private Random rand;
	
	public Heroi() {
		super(0, 0, "heroi");
		this.numFlechas = 1;
		this.ourosColetados = 0;
		this.isAlive = true;
		
		rand = new Random();
	}
	
	/* Utilidades */
	
	public int getNumFlechas() {
		return this.numFlechas;
	}
	
	public int getOurosColetados() {
		return this.ourosColetados;
	}
	
	public boolean getFlechaEquipada() {
		return this.flechaEquipada;
	}
	
	public boolean justKilledWumpus() {
		return justKilledWumpus;
	}
	
	public boolean coletarOuro() {
		if(emOuro) {
			ourosColetados += 1;
			cave.removerComponente("ouro", this.x, this.y);
			emOuro = false;
			return true;
		}
		return false;
	}
	
	public int getForca() {
		int forca = 0;
		
		if(flechaEquipada) forca += 10;
		
		return forca;
	}
	
	/* Acoes */
	
	public boolean mover(char direcao) {
		/* O heroi nunca tera acabado de matar o Wumpus antes de entrar na sala, apenas logo apos entrar nela */
		justKilledWumpus = false;

		emOuro = false;
		
		switch(direcao) {
		case 'S':
			if(!cave.moverPara(this, this.x, this.y + 1)) return false;
			break;
		case 'W':
			if (!cave.moverPara(this, this.x - 1, this.y)) return false;
			break;
		case 'N':
			if (!cave.moverPara(this, this.x, this.y - 1)) return false;
			break;
		case 'E':
			if (!cave.moverPara(this, this.x + 1, this.y)) return false;
			break;
		default:
			return false;
		}
		
		/* Logo apos um movimento, uma flecha possivelmente equipada eh perdida */
		flechaEquipada = false;
		
		return true;
	}
	
	
	public void encontro(Componente c) {
		if(c == null) return;
		
		if(c instanceof Ouro) 
			emOuro = true;
		else if(c instanceof Wumpus) 
			enfrentar((Wumpus) c);
		else if(c instanceof Buraco)
			this.morrer();
		else if(c instanceof Fedor)
			Controle.addNotificacao("Voce sente um fedor...");
		else if(c instanceof Brisa)
			Controle.addNotificacao("Voce sente uma brisa...");
	}
	
	public void enfrentar(ComponenteDinamico inimigo) {
		int forcaHeroi = getForca();
		int forcaInimigo = inimigo.getForca();
		
		if(forcaHeroi < forcaInimigo) morrer();
		else if(forcaHeroi > forcaInimigo) inimigo.morrer();
		else {
			if(rand.nextBoolean()) {
				Controle.addNotificacao("O Wumpus era mais forte do que voce imaginava! D:");
				morrer();
			}
			else {
				inimigo.morrer();
				Controle.addNotificacao("Voce derrotou o Wumpus!");
				justKilledWumpus = true;
			}
		}
	}
	
	/* Se ainda houver flechas, equipa uma e retorna true. Caso contrario retorna false */
	public boolean equiparFlecha() {
		if (numFlechas == 0 || flechaEquipada == true) 
			return false;
		
		numFlechas -= 1;
		flechaEquipada = true;
		
		return true;
	}
	
}

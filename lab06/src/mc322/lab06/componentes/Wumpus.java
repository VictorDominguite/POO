package mc322.lab06.componentes;

public class Wumpus extends ComponenteDinamico {
	
	public Wumpus(int x, int y) {
		super(x, y, "wumpus", true);
		this.isAlive = true;
	}
	
	public Wumpus() {
		this(0, 0);
	}
	
	public int getForca() {
		return 10;
	}
	
	public void onDestroy() {
		cave.removerComponente("fedor", x+1, y);
		cave.removerComponente("fedor", x-1, y);
		cave.removerComponente("fedor", x, y+1);
		cave.removerComponente("fedor", x, y-1);
	}
}

package mc322.lab06.componentes;

public class Buraco extends Componente {
	public Buraco(int x, int y) {
		super(x, y, "buraco", true);
	}
	public Buraco() {
		this(0, 0);
	}
	
	public void onDestroy() {
		cave.removerComponente("brisa", x+1, y);
		cave.removerComponente("brisa", x-1, y);
		cave.removerComponente("brisa", x, y+1);
		cave.removerComponente("brisa", x, y-1);
	}
	
}

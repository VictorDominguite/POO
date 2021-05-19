package mc322.lab06.componentes;

import mc322.lab06.Caverna;

public class MontadorComponente{
	/* A ordem define qual será mostrado prioritariamente na sala */
	public static final String[] componentes = {
		"wumpus", "ouro", "buraco",
		"heroi", 
		"fedor",
		"brisa"
	};
	
	public static char getRepr(String comp) {
		if(comp.equals("heroi")) return 'P';
		if(comp.equals("ouro")) return 'O';
		if(comp.equals("wumpus")) return 'W';
		if(comp.equals("buraco")) return 'B';
		if(comp.equals("brisa")) return 'b';
		if(comp.equals("fedor")) return 'f';
		
		return '-';
	}
	
	public static Componente montarComponente(char comp, int x, int y) {
		if(comp == getRepr("heroi")) {
			Heroi h = new Heroi();
			Caverna.getInstance().setHeroi(h);
			return h;
		}
		
		if(comp == getRepr("ouro"))
			return new Ouro(x, y);
		if(comp == getRepr("wumpus"))
			return new Wumpus(x, y);
		if(comp == getRepr("buraco"))
			return new Buraco(x, y);
		if(comp == getRepr("brisa"))
			return new Brisa(x, y);
		if(comp == getRepr("fedor"))
			return new Fedor(x, y);
		
		return null;
	}

}

package mc322.lab06;

import mc322.lab06.componentes.*;

public class Montador {
	private static int tamCaverna;
	
	/* Cria salas inicialmente vazias */
	private static void criarSalas(Caverna cave) {
		for(int y = 0; y < tamCaverna; y++) {
			for(int x = 0; x < tamCaverna; x++) {
				cave.setSalaEm(x, y, new Sala());
			}
		}
	}
	
	/* Adiciona os componentes nas salas */
	private static void popularSalas(Caverna cave, String[][] componentes) {
		for(int i = 0; i < componentes.length; i++) {
			int x = Integer.parseInt("" + componentes[i][0].charAt(2)) - 1;
			int y = Integer.parseInt("" + componentes[i][0].charAt(0)) - 1;
			char componente = componentes[i][1].charAt(0);
			
			if (cave.salaDentroDaCaverna(x, y) && cave.getSalaEm(x, y) != null) {
				Componente comp = MontadorComponente.montarComponente(componente, x, y);
				cave.getSalaEm(x, y).addComponente(comp);
				
				montarComponentesSecundarios(comp, cave, x, y);	
			}
		}
	}

	/* Retorna true caso a caverna seja montada com sucesso ou false caso contrario */
	public static boolean montarCaverna(String[][] args) {
		Caverna cave = Caverna.getInstance();
		tamCaverna = cave.getTamanho();
		
		if(args.length != tamCaverna*tamCaverna) return false;
		
		criarSalas(cave);
		popularSalas(cave, args);
		
		if (!verificarValidadeCaverna(cave)) 
			return false;
		
		cave.getSalaEm(0, 0).descobrirSala();
		
		return true;
	}
	
	/* Retorna false se o heroi nao estiver na posicao correta ou se nao houver o numero correto de componentes. 
	 * Retorna true caso contrario */
	private static boolean verificarValidadeCaverna(Caverna cave) {
		int numHerois = 0, numWumpus = 0, numBuracos = 0, numOuros = 0, tamCaverna = cave.getTamanho();
		if (!cave.getSalaEm(0, 0).temComponente("heroi")) {
			Controle.println("Deve haver um heroi na posicao (1, 1)");
			return false;
		}
		for (int i = 0; i < tamCaverna; i++) {
			for (int j = 0; j < tamCaverna; j++) {
				if (cave.getSalaEm(i, j).temComponente("heroi")) numHerois += 1;
				else if (cave.getSalaEm(i, j).temComponente("wumpus")) numWumpus += 1;
				else if (cave.getSalaEm(i, j).temComponente("buraco")) numBuracos += 1;
				else if (cave.getSalaEm(i, j).temComponente("ouro")) numOuros += 1;
			}
		}
		//System.out.println(numHerois + " " + numWumpus + " " + numBuracos + " " + numOuros);
		if (numHerois != 1 || numWumpus != 1 || numOuros != 1 || numBuracos < 2 || numBuracos > 3) {
			Controle.println("Numero invalido de componentes primarios");
			return false;
		}
		
		return true;
	}
	
	private static void posicionarComponenteSecundario(Caverna cave, int x, int y, char c) {
		if (cave.salaDentroDaCaverna(x + 1, y)) 
			cave.getSalaEm(x + 1, y).addComponente(MontadorComponente.montarComponente(c, x + 1, y));
		
		if (cave.salaDentroDaCaverna(x - 1, y)) 
			cave.getSalaEm(x - 1, y).addComponente(MontadorComponente.montarComponente(c, x - 1, y));
				
		if (cave.salaDentroDaCaverna(x, y + 1)) 
			cave.getSalaEm(x, y + 1).addComponente(MontadorComponente.montarComponente(c, x, y + 1));
		
		if (cave.salaDentroDaCaverna(x, y - 1)) 
			cave.getSalaEm(x, y - 1).addComponente(MontadorComponente.montarComponente(c, x, y - 1));
	}
	
	private static void montarComponentesSecundarios(Componente comp, Caverna cave, int x, int y) {
		if (comp instanceof Buraco) 
			posicionarComponenteSecundario(cave, x, y, 'b');
		
		else if (comp instanceof Wumpus)
			posicionarComponenteSecundario(cave, x, y, 'f');
	}
	

	
}

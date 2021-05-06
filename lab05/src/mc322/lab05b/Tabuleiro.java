package mc322.lab05b;

public class Tabuleiro {
	private Peca tab[][];
	private int dimensao;

	public Tabuleiro(int dimensao) {
		this.dimensao = dimensao;
		this.tab = new Peca[dimensao][dimensao];

		// Cria o tabuleiro com a posicao [0][0] da matriz correspondendo a casa a1
		for (int i = 0; i < dimensao; i++) {
			for(int j = 0; j < dimensao; j++) {
				if((i + j) % 2 == 0) {
					if (i < 3)
						setPecaEm(j, i, new Peao(j, i, false));
					else if (i >= dimensao - 3)
						setPecaEm(j, i, new Peao(j, i, true));
					else break;
				}
			}
		}
	}

	/* Interagir com o tabuleiro */

	/* Obtem o estado atual do tabuleiro e o devolve em uma string */
	public String obterEstado() {
		String estado = "";
		for (int i = dimensao - 1; i >= 0; i--) {
			for (int j = 0; j < dimensao; j++) {
				estado += 	(getPecaEm(j, i) != null) 
								? (getPecaEm(j, i).repr()) 
								: ("-");
			}
			estado += "\n";
		}
		return estado;
	}

	/* Imprime o estado atual do tabuleiro */
	public void imprimirTabuleiro(String estado) {
		int linha = dimensao;
		int total = estado.length();
		int atual = 0;
		char ch;

		while (atual < total) {
			System.out.print(linha--);

			do {
				ch = estado.charAt(atual++);

				System.out.print(" " + ch);
			} while(ch != '\n');
		}

		System.out.println("  a b c d e f g h");
		System.out.println();
	}

	/* estado: true se ocorreu tudo bem, false se houve algum erro */
	public String[] exportarArquivo(boolean estado) {
		String[] export;
		int num_casas = dimensao * dimensao;

		if(estado) {
			export = new String[num_casas];
			char repr;

			for (int i = dimensao - 1; i >= 0; i--) {
				for (int j = 0; j < dimensao; j++) {
					repr = (getPecaEm(i, j) != null) ? getPecaEm(i, j).repr() : '_';
					export[dimensao*i + j] = "" + Coords.npl(i+1) + (j+1) + repr;
				}
			}
		}
		else {
			export = new String[1];
			export[0] = "erro";
		}

		return export;
	}

	/* @return true se o movimento foi realizado com sucesso */
	public boolean solicitaMovimento(String comando) {
		int[] coords = processarComando(comando);

		int xIni = coords[0], yIni = coords[1], xFim = coords[2], yFim = coords[3];

		Peca peca_mover = getPecaEm(xIni, yIni);

		if (!verificarLimitesTabuleiro(xFim, yFim)) 
			return false;
		if (peca_mover == null)
			return false;
		if (!peca_mover.verificarMovimentoDiagonal(xFim, yFim)) 
			return false;

		String direcao = determinarDirecaoMovimento(xIni, yIni, xFim, yFim);
		Peca[] trajeto = obterTrajeto(xIni, yIni, xFim, yFim, direcao);

		int n = peca_mover.mover(trajeto, direcao);
		if (n != -1) {
			setPecaEm(xFim, yFim, peca_mover);
			setPecaEm(xIni, yIni, null);
			if(n != 0) 
				comerPecaNaDiagonal(xIni, yIni, n, direcao);
		}
		else return false;

		boolean cor = peca_mover.cor();
		if(peca_mover instanceof Peao && checarSeViraDama(yFim, cor)) 
			setPecaEm(xFim, yFim, new Dama(xFim, yFim, cor));

		return true;
	}

	/* Verificações */

	private boolean verificarLimitesTabuleiro(int xFim, int yFim) {
		return !(xFim >= dimensao || xFim < 0 || yFim >= dimensao || yFim <0);
	}

	private boolean checarSeViraDama(int yFim, boolean isPreto) {
		if(isPreto) 
			return yFim == 0;
		else 
			return yFim == dimensao - 1;
	}

	/* Utilitarios */

	private Peca getPecaEm(int x, int y) {
		return tab[y][x];
	}

	private void setPecaEm(int x, int y, Peca p) {
		tab[y][x] = p;
	}

	/* Assume-se o formato de comando: 'xy:zw' */
	private int[] processarComando(String comando) {
		int[] res = new int[4];

		String coord_i = comando.substring(0, 2);
		String coord_f = comando.substring(3, 5);

		System.out.println("Source: " + coord_i);
		System.out.println("Target: " + coord_f); 

		res[0] = Coords.lpn(coord_i.charAt(0)) - 1;
		res[1] = Integer.parseInt("" + coord_i.charAt(1)) - 1;
		res[2] = Coords.lpn(coord_f.charAt(0)) - 1;
		res[3] = Integer.parseInt("" + coord_f.charAt(1)) - 1;

		return res;
	}

	private String determinarDirecaoMovimento(int xIni, int yIni, int xFim, int yFim) {
		if 		(xIni > xFim && yIni > yFim) return "SW";
		else if (xIni < xFim && yIni > yFim) return "SE";
		else if (xIni > xFim && yIni < yFim) return "NW";
		else if (xIni < xFim && yIni < yFim) return "NE";
		else 								 return "ERRO";
	}

	private Peca[] obterTrajeto(int xIni, int yIni, int xFim, int yFim, String direcao) {
		int i = 0, x = xIni, y = yIni;
		Peca[] trajeto = new Peca[Math.abs(yFim - yIni)];

		switch (direcao) {
		case ("NE"):
			x += 1;
			y += 1;
			while (x <= xFim) 
				trajeto[i++] = getPecaEm(x++, y++);

			break;
		case ("NW"):
			x -= 1;
			y += 1;
			while (x >= xFim) 
				trajeto[i++] = getPecaEm(x--, y++);

			break;
		case ("SE"):
			x += 1;
			y -= 1;
			while (x <= xFim) 
				trajeto[i++] = getPecaEm(x++, y--);

			break;
		case ("SW"):
			x -= 1;
			y -= 1;
			while (x >= xFim) 
				trajeto[i++] = getPecaEm(x--, y--);

			break;
		}
		return trajeto;
	}

	private void comerPecaNaDiagonal(int xIni, int yIni, int n, String direcao) {
		if		(direcao.equals("SE")) 	setPecaEm(xIni + n, yIni - n, null);
		else if	(direcao.equals("SW")) 	setPecaEm(xIni - n, yIni - n, null);
		else if	(direcao.equals("NE")) 	setPecaEm(xIni + n, yIni + n, null);
		else 							setPecaEm(xIni - n, yIni + n, null);
	}

}
package mc322.lab06;

public class AppMundoWumpus {
	private static CSVHandling handler;
	
	private static void setEntrada(String entrada) {
		if(handler != null)
			handler.setDataSource(entrada);
	}
	
	public static void executaJogo(String path) {
		if(handler == null)
			handler = new CSVHandling();
		
		setEntrada(path);
		
		String[][] estado_inicial = handler.requestCommands();
		
		//Inicializar coisas do jogo, tipo caverna, controle, etc...
		if (!Montador.montarCaverna(estado_inicial)) return;
		Controle ct = new Controle(Caverna.getInstance().getHeroi());
		
		ct.executaJogo();
	}

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("E necessario dizer qual o arquivo CSV de entrada! ");
			System.exit(1);
		}
		
		AppMundoWumpus.executaJogo(args[0]);
	}

}

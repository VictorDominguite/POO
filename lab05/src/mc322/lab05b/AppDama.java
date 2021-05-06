package mc322.lab05b;

public class AppDama {
	private static CSVHandling handler;

	/* Executa, imprime e retorna todos os estados do jogo dado pelo arquivo csv em 'source'
	 */
	public static String[] executaJogo(String source){
		AppDama.setReaderSource(source);

		String[] commands = handler.requestCommands();

		String[] estados = new String[commands.length + 1];

		Tabuleiro tab = new Tabuleiro(8);

		System.out.println("Tabuleiro inicial: ");
		estados[0] = tab.obterEstado();
		tab.imprimirTabuleiro(estados[0]);

		for(int i = 0; i < commands.length; i++){
			if(tab.solicitaMovimento(commands[i])) {
				estados[i+1] = tab.obterEstado();
				tab.imprimirTabuleiro(estados[i+1]);
			}
			else {
				System.out.println("Movimento invalido!");
				handler.exportState(tab.exportarArquivo(false));
				return estados;
			}
		}

		handler.exportState(tab.exportarArquivo(true));
		return estados;
	} 

	public static void setReader() {
		if(AppDama.handler == null)
			AppDama.handler = new CSVHandling();
	}

	public static void setReaderExport(String export) {
		AppDama.handler.setDataExport(export);
	}

	public static void setReaderSource(String source) {
		AppDama.handler.setDataSource(source);
	}

	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Deve ser dado ao menos dois argumentos!");
			System.exit(1);
		}

		AppDama.setReader();
		AppDama.setReaderExport(args[1]);

		String[] estados = AppDama.executaJogo(args[0]);
	}
}
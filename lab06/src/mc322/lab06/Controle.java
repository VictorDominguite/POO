package mc322.lab06;

import java.util.Scanner;

import mc322.lab06.componentes.Heroi;

public class Controle {
	private Scanner teclado;
	private Heroi heroi;
	private int pontos;
	private String player;
	
	private Caverna cave;
	
	private static String[] notificacoes  = new String[8];
	private static int indice_notif = 0;
	
	public Controle(Heroi heroi) {
		teclado = new Scanner(System.in);
		
		this.heroi = heroi;
		this.pontos = 0;
		
		this.cave = Caverna.getInstance();
	}
	
	private void getNome() {
		print("Digite seu nome: ");
		this.player = teclado.nextLine();
		println("");
	}
	
	public static void print(String s) {
		System.out.print(s);
	}
	
	public static void println(String s) {
		System.out.println(s);
	}
	
	public static void addNotificacao(String s) {
		notificacoes[indice_notif] = s;
		indice_notif += 1;
	}
	
	private void lerNotificacoes() {
		for(int i = 0; i < indice_notif; i++) 
			println(notificacoes[i]);
		
		indice_notif = 0;
	}
	
	public void addPontos(int x) {
		this.pontos += x;
	}
	
	private void printCave(Caverna cave) {
		int linha = 1;
		String estado = cave.toString();
		
		int total = estado.length();
		int atual = 0;
		char ch;

		while (atual < total) {
			print("" + linha++);

			do {
				ch = estado.charAt(atual++);

				print(" " + ch);
			} while(ch != '\n');
		}

		println("  1 2 3 4 \n");
	}
	
	private boolean processarComando(char comando) {
		switch(comando) {
		//Sair do jogo
		case('q'):
			return false;
		
		//Andar para cima
		case('w'):
			if(!heroi.mover('N'))
				addNotificacao("Nao foi possivel se mover nessa direcao. ");
			else addPontos(-15);
			break;
		
		//Andar para esquerda
		case('a'):
			if(!heroi.mover('W'))
				addNotificacao("Nao foi possivel se mover nessa direcao. ");
			else addPontos(-15);
			break;
		
		//Andar para baixo
		case('s'):
			if(!heroi.mover('S'))
				addNotificacao("Nao foi possivel se mover nessa direcao. ");
			else addPontos(-15);
			break;
		
		//Andar para direita
		case('d'):
			if(!heroi.mover('E'))
				addNotificacao("Nao foi possivel se mover nessa direcao. ");
			else addPontos(-15);
			break;
		
		//Tentar equipar flecha
		case('k'):
			if(!heroi.equiparFlecha())
				addNotificacao("Nao ha flechas disponiveis! ");
			else
				addPontos(-100);
				addNotificacao("Flecha equipada. ");
			break;
		
		//Coletar ouro
		case('c'):
			if(!heroi.coletarOuro())
				addNotificacao("Nao ha ouro nessa sala!");
			else 
				addNotificacao("Ouro coletado!");
			break;
		
		//Menu de ajuda (comando adicional)
		case('h'):
			addNotificacao("== Comandos disponiveis ==");
			addNotificacao("=  h - Abre esse menu	=");
			addNotificacao("=  w,a,s,d - Se mover	=");
			addNotificacao("=  k - Equipar flecha	=");
			addNotificacao("=  c - Coletar ouro	=");
			addNotificacao("==========================");
			break;
		
		default:
			addNotificacao("Comando invalido! Entre 'h' para abrir o menu de ajuda. ");
		}
		
		if (heroi.justKilledWumpus()) addPontos(500);
		
		return true;
	}
	
	public void printEstadoAtual() {
		println("=========================");
		println("Flechas disponíveis: " + heroi.getNumFlechas());
		println("Ouro em mãos: " + (heroi.getOurosColetados() * 1000) + "$");
		
		println("\nMapa: ");
		printCave(cave);
		
		println("Player: " + player);
		println("Score: " + pontos + "\n");
	}
	
	public void executaJogo() {
		getNome();
		printEstadoAtual();
		
		char comando;
		boolean rodando;
		
		do {
			print("Entre um comando: ");
			comando = teclado.nextLine().charAt(0);
			
			rodando = processarComando(comando);
			
			printEstadoAtual();
			
			if(!heroi.isAlive()) {
				addPontos(-1000);
				addNotificacao("Voce morreu!!! =(");
				rodando = false;
			}
			
			/* Verifica se o heroi ja coletou o ouro e se esta na saida da caverna */
			else if (heroi.getOurosColetados() > 0 
						&& heroi.getX() == 0 
						&& heroi.getY() == 0 
						&& comando == 'q') {
				addPontos(1000);
				addNotificacao("Voce ganhou!!! =D");
				rodando = false;
			}
			
			lerNotificacoes();
		} while(rodando);
		
		println("=========================");
		println("Score final: " + pontos);
		println("Volte sempre, " + player + "!");
	}
	
}

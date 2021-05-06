package mc322.lab05b;

public class Coords{
	public Coords(){}

	/* Letra para Número */
	public static int lpn(char letra){
		return ((int) letra) - ((int) 'a') + 1;
	}

	/* Número para Letra */
	public static char npl(int numero) {
		return (char) (numero + (int) 'a' - 1);
	}
}
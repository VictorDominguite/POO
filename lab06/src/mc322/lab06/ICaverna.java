package mc322.lab06;

import mc322.lab06.componentes.Componente;

public interface ICaverna {
	public boolean moverPara(Componente comp, int x, int y);
	public void removerComponente(String tipo, int x, int y);
}

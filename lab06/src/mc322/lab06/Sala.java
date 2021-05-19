package mc322.lab06;

import mc322.lab06.componentes.*;

public class Sala {
	private boolean descoberta = false;
	private Componente[] componentes;
	private int maxComponentes;
	private int numComponentes = 0;
	
	public Sala(int maxComponentes) {
		this.maxComponentes = maxComponentes;
		this.componentes = new Componente[maxComponentes];
	}
	
	public Sala() {
		this(4);
	}
	
	public void descobrirSala() {
		descoberta = true;
	}
	
	public void addComponente(Componente c) {
		if(c == null) return;
		
		if(numComponentes == maxComponentes) {
			Controle.addNotificacao("Nao ha mais espaco na sala");
			return;
		}
		
		if (c.ehPrimario() && obterPrimario() != null) {
			Controle.addNotificacao("Nao e possivel adicionar um componente primario onde ja ha outro componente primario");
			return;
		}
		
		componentes[numComponentes] = c;
		numComponentes += 1;
		
		if(c instanceof Heroi) {
			Heroi h = (Heroi) c;
			for(Componente comp : componentes)
				h.encontro(comp);
			
			descobrirSala();
		}
	}
	
	public void removerComponente(String tipo) {
		for(int i = 0; i < numComponentes; i++) {
			if(tipo.equals(componentes[i].getNome())) {
				while(i + 1 < numComponentes) {
					componentes[i] = componentes[i+1];
					i += 1;
				}
				componentes[i] = null;
			}
		}
		
		numComponentes -= 1;
	}

	/* Verifica se ha uma componente de 'nome' na sala */
	public boolean temComponente(String nome) {
		for (int i = 0; i < numComponentes; i++) {
			if(componentes[i] == null) break;
			if (componentes[i].getNome().equals(nome)) 
				return true;
		}
		
		return false;
	}
	
	public Componente[] obterComponentes() {
		return componentes;
	}
	
	public Componente obterPrimario() {
		for(int i = 0; i < numComponentes; i++) {
			if(componentes[i].ehPrimario())
				return componentes[i];
		}
		return null;
	}
	
	public String toString() {
		// So ira retorna o proximo se nao houver o anterior na mesma sala
		if (descoberta) {
			for(String s : MontadorComponente.componentes) {
				if(temComponente(s)) 
					return "" + MontadorComponente.getRepr(s);
			}
			return "#";
		}
		return "-";
	}
}

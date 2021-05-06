package mc322.lab05b;

public class Peao extends Peca{

	public Peao(int x, int y, boolean isPreto){
		super(x, y, isPreto);
	}

	@Override
	public int mover(Peca[] trajeto, String direcao){
		if(super.mover(trajeto, direcao) == -1) return -1;

		switch(trajeto.length) {
		case(1):	//andar
			if(trajeto[0] != null) return -1;

			switch(direcao) {
			case("SE"):
				if(!isPreto) return -1;
				this.setPosicao(x+1, y-1);
				break;
			case("SW"):
				if(!isPreto) return -1;
				this.setPosicao(x-1, y-1);
				break;
			case("NE"):
				if(isPreto) return -1;
				this.setPosicao(x+1, y+1);
				break;
			case("NW"):
				if(isPreto) return -1;
				this.setPosicao(x-1, y+1);
				break;
			default:
				return -1;
			}

			return 0;
		case(2):	//comer
			if(trajeto[0] == null) return -1;
			if(trajeto[1] != null) return -1;

			if		(direcao.equals("SE"))
				this.setPosicao(x+2, y-2);
			else if	(direcao.equals("SW"))
				this.setPosicao(x-2, y-2);
			else if	(direcao.equals("NE"))
				this.setPosicao(x+2, y+2);
			else
				this.setPosicao(x-2, y+2);

			return 1;
		default:
			return -1;
		}
	}

	@Override
	public char repr(){
		return isPreto ? 'p' : 'b';
	}
}
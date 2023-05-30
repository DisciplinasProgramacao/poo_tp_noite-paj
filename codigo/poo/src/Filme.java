
import java.time.LocalDate;
import java.util.ArrayList;

public class Filme extends Midia{
	
	private int duracao;

	public Filme(String idMidia, String nome, LocalDate dataLancamento, ArrayList<String> idioma, ArrayList<String> genero, int duracao) {
		super(idMidia, nome, dataLancamento, idioma, genero);
		this.duracao = duracao;
	}

	
	//Getters and Setters 
	
	protected int getDuracao() {
		return duracao;
	}

	protected void setDuracao(int duracao) {
		this.duracao = duracao;
	}

}

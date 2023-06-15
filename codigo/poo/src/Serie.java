
import java.time.LocalDate;

public class Serie extends Midia{
	
	private int quantEp;
	
	public Serie(String idMidia, String nome, LocalDate dataLancamento, int quantEp) {
		super(idMidia,nome,dataLancamento);
		this.quantEp = quantEp;
	}
	
	public Serie(String nome, LocalDate dataLancamento, int quantEp) {
		super(nome,dataLancamento);
		this.quantEp = quantEp;
	}

	//Getters and Setters
	
	protected int getQuantEp() {
		return quantEp;
	}
}

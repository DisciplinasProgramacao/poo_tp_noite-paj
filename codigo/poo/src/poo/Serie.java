package poo;

import java.util.ArrayList;
import java.time.LocalDate;

public class Serie extends Midia{
	
	private int quantEp;
	
	public Serie(String idMidia, String nome, LocalDate dataLancamento, ArrayList<String> idioma, ArrayList<String> genero, int quantEp) {
		super(idMidia,nome,dataLancamento,idioma,genero);
		this.quantEp = quantEp;
	}

	//Getters and Setters
	
	protected int getQuantEp() {
		return quantEp;
	}

	protected void setQuantEp(int quantEp) {
		this.quantEp = quantEp;
	}
	
}

package poo;

import java.util.ArrayList;
import java.time.LocalDate;

public class Midia {
	protected final String idMidia;
	protected final String nome;
	protected ArrayList<String> idioma = new ArrayList<>();
	protected ArrayList<String> genero = new ArrayList<>();
	protected final LocalDate dataLancamento;
	protected int quantidadeDeViwers;
	
	public Midia(String idMidia, String nome, LocalDate dataLancamento, ArrayList<String> idioma, ArrayList<String> genero) {
		this.idMidia = idMidia;
		this.nome = nome;
		this.dataLancamento = dataLancamento;
		this.quantidadeDeViwers = 0;
		this.idioma = idioma;
		this.genero = genero;
	}
	
	public void adicionarGenero(String genero) {
		this.genero.add(genero);
	}
	
	public void adicionarIdioma(String idioma) {
		this.idioma.add(idioma);
	}
	
	public void removerGenero(String genero) {
		this.genero.remove(genero);
	}
	
	public void removerIdioma(String idioma) {
		this.idioma.remove(idioma);
	}
	
	public void assistiu() {
		quantidadeDeViwers ++;
	}
	
	//Getters and Setters
	
	public ArrayList<String> getIdioma() {
		return idioma;
	}

	public ArrayList<String> getGenero() {
		return genero;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
	
	public String getIdMidia() {
		return idMidia;
	}
	
}

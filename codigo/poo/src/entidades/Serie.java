package entidades;
import java.time.LocalDate;
/**
 * Classe Serie:
 * Usando: encapsulamento, construtores
 * Extendendo a classe Midia
 */
public class Serie extends Midia{
	//#region atributos
	private int quantEp;

	//#endregion

	/**
	 * Cria uma Serie passando os parametros pelo contrutor da classe mãe com o id da Midia
	 * @param idMidia defini o id da serie que ja existe nos arquivos
	 * @param nome para definir  o nome do filme
	 * @param dataLancamento data para definir a datad e lançamento do filme
	 * @param quantEp quantidade de episodio contém a serie
	 */
	public Serie(String idMidia, String nome, LocalDate dataLancamento, int quantEp) {
		super(idMidia,nome,dataLancamento);
		this.quantEp = quantEp;
	}
	/**
	 * Cria uma Serie passando os parametros pelo contrutor da classe mãe
	 * @param nome para definir  o nome do filme
	 * @param dataLancamento data para definir a datad e lançamento do filme
	 * @param quantEp quantidade de episodio contém a serie
	 */
	public Serie(String nome, LocalDate dataLancamento, int quantEp) {
		super(nome,dataLancamento);
		this.quantEp = quantEp;
	}

	@Override
	public String toString() {
		String superString = super.toString();
		StringBuilder sb = new StringBuilder(superString);
		sb.append("\nQuantidade de ep: " + this.quantEp);
		return sb.toString();
	}
	
	//#region getters and setters
	
	public int getQuantEp() {
		return quantEp;
	}

	//#endregion
}

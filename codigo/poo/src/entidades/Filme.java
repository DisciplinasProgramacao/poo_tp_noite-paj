package entidades;
import java.time.LocalDate;

/**
 * Classe Filme:
 * Usando: encapsulamento, construtores
 * Extendendo a classe Midia
 */
public class Filme extends Midia{
	//#region atributos
	private int duracao;

	//#endregion

	//#region construtores
	/**
	 * Cria um filme passando os parametros pelo contrutor da classe mãe com o id da Midia
	 * @param idMidia defini o id do filme que ja existe nos arquivos
	 * @param nome para definir  o nome do filme
	 * @param dataLancamento data para definir a datad e lançamento do filme
	 * @param duracao duração do filme
	 */
	public Filme(String idMidia, String nome, LocalDate dataLancamento, int duracao) {
		super(idMidia, nome, dataLancamento);
		this.duracao = duracao;
	}
	/**
	 * Cria um filme passando os parametros pelo contrutor da classe mãe sem o id da midia
	 * @param nome para definir  o nome do filme
	 * @param dataLancamento data para definir a datad e lançamento do filme
	 * @param duracao duração do filme
	 */
	public Filme(String nome, LocalDate dataLancamento, int duracao) {
		super(nome, dataLancamento);
		this.duracao = duracao;
	}
	
	/**
	 * Metodo para imprimir a descricao de midia
	 * @return String, String contendo a descricao da Midia
	 */
	@Override
	public String toString() {
		String superString = super.toString();
		StringBuilder sb = new StringBuilder(superString);
		sb.append("\nDuração do filme: " + this.duracao);
		return sb.toString();
	}
	//#endregion

	//#region getters and setters
	
	public int getDuracao() {
		return duracao;
	}

	//#endregion
}

package entidades;
import java.util.Random;

/**
 * Enum Idioma:
 * Todos os idiomas de filmes e series
 */
public enum Idioma {
	//#region
	Portugues ("Português"),
	Ingles ("Inglês"),
	Espanhol ("Espanhol");
	//#endregion

	//#region atributos
	String descricao;
	//#endregion

	//#region variaveis
	private static Random irng =  new Random();
	//#endregion

	//#region contrutores
	/**
	 * Cria um enum Idioma recebendo sua descrição por parametro
	 * @param descricao passando a descriçao do enum
	 */
	Idioma(String descricao) {
		this.descricao = descricao;
	}

	//#endregion

	/**
	 * Metodo para gerar  um número aleatório no intervalo de 0 ao total de valores dentro de idioma
	 *
	 * @return retorna um valor aleatório da enumeração Idioma.
	 */
	public static Idioma randomIdioma() {
		Idioma[] idioma = values();
		return idioma[irng.nextInt(idioma.length)];
	}
	//#region getters and setters
	public String getDescricao() {
		return this.descricao;
	}

	//#endregion
}

import java.util.Random;

/**
 * Enum Genero:
 * Todos os generos de filmes e series
 */
public enum Genero {
	//#region
	Acao ("Ação"),
	Anime ("Anime"),
	Aventura ("Aventura"),
	Comedia ("Comedia"),
	Documentario ("Documentario"),
	Drama ("Drama"),
	Policial ("Policial"),
	Romance ("Romance"),
	Suspense ("Suspense");

	//#endregion

	//#region atributos
	String descricao;

	private static Random grng =  new Random();

	//#endregion


	//#region contrutores
	/**
	 * Cria um enum Genero passando sua dedscrição por parametro
	 * @param descricao passando a descriçao do enum
	 */
	Genero(String descricao) {
		this.descricao = descricao;
	}

	//#endregion

	/**
	 * Metodo para gerar  um número aleatório no intervalo de 0 ao total de valores dentro de genero
	 *
	 * @return retorna um valor aleatório da enumeração Genero.
	 */
	public static Genero randomGenero() {
		Genero[] genero = values();
		return genero[grng.nextInt(genero.length)];
	}
	//#region getters and setters
	public String getDescricao() {
		return this.descricao;
	}
}

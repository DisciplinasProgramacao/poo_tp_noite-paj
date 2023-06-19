package entidades;
import excecoes.MidiaJaAdicionadaException;
import excecoes.SemPermissaoException;

/**
 * Classe ClienteProfissional:
 * Usando: encapsulamento, construtores
 */
public class ClienteProfissional extends Cliente{

	//#region atributos
	protected boolean podeAssistir = true;

	//#endregion

	//#region construtores
	/**
	 * Cria um ClienteProfissional passando seus respectivos atributos por parametro para o atributod a classe mãe
	 * @param nome define um nome para o cliente profisioanl
	 * @param usuario define um usuario para a conta cliente profisioanl
	 * @param senha define uma senha para a conta do cliente profisioanl
	 */
	public ClienteProfissional(String nome, String usuario, String senha) {
		super(nome, usuario, senha);
	}
	//#endregion

	//#region métodos de negócio

	/**
	 * O metodo adicionar recebe como parametro uma string contento a opção que o usuario escolheu e uma Midia
	 * que ele selecionou para adicionar na lista Assistidos ou na lista Assistir
	 *
	 * @param opcao, recebe a opção F para lista assistidos e A para lista assistir
	 * @param midia, recebe a midia que o usuario quer adicionar em alguma lista
	 *
	 */
	protected void adicionar(String opcao, Midia midia) throws MidiaJaAdicionadaException, SemPermissaoException {
		switch (opcao.toUpperCase()) {
		case "F":
			verificaAdicao(midia, podeAssistir);
			listaAssistir.add(midia);
			break;

		case "A":
			verificaAdicao(midia, podeAssistir);
			listaAssistidos.add(midia);
			midia.assistiu();
			listaAssistir.remove(midia);
			break;
		}
	}

}

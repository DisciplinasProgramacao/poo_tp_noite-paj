
import java.util.ArrayList;

public class Cliente {

	private String nome;
	private String usuario;
	private String senha;
	private ArrayList<Midia> listaAssistidos;
	private ArrayList<Midia> listaAssistir;

	public Cliente(String nome, String usuario, String senha) {
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}

	/**
	 * O metodo logar recebe como parametro uma string contendo a senha escrita pelo usuario
	 * e irá verificar se ela e igual a senha da conta do cliente.
	 * 
	 * @param senha, recebe a senha escrita pelo usuário
	 * @return boolean, retornará verdadeiro caso a senha digitada seja a mesma da conta do usuário e retornará falso caso seja diferente
	 */
	public boolean logar(String senha) {
		if(this.senha.equals(senha)) return true;
		
		return false;
	}
	
	/**
	 * O metodo recebe como parametro a opcao da lista que deseja inserir a midia e a midia na qual deseja ser inserida
	 * 
	 * @param opcao, opcao para escolher a lista desejada (F para assistir futuramente ou A para já assistidas)
	 * @param midia, objeto da classe Midia, o filme ou a serie a ser adicionada
	 */
	public void adicionar(String opcao, Midia midia) {
		switch (opcao.toUpperCase()) {
		case "F":
			listaAssistir.add(midia);
			break;

		case "A":
			listaAssistidos.add(midia);
			midia.assistiu();
			listaAssistir.remove(midia);
			break;
		}
	}


	/**
	 * O metodo ira buscar na lista desejada pela pesquisa digitada pelo usuario
	 * 
	 * @param busca, recebe uma string para buscar a midia desejada
	 * @param opcao, recebe a lista desejada para fazer a busca (assistidos para lista de assistidos 
	 * 														ou assistir para a lista de assistir futuramente)
	 * 
	 * @return ArrayList<Midia>, retorna uma ArrayList contendo às midias que contem no nome a string buscada pelo usuario
	 */
	public ArrayList<Midia> buscarLista(String busca, String opcao) {
		ArrayList<Midia> resultados = new ArrayList<>();
		switch (opcao) {
		case "assistidos":
			for (Midia midia : listaAssistidos) {
				if (midia.getGenero().contains(busca) || midia.getIdioma().contains(busca)
						|| midia.getNome().contains(busca)) {
					resultados.add(midia);
				}
			}
			return resultados;
			
		case "assistir":
			for (Midia midia : listaAssistir) {
				if (midia.getGenero().contains(busca) || midia.getIdioma().contains(busca)
						|| midia.getNome().contains(busca)) {
					resultados.add(midia);
				}
			}
			return resultados;
		}
		return null;
	}
	// Getters and Setters

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}

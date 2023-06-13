
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cliente implements Comparable<Cliente>{

	private String nome;
	private String usuario;
	private String senha;
	private ArrayList<Midia> listaAssistidos = new ArrayList<>();
	private ArrayList<Midia> listaAssistir = new ArrayList<>();
	private ArrayList<Midia> listaMidiasAvaliadas = new ArrayList<>();

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
	protected void adicionar(String opcao, Midia midia) {
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
	public List<Midia> buscarLista(String busca, String opcao) {
		List<Midia> resultados = new ArrayList<>();
		switch (opcao) {
		case "assistidos":
			resultados = listaAssistidos.stream().filter(m -> m.nome.contains(busca)).collect(Collectors.toList());
			return resultados;
			
		case "assistir":
			resultados = listaAssistir.stream().filter(m -> m.nome.contains(busca)).collect(Collectors.toList());
			return resultados;
		}
		return null;
	}
	
	@Override
	public int compareTo(Cliente o) {
		return this.nome.compareTo(o.nome);
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
	
	public void setListaAvaliados(Midia midia) {
		this.listaMidiasAvaliadas.add(midia);
	}
	
	public ArrayList<Midia> getListaAvaliados() {
		return listaMidiasAvaliadas;
	}

}

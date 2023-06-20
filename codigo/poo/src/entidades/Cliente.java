package entidades;
import excecoes.AvaliacaoInsuficienteException;
import excecoes.MidiaJaAdicionadaException;
import excecoes.SemPermissaoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
/**
 * Classe Cliente:
 * Usando: encapsulamento, construtores
 * Implementando Comparable para ser uma classe comparavel
 * Implementando IComentar para clientes que podem ou não comentar
 */
public class Cliente implements Comparable<Cliente>,IComentar{
	//#region atributos
	private String nome;
	private String usuario;
	private String senha;
	protected ArrayList<Midia> listaAssistidos = new ArrayList<>();
	protected ArrayList<Midia> listaAssistir = new ArrayList<>();
	protected SortedMap<LocalDate,Midia> listaMidiasAvaliadas = new TreeMap<>();
	//#endregion

	//#region construtores
	public Cliente(String nome, String usuario, String senha) {
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}
	//#endregion

	//#region métodos de negócio

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
	 * @throws MidiaJaAdicionadaException
	 * @throws SemPermissaoException
	 */
	protected void adicionar(String opcao, Midia midia) throws MidiaJaAdicionadaException, SemPermissaoException {
		switch (opcao.toUpperCase()) {
		case "F":
			verificaAdicao(midia, true);
			listaAssistir.add(midia);
			break;

		case "A":
			verificaAdicao(midia, false);
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
		case "assistidas":
			if(busca != null) resultados = listaAssistidos.stream().filter(m -> m.nome.contains(busca)).collect(Collectors.toList());
			else return new ArrayList<>(listaAssistidos);
			return resultados;
			
		case "assistir":
			 resultados = listaAssistir.stream().filter(m -> m.nome.contains(busca)).collect(Collectors.toList());
			return resultados;
		}
		return null;
	}
	/**
	 * O metodo ira verificar se contem a midia pesquisada e se o usuario pode assistir filmes em lançamento, caso não
	 * irá jogar exceções
	 *
	 * @param midia, recebe uma midia para verificar se contem na lista
	 * @param podeAssistir, recebe esse bollean para verificar se você tem permissão para assistir filmes em lançamento
	 *
	 * @return void
	 */
	public void verificaAdicao(Midia midia, boolean podeAssistir) throws MidiaJaAdicionadaException, SemPermissaoException {
		if(listaAssistidos.contains(midia)) {
			throw new MidiaJaAdicionadaException("A Midia já foi adicionada à lista! Favor inserir outra midia.");
		}else if(listaAssistir.contains(midia)) {
			throw new MidiaJaAdicionadaException("A Midia já foi adicionada à lista! Favor inserir outra midia.");
		}
		if(midia.getEhLancamento() && !podeAssistir) {
			throw new SemPermissaoException("Você não tem permissão para assistir filmes em lançamento!");
		}
	}


	@Override
	public int compareTo(Cliente o) {
		return this.nome.compareTo(o.nome);
	}

	/**
	 * Metodo utilizado para fazer um comentário na mídia. Só será realizado o comentário se o cliente tiver avalido 5 midias ou mais no mês anterior
	 * 
	 * @param msg, Comentário a ser feito sobre a midia
	 * @param midia, Midia a ser comentada
	 * @throws AvaliacaoInsuficienteException
	 */	
	public void comentar(String msg, Midia midia) throws AvaliacaoInsuficienteException {
		IComentar.comentar(msg, midia,listaMidiasAvaliadas,this.usuario);
	}

	//#region getters and setters


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
	
	public void setListaMidiasAvaliadas(Midia midia) {
		LocalDate data = LocalDate.now();
		this.listaMidiasAvaliadas.put(data,midia);
	}
	
	public SortedMap<LocalDate, Midia> getListaMidiasAvaliadas() {
		return listaMidiasAvaliadas;
	}
	
	public ArrayList<Midia> getListaAssistidos(){
		return this.listaAssistidos;
	}

	public ArrayList<Midia> getListaAssistir() {
		return listaAssistir;
	}
}

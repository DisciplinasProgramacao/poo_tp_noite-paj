package entidades;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Classe Midia:
 * Usando: encapsulamento, construtores
 * Implementando o Comparable para ser uma classe comparavel
 */
public class Midia implements Comparable<Midia> {

	//#region atributos
	protected final String idMidia;
	protected final String nome;
	
	protected ArrayList<String> idioma = new ArrayList<>();
	protected String genero;
	
	protected final LocalDate dataLancamento;
	protected int quantidadeDeViwers;
	
	protected double avaliacao;
	protected ArrayList<Double> notas = new ArrayList<>();
	
	protected SortedMap<String,String> listaComentarios = new TreeMap<>();
	
	protected boolean ehLancamento;

	//#endregion

	/**
	 * Inicializador: verifica se a lista idioma contem algum idioma, se não ele gera idiomas
	 * aleatorios a lista de idiomas.
	 * Pega um genero aleatorio do enum genero
	 * Chama o metodo verificaLancamento para inicializar a classe com a midia verificada
	 */
	public void init() {
		for(int i=0; i<2; i++) {
			if(!idioma.contains(Idioma.randomIdioma().getDescricao()))
			this.idioma.add(Idioma.randomIdioma().getDescricao());
		}
		this.genero = (Genero.randomGenero().getDescricao());
		verificaLancamento();
	}
	//#region construtores

	/**
	 * Cria uma midia pegando os parametros dos contrutores das classes filhas e chama o metodo init
	 * para fazer as devidas verificações
	 * @param idMidia defini o id da Midia que ja existe nos arquivos
	 * @param nome para definir  o nome da midia
	 * @param dataLancamento data para definir a datad e lançamento da midia
	 */
	public Midia(String idMidia, String nome, LocalDate dataLancamento) {
		this.idMidia = idMidia;
		this.nome = nome;
		this.dataLancamento = dataLancamento;
		this.quantidadeDeViwers = 0;
		init();
	}
	/**
	 * Cria uma midia pegando os parametros dos contrutores das classes filhas ,chama o metodo init
	 * para fazer as devidas verificações e cria o id da Midia que não tem nos arquivos aleatorioamente
	 * @param nome para definir  o nome da midia
	 * @param dataLancamento data para definir a datad e lançamento da midia
	 */
	public Midia(String nome, LocalDate dataLancamento) {
		Random num = new Random();
		this.nome = nome;
		this.dataLancamento = dataLancamento;
		this.idMidia = Integer.toString(num.nextInt(100000) + 10000);
		this.quantidadeDeViwers = 0;
		init();
	}

	//#endregion

	/**
	 * O metodo verificaLnacamento verifica se o mês de lançamento é igual ao mês atual, se sim
	 * ele atualiza a variavel ehLançamento para true
	 *
	 */
	private void verificaLancamento(){
		LocalDate data = LocalDate.now();
		if(dataLancamento.getMonth().equals(data.getMonth())) ehLancamento = true;
		else ehLancamento = false;
	}

	//#region métodos de negócio
	/**
	 * Metodos para adicionar e remover algum idioma da midia passando o nome do idioma por parametro
	 *
	 * @param idioma, recebe o nome do idioma a ser adicionado ou removido
	 *
	 */
	public void adicionarIdioma(String idioma) {
		this.idioma.add(idioma);
	}

	public void removerIdioma(String idioma) {
		this.idioma.remove(idioma);
	}

	/**
	 * Metodo para somar q quantidade de viwers que assistiram uma midia
	 */
	public void assistiu() {
		quantidadeDeViwers++;
	}
	/**
	 * Metodo para avaliar uma midia passando sua nota por parametro e fazendo a media dessas notas
	 *
	 * @param nota, recebe o valor da avaliação
	 *
	 */
	public void avaliar(double nota) {
		notas.add(nota);
		double x = 0;
		for (double not : notas) {
			x += not;
		}
		this.avaliacao = x / notas.size();
	}
	/**
	 * Metodo para comentar uma midia passando o usuario do cliente e o comentario da midia
	 *
	 * @param usuario, para verificar qual usuario está comentando
	 * @param comentario, comentario que o usuario deixou na midia
	 *
	 */
	protected void comentar(String usuario, String comentario) {
		listaComentarios.put(usuario, comentario);
	}

	/**
	 * Metodo para comparar uma midia pelo nome
	 *
	 * @param m, recebe o nome a ser comparado

	 *
	 */
	@Override
	public int compareTo(Midia m) {
		return this.nome.compareTo(m.nome);
	}

	@Override
	public String toString() {
		String formattedDate = this.dataLancamento.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
		StringBuilder sb = new StringBuilder("Nome: " + this.nome);
		sb.append("\nData de Lançamento: " + formattedDate);
		sb.append("\nIdiomas: " + this.idioma.toString());
		sb.append("\nGeneros: " + this.genero.toString());

		sb.append("\nAvaliação: " + this.avaliacao);
		sb.append("\nComentarios: " + this.listaComentarios);
		getListaComentarios().forEach((key, value) -> System.out.println(key + " " + value));

		return sb.toString();
	}
	
	// Getters and Setters

	public ArrayList<String> getIdioma() {
		return idioma;
	}

	public String getGenero() {
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

	public double getAvaliacao() {
		return avaliacao;
	}

	public SortedMap<String, String> getListaComentarios() {
		return listaComentarios;
	}
	
	public int getQuantidadeDeViwers() {
		return quantidadeDeViwers;
	}
	
	public boolean getEhLancamento() {
		return ehLancamento;
	}

}

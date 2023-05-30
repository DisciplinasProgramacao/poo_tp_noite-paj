
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ServicoStreaming {

	private ArrayList<Midia> listaMidia = new ArrayList<>();
	private ArrayList<Cliente> listaCliente = new ArrayList<>();
	private ArrayList<String> idioma = new ArrayList<>();
	private ArrayList<String> genero = new ArrayList<>();
	private Cliente clienteLogado;

	public ServicoStreaming() {
		idioma.add("portugues");
		idioma.add("ingles");
		idioma.add("espanhol");
		genero.add("terror");
		genero.add("acao");
		genero.add("comedia");
		genero.add("romance");
		genero.add("ficcao cientifica");
	}

	/**
	 * Metodo para realizar o login do usuario no servico. Ele permanecerá logado
	 * até que deslogue.
	 * 
	 * @param usuario Recebe uma string contendo o nome de usuario
	 * @param senha   Recebe uma string contendo a senha do usuario
	 * @return True = Usuario e senha corretos, False = Usuario ou senha incorretos
	 */
	public boolean logar(String usuario, String senha) {
		for (Cliente cliente : listaCliente) {
			if (cliente.getUsuario().equals(usuario)) {
				if (cliente.logar(senha)) {
					clienteLogado = cliente;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Metodo usado para cadastrar um novo cliente no sistema e depois salva-lo na
	 * lista de clientes
	 * 
	 * @param nome,    string que carrega o nome do cliente
	 * @param usuario, string que carrega o usuario escolhido pelo cliente
	 * @param senha,   string que carrega a senha escolhida pelo cliente
	 */
	public void cadastrar(String nome, String usuario, String senha) {
		Cliente cliente = new Cliente(nome, usuario, senha);
		listaCliente.add(cliente);
	}

	/**
	 * Metodo usado para remover um cliente do sistema, apos informar o usuario e a
	 * senha, será realizado a verificacao se a senha e o usuario informados
	 * coincidem. Se for verdadeiro, removera o cliente da lista, se não, não
	 * removerá da lista
	 * 
	 */
	public void removerCliente() {
		listaCliente.remove(clienteLogado);
	}

	/**
	 * O metodo buscarGeral serve para buscar a midia desejada nas listas de midia
	 * tanto do sistema, quanto do usuario, dependerá da escolha do cliente em qual
	 * lista ele deseja buscar a midia. Caso o cliente tenha digitado uma midia que
	 * não existe, a lista irá retornar nula.
	 * 
	 * @param busca, recebe uma string contendo o que o usuario deseja buscar
	 * @param opcao, recebe a opcao da lista que deseja buscar ("geral" para a lista
	 *               de midias do sistema "assistir" para a lista de midias para
	 *               assistir futuramente do cliente "assistidos" para a lista de
	 *               midias assistidas do cliente)
	 * @return ArrayList<Midia>, retorna uma ArrayList de midias que contem no nome
	 *         a string buscada pelo usuário. Retornara nulo caso não seja
	 *         encontrado nenhuma midia
	 */
	public ArrayList<Midia> buscarGeral(String busca, String opcao) {
		ArrayList<Midia> resultados = new ArrayList<>();
		opcao = opcao.toLowerCase();
		switch (opcao) {
		case "geral":
			for (Midia midia : listaMidia) {
				if (midia.getNome().contains(busca)) {
					resultados.add(midia);
				}
			}
			return resultados;

		case "assistir":
			return clienteLogado.buscarLista(busca, opcao);

		case "assistidos":
			return clienteLogado.buscarLista(busca, opcao);

		}
		return null;
	}

	/**
	 * O metodo serve para adicionar uma midia a lista de midias do sistema
	 * 
	 * @param midia, recebe um objeto midia
	 */
	private void adicionarMidia(Midia midia) {
		listaMidia.add(midia);
	}

	/**
	 * O metodo realiza a leitura de quatro arquivos contendo a audiencia,
	 * espectadores e os filmes e series; Durante a leitura, é realizado o
	 * gravamento das midias e clientes em suas respectivas listas.
	 * 
	 */
	public void lerArquivo() {
		try {
			Random gerador = new Random();
			FileReader arq = new FileReader("D:\\Eclipse\\Programs\\TrabalhoPraticoPOO\\src\\POO_Espectadores.csv");
			FileReader arq2 = new FileReader("D:\\Eclipse\\Programs\\TrabalhoPraticoPOO\\src\\POO_Series.csv");
			FileReader arq3 = new FileReader("D:\\Eclipse\\Programs\\TrabalhoPraticoPOO\\src\\POO_Audiencia.csv");
			FileReader arq4 = new FileReader("D:\\Eclipse\\Programs\\TrabalhoPraticoPOO\\src\\POO_Filmes.csv");
			BufferedReader lerarq = new BufferedReader(arq);
			BufferedReader lerarq2 = new BufferedReader(arq2);
			BufferedReader lerarq3 = new BufferedReader(arq3);
			BufferedReader lerarq4 = new BufferedReader(arq4);

			String linha = lerarq.readLine();
			while (linha != null) {
				String[] linhaPartida = linha.split(";");
				cadastrar(linhaPartida[0], linhaPartida[1], linhaPartida[2]);
				linha = lerarq.readLine();
			}

			String linha2 = lerarq2.readLine();
			while (linha2 != null) {
				String[] linhaPartida2 = linha2.split(";");

				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataLancamento = LocalDate.parse(linhaPartida2[2], formato);

				ArrayList<String> idiomas = new ArrayList<>();
				idiomas.add(idioma.get(gerador.nextInt(2)));

				ArrayList<String> generos = new ArrayList<>();
				generos.add(genero.get(gerador.nextInt(4)));

				Midia serie = new Serie(linhaPartida2[0], linhaPartida2[1], dataLancamento, idiomas, generos,
						gerador.nextInt(12));
				adicionarMidia(serie);
				linha2 = lerarq2.readLine();
			}

			String linha3 = lerarq3.readLine();
			while (linha3 != null) {
				String[] linhaPartida3 = linha3.split(";");

				for (Midia midia : listaMidia) {
					if (midia.getIdMidia().equals(linhaPartida3[2])) {
						for (Cliente cliente : listaCliente) {
							if (cliente.getUsuario().equals(linhaPartida3[0])) {
								cliente.adicionar(linhaPartida3[1], midia);
							}
						}
					}
				}
				linha3 = lerarq3.readLine();
			}

			String linha4 = lerarq4.readLine();
			while (linha != null) {
				String[] linhaPartida4 = linha4.split(";");

				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataLancamento = LocalDate.parse(linhaPartida4[2], formato);

				ArrayList<String> idiomas = new ArrayList<>();
				idiomas.add(idioma.get(gerador.nextInt(2)));

				ArrayList<String> generos = new ArrayList<>();
				generos.add(genero.get(gerador.nextInt(4)));

				int duracao = Integer.parseInt(linhaPartida4[3]);

				Midia filme = new Filme(linhaPartida4[0], linhaPartida4[1], dataLancamento, idiomas, generos, duracao);
				adicionarMidia(filme);
				linha4 = lerarq4.readLine();
			}

			arq.close();
			arq2.close();
			arq3.close();
			arq4.close();

		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * O metodo deslogar é utilizado para deslogar o usuário do sistema.
	 */
	public void deslogar() {
		clienteLogado = null;
	}

	/**
	 * O metodo avaliar é utilizado para dar uma nota para uma midia. Caso o cliente já tenha avaliado essa midia antes, não será feito a nova avaliacao.
	 * 
	 * @param nota, double contendo a nota que deseja ser dada à midia
	 * @param busca, String contendo a midia que deseja ser avaliada
	 */
	public void avaliar(double nota, String busca) {
		ArrayList<Midia> midiaAvaliada = buscarGeral(busca, "geral");
		ArrayList<Midia> verificacao = clienteLogado.getListaAvaliados();
		if(!verificacao.contains(midiaAvaliada.get(0))){
			midiaAvaliada.get(0).availiar(nota);
		}
	}

}

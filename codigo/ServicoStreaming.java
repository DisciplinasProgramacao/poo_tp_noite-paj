package projetinho;

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
	 * Metodo usado para cadastrar um novo cliente no sistema e depois salva-lo na lista de clientes
	 * 
	 * @param nome, string que carrega o nome do cliente
	 * @param usuario, string que carrega o usuario escolhido pelo cliente
	 * @param senha, string que carrega a senha escolhida pelo cliente
	 */
	public void cadastrar(String nome, String usuario, String senha) {
		Cliente cliente = new Cliente(nome, usuario, senha);
		listaCliente.add(cliente);
	}

	/**
	 * Metodo usado para remover um cliente do sistema, apos informar o usuario e a senha, será realizado a verificacao
	 * se a senha e o usuario informados coincidem. Se for verdadeiro, removera o cliente da lista, se não, não removerá da lista
	 * 
	 * @param usuario, recebe uma string contendo o usuario que deseja ser removido
	 * @param senha, recebe uma string contendo a senha do usuario que deseja ser removido
	 */
	public void removerCliente(String usuario, String senha) {
		for(Cliente cliente : listaCliente) {
			if(cliente.getUsuario().equals(usuario)) {
				if(cliente.logar(senha)) {
					listaCliente.remove(cliente);	
				}
			}
		}
	}

	/**
	 * O metodo buscarGeral serve para buscar a midia desejada nas listas de midia tanto do sistema, 
	 * quanto do usuario, dependerá da escolha do cliente em qual lista ele deseja buscar a midia.
	 * Caso o cliente tenha digitado o usuario ou a senha errada, a lista irá retornar nula e sera avisado na tela que ele digitou algo errado
	 * 
	 * @param usuario, recebe uma string contendo o usuario do cliente
	 * @param senha, recebe uma string contendo a senha do usuario do cliente
	 * @param busca, recebe uma string contendo o que o usuario deseja buscar
	 * @param opcao, recebe a opcao da lista que deseja buscar ("geral" para a lista de midias do sistema
	 * 															"assistir" para a lista de midias para assistir futuramente do cliente
	 * 															"assistidos" para a lista de midias assistidas do cliente)
	 * @return ArrayList<Midia>, retorna uma ArrayList de midias que contem no nome a string buscada pelo usuário. Retornara nulo caso não seja encontrado nenhuma midia ou o usuario/senha estejam errados
	 */
	public ArrayList<Midia> buscarGeral(String usuario, String senha, String busca, String opcao) {
		for (Cliente cliente : listaCliente) {
			if (cliente.getUsuario().equals(usuario)) {
				if (cliente.logar(senha)) {
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

					case "assistir" :
						return cliente.buscarLista(busca, opcao);
					
					case "assistidos" :
						return cliente.buscarLista(busca, opcao);
					}
				}
			}
		}
		System.out.println("Login/Senha inválidos");
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
	 * O metodo recebera um arquivo e realizara a leitura do mesmo, desde que contenha no titulo (series, espectadores, audiencia ou filmes)
	 * Sua funcionalidade é de identificar o tipo de objeto entre cliente,filme ou serie e a audiência de ambas.
	 * 
	 * @param caminho, recebe uma string contendo o caminho do arquivo + o nome do mesmo.
	 */
	public void lerArquivo(String caminho) {
		try {
			Random gerador = new Random();
			FileReader arq = new FileReader(caminho);
			BufferedReader lerarq = new BufferedReader(arq);

			String linha = lerarq.readLine();
			if (caminho.contains("Espectadores")) {
				while (linha != null) {
					String[] linhaPartida = linha.split(";");
					cadastrar(linhaPartida[0], linhaPartida[1], linhaPartida[2]);
					linha = lerarq.readLine();
				}
			}
			else if (caminho.contains("Series")) {
				while (linha != null) {
					String[] linhaPartida = linha.split(";");

					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate dataLancamento = LocalDate.parse(linhaPartida[2], formato);

					ArrayList<String> idiomas = new ArrayList<>();
					idiomas.add(idioma.get(gerador.nextInt(2)));

					ArrayList<String> generos = new ArrayList<>();
					generos.add(genero.get(gerador.nextInt(4)));
					
					Midia serie = new Serie(linhaPartida[0], linhaPartida[1], dataLancamento,idiomas,generos,gerador.nextInt(12));
					adicionarMidia(serie);
					linha = lerarq.readLine();
				}
			}
			else if (caminho.contains("Audiencia")) {
				while (linha != null) {
					String[] linhaPartida = linha.split(";");

					for (Midia midia : listaMidia) {
						if (midia.getIdMidia().equals(linhaPartida[2])) {
							for (Cliente cliente : listaCliente) {
								if (cliente.getUsuario().equals(linhaPartida[0])) {
									cliente.adicionar(linhaPartida[1], midia);
								}
							}
						}
					}
					linha = lerarq.readLine();
				}
			}
			else if (caminho.contains("Filmes")) {
				linha = lerarq.readLine();
				while (linha != null) {
					String[] linhaPartida = linha.split(";");
					
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate dataLancamento = LocalDate.parse(linhaPartida[2], formato);

					ArrayList<String> idiomas = new ArrayList<>();
					idiomas.add(idioma.get(gerador.nextInt(2)));

					ArrayList<String> generos = new ArrayList<>();
					generos.add(genero.get(gerador.nextInt(4)));
					
					int duracao = Integer.parseInt(linhaPartida[3]);
					
					Midia filme = new Filme(linhaPartida[0], linhaPartida[1], dataLancamento,idiomas,generos,duracao);
					adicionarMidia(filme);
					linha = lerarq.readLine();
				}
			}

			arq.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

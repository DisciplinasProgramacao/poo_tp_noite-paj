import entidades.*;
import excecoes.ClienteInvalidoException;
import excecoes.MidiaNaoEncontradaException;
import excecoes.UsuarioSenhaErradosException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
	static Scanner ent = new Scanner(System.in);

	private static void comentarAssistida(ServicoStreaming servico){
		ent.nextLine();
		List<Midia> midias2 = servico.buscarGeral(null, "assistidas");
		if (!midias2.isEmpty()) {
			for (Midia m : midias2) {
				System.out.println(m.toString());
				System.out.println();
			}

			System.out.println("Qual midia assistida deseja Comentar?");
			String busca = ent.nextLine();
			System.out.println("Qual comentário deseja fazer?");
			String comentario = ent.nextLine();
			servico.comentar(comentario, busca);
		}
	}
	private static void buscarMidia(ServicoStreaming servico){
		ent.nextLine();
		System.out.println("Qual midia deseja buscar?");
		String midia = ent.nextLine();

		System.out.println("Qual lista deseja pesquisar?");
		System.out.println("geral - Lista de midias do serviço");
		System.out.println("assistir - Lista de midias que o usuário deseja ver");
		System.out.println("assistidas - Lista de midias que foram assistidas pelo usuários");
		String opc = ent.nextLine();
		opc.toLowerCase();

		List<Midia> midias = servico.buscarGeral(midia, opc);

		if (midias != null)
			for (Midia m : midias) {
				System.out.println(m.toString());
				System.out.println();
			}

		else
			System.out.println("Não foi encontrado nenhuma midia.");
	}

	private static void avaliarMidiaAssistida(ServicoStreaming servico){
		ent.nextLine();
		List<Midia> midias1 = servico.buscarGeral(null, "assistidas");
		if (!midias1.isEmpty()) {
			for (Midia m : midias1) {
				System.out.println(m.toString());
				System.out.println();
			}


			System.out.println();
			System.out.println("Qual midia assistida deseja avaliar?");
			String busca = ent.nextLine();
			System.out.println("Qual nota deseja dar?");
			double nota = ent.nextDouble();

			try {
				servico.avaliar(nota, busca);
				System.out.println("Midia avaliada");
			} catch (MidiaNaoEncontradaException e2) {
				System.out.println(e2.getMessage());
			} catch (IllegalArgumentException e3) {
				System.out.println(e3.getMessage());
			}
		} else {
			System.out.println("Não foi encontrada nenhuma midia na sua lista de Midias Assistidas!");
		}
	}

	private static void adicionarMidiaAssistidaOuAssistidos(ServicoStreaming servico){
		ent.nextLine();
		System.out.println("Qual midia deseja adicionar?");
		String mi = ent.nextLine();
		System.out.println("Em qual lista deseja colocar?");
		System.out.println("Para assistir depois: (F)");
		System.out.println("Já assistidas: (A)");
		String opca = ent.nextLine();

		try {
			servico.adicionar(opca, mi);
		} catch (MidiaNaoEncontradaException e1) {
			System.out.println(e1.getMessage());
		}

	}

	private static void removeCliente(ServicoStreaming servico, int opcao){
		System.out.println("Tem certeza que deseja remover a conta? S/N");
		char op = ent.next().charAt(0);
		Character.toUpperCase(op);
		if (op == 'S') {
			System.out.println("Digite sua senha:");
			try {
				servico.removerCliente(ent.nextLine());
			} catch (UsuarioSenhaErradosException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Conta removida");
			opcao = 0;
		} else {
			System.out.println("Conta não removida");
		}
	}

	public static void relatoriosSistema(ServicoStreaming servico) {
		int quant;
		int maiorquant = 0;
		Cliente maior = null;
		List<Midia> midia;
		Map<Object, List<Midia>> listaMidia;
		int opcao = 1;
		while (opcao != 0) {
			System.out.println();
			System.out.println("Qual relátorio deseja ver?");
			System.out.println("1 - Qual cliente assistiu mais midias ");
			System.out.println("2 - Qual cliente tem mais avaliações");
			System.out.println("3 - Porcentagem de clientes com pelo menos 15 avaliações");
			System.out.println("4 - Melhores 10 midias com maior visualizações");
			System.out.println("5 - Melhores 10 midias com maior media de avaliação com mais de 100 visualizações");
			System.out.println("6 - Melhores 10 mídias com mais visualizações separadas por gênero");
			System.out.println("7 - Melhores 10 midias com maior media de avaliação com mais de 100 visualizações separadas por gênero");
			System.out.println("0 - Sair");
			opcao = ent.nextInt();
			switch (opcao) {

			case 1:
				maiorquant = 0;
				quant = 0;
				maior = null;
				for (Cliente c : servico.getListaCliente().values()) {
					quant = c.getListaAssistidos().size();
					System.out.println(quant);
					if (quant > maiorquant) {
						maiorquant = quant;
						maior = c;
					}
				}
				System.out.println(quant);
				System.out.println("Cliente com maior quantidade de midias assistidas é: " + maior.getUsuario()
						+ "! \nAssistiu um total de: " + maior.getListaAssistidos().size() + " Midias!");
				break;

			case 2:
				maiorquant = 0;
				quant = 0;
				maior = null;
				for (Cliente c : servico.getListaCliente().values()) {
					quant = c.getListaMidiasAvaliadas().size();
					if (quant > maiorquant) {
						maiorquant = quant;
						maior = c;
					}
				}
				System.out.println("Cliente com maior quantidade de midias assistidas é: " + maior.getUsuario()
						+ "! \nAvaliou um total de: " + maior.getListaMidiasAvaliadas().values().stream().mapToInt(List::size).sum() + " Midias!");
				break;

			case 3:
				maiorquant = 0;
				quant = 0;
				maior = null;
				int quantCliente = 0;
				for (Cliente c : servico.getListaCliente().values()) {
					quant = c.getListaMidiasAvaliadas().size();
					quantCliente++;
					if (quant > 15) {
						maiorquant++;
					}
				}
				int porc = (maiorquant * 100) / quantCliente;
				System.out.println("A porcentagem de clientes com pelo menos 15 avaliações é de: " + porc + "%");
				break;

			case 4:
				midia = new ArrayList<>();
				midia = servico.getListaMidia().values().stream()
						.sorted(Comparator.comparing(Midia::getQuantidadeDeViwers).reversed()).limit(10).toList();
				System.out.println("O top 10 filmes mais assistidos são:");
				for (Midia m : midia) {
					System.out.println(m.getNome() + " - " + m.getQuantidadeDeViwers() + " Visualizações");
				}
				break;

			case 5:
				midia = new ArrayList<>();
				midia = servico.getListaMidia().values().stream().filter(m -> m.getQuantidadeDeViwers() >= 100)
						.sorted(Comparator.comparing(Midia::getAvaliacao).reversed()).limit(10).toList();
				if (!midia.isEmpty()) {
					System.out.println("O top 10 filmes com melhor avaliação são:");
					for (Midia m : midia) {
						System.out.println(m.getNome() + " - " + m.getAvaliacao());
					}
				} else {
					System.out.println("Não há midias com mais de 100 visualizações.");
				}
				break;

			case 6:
				listaMidia = servico.getListaMidia().values().stream()
						.sorted(Comparator.comparing(Midia::getQuantidadeDeViwers).reversed())
						.collect(Collectors.groupingBy(Midia::getGenero));

				Map<String, List<Midia>> listaMidiaPorGenero = new TreeMap<>();
				listaMidia.forEach((genero, mid) -> {
					List<Midia> dezMidiasDoGenero = mid.stream().distinct().limit(10).collect(Collectors.toList());
					listaMidiaPorGenero.put((String) genero, dezMidiasDoGenero);
				});

				listaMidiaPorGenero.forEach((genero, mi) -> {
					System.out.println("Gênero: " + genero);
					System.out.println("Mídias:");
					mi.forEach(m -> System.out.println(m.getNome() + " - " + m.getAvaliacao()));
					System.out.println();
				});
				break;

			case 7:
				listaMidia = servico.getListaMidia().values().stream().filter(Midia -> Midia.getQuantidadeDeViwers() >= 100)
						.sorted(Comparator.comparing(Midia::getAvaliacao).reversed())
						.collect(Collectors.groupingBy(Midia::getGenero));

				Map<String, List<Midia>> listaMidiaPorGenero2 = new TreeMap<>();
				listaMidia.forEach((genero, mid) -> {
					List<Midia> dezMidiasDoGenero = mid.stream().distinct().limit(10).collect(Collectors.toList());
					listaMidiaPorGenero2.put((String) genero, dezMidiasDoGenero);
				});

				if (!listaMidiaPorGenero2.isEmpty()) {
					listaMidiaPorGenero2.forEach((genero, mi) -> {
						System.out.println("Gênero: " + genero);
						System.out.println("Mídias:");
						mi.forEach(m -> System.out.println(m.getNome() + " - " + m.getAvaliacao()));
						System.out.println();
					});
				} else {
					System.out.println("Não há midias com mais de 100 visualizações.");
				}
				break;
			}
		}
	}

	private static void adicionaFilmeGeral(String nome, LocalDate data, String dt, int quantEp, ServicoStreaming servico){
		ent.nextLine();
		System.out.println("Digite o nome da Serie:");
		nome = ent.nextLine();
		System.out.println("Digite a data de lancamento: (dd/mm/aaaa)");
		dt = ent.nextLine();
		if (dt.length() == 10) {
			System.out.println("Digite a quantidade de episodeos:");
			quantEp = ent.nextInt();

			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			data = LocalDate.parse(dt, formato);

			Midia serie = new Serie(nome, data, quantEp);
			servico.adicionarMidia(serie);
			System.out.println("Serie adicionada ao sistema!");
		} else {
			System.out.println("Insira uma data válida!");
		}

	}

	private static void adicionaSerieGeral(String nome, LocalDate data, String dt, int duracao, ServicoStreaming servico){
		ent.nextLine();
		System.out.println("Digite o nome do Filme:");
		nome = ent.nextLine();
		System.out.println("Digite a data de lancamento: (dd/mm/aaaa)");
		dt = ent.nextLine();
		if (dt.length() == 10) {
			System.out.println("Digite a duração:");
			duracao = ent.nextInt();

			DateTimeFormatter formato1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			data = LocalDate.parse(dt, formato1);

			Midia filme = new Filme(nome, data, duracao);
			servico.adicionarMidia(filme);
			System.out.println("Filme adicionado ao sistema!");
		} else {
			System.out.println("Insira uma data válida!");
		}
		
	}
	public static void adicionarMidia(ServicoStreaming servico) {
		int opcao = 1;
		String nome = null;
		LocalDate data = null;
		String dt = null;
		int quantEp = 0;
		int duracao = 0;
		while (opcao != 0) {
			System.out.println("Qual midia deseja adicionar?");
			System.out.println("1 - Serie");
			System.out.println("2 - Filme");
			System.out.println("0 - Sair");
			opcao = ent.nextInt();

			switch (opcao) {
			case 1:
				adicionaFilmeGeral(nome, data,dt, quantEp, servico);
				break;
			case 2:
				adicionaSerieGeral(nome, data,dt, duracao, servico);
				break;
			}
		}
	}

	public static void logado(ServicoStreaming servico, Scanner ent) {
		int opcao = 1;
		while (opcao != 0) {
			System.out.println();
			System.out.println("1 - Buscar Midia");
			System.out.println("2 - Avaliar Midia Assistida");
			System.out.println("3 - Comentar em uma Midia Assistida");
			System.out.println("4 - Adicionar Midia a lista assistir/assistidos");
			System.out.println("5 - Relátorios do sistema");
			System.out.println("6 - Adicionar Midia");
			System.out.println("7 - Deslogar");
			System.out.println("8 - Remover Cliente");

			opcao = ent.nextInt();

			switch (opcao) {

				case 1:
					buscarMidia(servico);
					break;

				case 2:
					avaliarMidiaAssistida(servico);
					break;

				case 3:
					comentarAssistida(servico);
					break;

				case 4:
					adicionarMidiaAssistidaOuAssistidos(servico);
					break;

				case 5:
					relatoriosSistema(servico);
					break;

				case 6:
					adicionarMidia(servico);
					break;

				case 7:
					servico.deslogar();
					System.out.println("Cliente deslogado!");
					opcao = 0;
					break;

				case 8:
					removeCliente(servico, opcao);
					break;
			}
		}

	}
	public static void main(String[] args) {

		String usuario = null;
		String senha = null;
		int opcao = 1;

		ServicoStreaming servico = new ServicoStreaming();
		servico.lerArquivo();

		while (opcao != 0) {
			System.out.println("Menu:");
			System.out.println("1 - Logar");
			System.out.println("2 - Cadastrar");
			System.out.println("0 - Sair");

			opcao = ent.nextInt();

			switch (opcao) {
			case 1:
				loginUsuario(usuario, senha, servico);
				break;
			case 2:
				cadastraUsuario(usuario, senha, servico);
				break;

			}
		}
	}
	public static void loginUsuario(String usuario, String senha, ServicoStreaming servico){
		ent.nextLine();
		System.out.println("Digite o usuário:");
		usuario = ent.nextLine();
		System.out.println("Digite a senha:");
		senha = ent.nextLine();
		try {
			servico.logar(usuario, senha);
			System.out.println("Usuário logado com sucesso!");
			logado(servico, ent);
		} catch (UsuarioSenhaErradosException e) {
			System.out.println(e.getMessage());
		}

	}
	public static void cadastraUsuario(String usuario, String senha, ServicoStreaming servico){
		Cliente cliente;
		ent.nextLine();
		System.out.println("Digite seu nome: (minimo de 3 letras!)");
		String nome = ent.nextLine();
		System.out.println("Digite o usuário: (minimo de 4 letras!)");
		usuario = ent.nextLine();
		System.out.println("Digite a senha: (minimo de 4 letras!)");
		senha = ent.nextLine();
		System.out.println("É cliente profissional? (sim / nao)");
		String tc = ent.nextLine();
		switch (tc.toLowerCase()) {
			case "sim":
				cliente = new ClienteProfissional(nome, usuario, senha);
				try {
					verificaCliente(cliente);
					servico.cadastrar(cliente);
					System.out.println("Usuário cadastrado com sucesso!");
				} catch (ClienteInvalidoException e) {
					System.out.println(e.getMessage());
				}

				break;

			case "nao":
				cliente = new Cliente(nome, usuario, senha);
				try {
					verificaCliente(cliente);
					servico.cadastrar(cliente);
					System.out.println("Usuário cadastrado com sucesso!");
				} catch (ClienteInvalidoException e) {
					System.out.println(e.getMessage());
				}
				break;

			default:
				System.out.println("Erro! Opção inválida, por favor escolher sim ou nao!");
				System.out.println("Cliente não cadastrado!");
		}

	}
	
	public static void verificaCliente(Cliente cliente) throws ClienteInvalidoException {
		if(cliente.getNome().length() < 3 || cliente.getSenha().length() < 4 || cliente.getUsuario().length() < 4) {
			throw new ClienteInvalidoException("Informações inválidas. Favor seguir as restrições de cadastro!");
		}
	}
}

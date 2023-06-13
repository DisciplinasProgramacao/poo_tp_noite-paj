
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static void logado(ServicoStreaming servico, Scanner ent) {
		int opcao = 1;
		while (opcao != 3 || opcao != 4) {
			System.out.println("1 - Buscar Midia");
			System.out.println("2 - Avaliar Midia");
			System.out.println("3 - Deslogar");
			System.out.println("4 - Remover Cliente");

			opcao = ent.nextInt();
			
			switch(opcao) {
			
			case 1:
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
				if(midias != null) System.out.println(midias.toString());
				else System.out.println("Não foi encontrado nenhuma midia.");
				break;
				
			case 2:
				ent.nextLine();
				List<Midia> midias1 = servico.buscarGeral(null,"assistidas");
				System.out.println(midias1.toString());
				System.out.println("Qual midia assistida deseja avaliar?");
				String busca = ent.nextLine();
				System.out.println("Qual nota deseja dar?");
				double nota = ent.nextDouble();
				servico.avaliar(nota,busca);
				System.out.println("Midia avaliada");

				
				break;
				
			case 3:
				servico.deslogar();
				System.out.println("Cliente deslogado!");
				opcao = 0;
				break;
				
			case 4:
				System.out.println("Tem certeza que deseja remover a conta? S/N");
				char op = ent.next().charAt(0);
				Character.toUpperCase(op);
				if(op == 'S') {
					System.out.println("Digite sua senha:");
					try {
						servico.removerCliente(ent.nextLine());
					} catch (UsuarioSenhaErradosException e) {
						System.out.println(e.getMessage());
					}
					System.out.println("Conta removida");
					opcao = 0;
				}else {
					System.out.println("Conta não removida");
				}
				break;
				}	
			}

		}

	public static void main(String[] args) {
		Scanner ent = new Scanner(System.in);

		String usuario;
		String senha;
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
				ent.nextLine();
				System.out.println("Digite o usuário:");
				usuario = ent.nextLine();
				ent.nextLine();
				System.out.println("Digite a senha:");
				senha = ent.nextLine();
				try {
					servico.logar(usuario, senha);
					System.out.println("Usuário logado com sucesso!");
					logado(servico, ent);
				} catch (UsuarioSenhaErradosException e) {
					System.out.println(e.getMessage());
				}

			case 2:
				ent.nextLine();
				System.out.println("Digite seu nome:");
				String nome = ent.nextLine();
				System.out.println("Digite o usuário:");
				usuario = ent.nextLine();
				System.out.println("Digite a senha:");
				senha = ent.nextLine();

				servico.cadastrar(nome, usuario, senha);
				System.out.println("Usuário cadastrado com sucesso!");
				break;

			}
		}
		
	}
}
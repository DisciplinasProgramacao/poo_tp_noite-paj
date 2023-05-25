package poo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
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
			System.out.println("3 - Remover Cliente");
			System.out.println("4 - Buscar Midia");
			System.out.println("5 - Deslogar");
			System.out.println("0 - Sair");

			opcao = ent.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Digite o usuário:");
				usuario = ent.nextLine();
				ent.nextLine();
				System.out.println("Digite a senha:");
				senha = ent.nextLine();
				if(servico.logar(usuario, senha)) {
					System.out.println("Usuário logado com sucesso!");
				}else System.out.println("Usuario ou senha errado!");
				break;
			
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
				
			case 3:
				System.out.println("Tem certeza que deseja remover a conta? S/N");
				char op = ent.next().charAt(0);
				Character.toUpperCase(op);
				if(op == 'S') {
					servico.removerCliente();
					System.out.println("Conta removida");
				}else {
					System.out.println("Conta não removida");
				}
				break;
				
			case 4:
				ent.nextLine();
				System.out.println("Qual midia deseja buscar?");
				String midia = ent.nextLine();
				
				System.out.println("Qual lista deseja pesquisar?");
				System.out.println("geral - Lista de midias do serviço");
				System.out.println("assistir - Lista de midias que o usuário deseja ver");
				System.out.println("assistidas - Lista de midias que foram assistidas pelo usuários");
				String opc = ent.nextLine();
				
				opc.toLowerCase();
				
				ArrayList<Midia> midias = servico.buscarGeral(midia, opc);
				if(!midias.isEmpty()) {
					for (Midia nomes : midias) {
						System.out.println(nomes.getNome());
					}
				}
				else {
					System.out.println("Nao foi encontrado essa midia!");
				}
				break;
				
			case 5:
				servico.deslogar();
				System.out.println("Cliente deslogado!");
				break;
				}	
		}
		ent.close();
	}

}

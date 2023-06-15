
public class ClienteProfissional extends Cliente{

	protected boolean podeAssistir = true;
	
	public ClienteProfissional(String nome, String usuario, String senha) {
		super(nome, usuario, senha);
	}
	
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

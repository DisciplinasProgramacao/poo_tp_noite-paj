import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServicoStreamingTest {
    private Cliente cliente;
    private ServicoStreaming servico;

    /**
     * @BeforeAll para cadastrar e logar um cliente antes de cada teste
     */
    @BeforeAll
    public void beforeAll() throws ClienteInvalidoException, UsuarioSenhaErradosException {
        String nome = "Ana";
        String usuario = "annalu";
        String senha = "1234";
        cliente = new Cliente(nome, usuario, senha);
        servico = new ServicoStreaming();
        servico.cadastrar(cliente);
        servico.logar(usuario, senha );
    }

    /**
     * @Test verificando se logou corretamente o cliente no beforeAll acima
     */
    @org.junit.jupiter.api.Test
    void logar() throws ClienteInvalidoException, UsuarioSenhaErradosException {
        Assert.assertTrue(servico.getClienteLogado() == cliente);
    }

    /**
     * @Test para cadastrar um cliente
     */
    @org.junit.jupiter.api.Test
    void cadastrar() throws ClienteInvalidoException {
        String nome = "Ana";
        String usuario = "annalu";
        String senha = "1234";
        Cliente cliente = new Cliente(nome, usuario, senha);
        ServicoStreaming servico = new ServicoStreaming();
        servico.cadastrar(cliente);
        Assert.assertEquals(1, servico.getListaCliente().size());

    }
    /**
     * @Test para remover um cliente do serviço streaming
     */
    @org.junit.jupiter.api.Test
    void removerCliente() throws UsuarioSenhaErradosException {
        servico.removerCliente("1234");
        Assert.assertEquals(0, servico.getListaCliente().size());
    }
    /**
     * @Test para buscar uma midia na lista geral
     */
    @org.junit.jupiter.api.Test
    void buscarGeral() {
        String busca = "filme";
        String opcao = "geral";
        List<Midia> resultados = servico.buscarGeral(busca, opcao);
        Assertions.assertNotNull(resultados);
        Assertions.assertTrue(resultados.isEmpty());
    }
    /**
     * @Test para buscar uma midia na lista de assistir
     */
    @org.junit.jupiter.api.Test
    void buscarAssistir() {
        String busca = "série";
        String opcao = "assistir";
        List<Midia> resultados = servico.buscarGeral(busca, opcao);
        Assertions.assertNotNull(resultados);
        Assertions.assertTrue(resultados.isEmpty());
    }
    /**
     * @Test para buscar uma midia na lista de assistidos
     */
    @org.junit.jupiter.api.Test
    void buscarAssistidos() {
        String busca = "documentário";
        String opcao = "assistidas";
        List<Midia> resultados = servico.buscarGeral(busca, opcao);
        Assertions.assertNotNull(resultados);
        Assertions.assertTrue(resultados.isEmpty());
    }
    /**
     * @Test para deslogar um cliente
     */
    @org.junit.jupiter.api.Test
    void deslogar() {
        servico.deslogar();
        Cliente clienteLogado = servico.getClienteLogado();
        Assertions.assertNull(clienteLogado);
    }

}
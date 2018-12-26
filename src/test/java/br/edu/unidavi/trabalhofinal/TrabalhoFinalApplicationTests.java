package br.edu.unidavi.trabalhofinal;

import br.edu.unidavi.trabalhofinal.entitys.Cliente;
import br.edu.unidavi.trabalhofinal.entitys.Produto;
import br.edu.unidavi.trabalhofinal.repositorys.ClienteRepository;
import br.edu.unidavi.trabalhofinal.repositorys.ProdutoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrabalhoFinalApplicationTests {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Test
	public void contextLoads() {
	}

	//Realiza a criação de um novo registro (Cliente)
	@Test
	public void createCliente() throws Exception {
		SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
		Date data = formatador.parse("01-01-2018");

		Cliente cliente = new Cliente();
		cliente.setNome("User Test");
		cliente.setEmail("test@email.com");
		cliente.setCpf("987654321");
		cliente.setDataNascimento(data);

		cliente = clienteRepository.save(cliente);

		assertNotNull(cliente);
		assertTrue(cliente.getId() != null);
	}

	//Faz uma busca de Clientes no estado de SC - O retorno deve ser maior que 0 (zero)
	@Test
	public void getEnderecoByEstado() {
		List<Cliente> clientes = clienteRepository.findClienteByEstado("SC");

		assertTrue(clientes.size() > 0);
	}

	//Cria um novo produto, em seguida realiza sua exclusão
	//Após a exclusão, a busca pelo ID do produto deve retornar nulo
	@Test
	public void deletaProduto() {
		Produto produto = new Produto();
		produto.setNome("REFRIGERANTE");
		produto.setDescricao("REFR PEPSI 2 LTS");
		produto.setMarca("PEPSI");
		produto.setValor(6.50);

		produto = produtoRepository.save(produto);

		produtoRepository.delete(produto.getId());

		Produto produtoExcluido = produtoRepository.findOne(produto.getId());

		assertNull(produtoExcluido);
	}
}
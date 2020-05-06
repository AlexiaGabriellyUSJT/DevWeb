package test;


import static org.junit.Assert.assertEquals;
import model.Cliente;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import service.ClienteService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {
	Cliente cliente, copia;
	ClienteService clienteService;
	static int id = 0;

	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
	 * linha com o id igual ao do escolhido para o to instanciado abaixo. Se
	 * houver, delete. 
	 * Certifique-se também que sobrecarregou o equals na classe
	 * Cliente. 
	 * Certifique-se que a fixture cliente1 foi criada no banco.
	 * Além disso, a ordem de execução dos testes é importante; por
	 * isso a anotação FixMethodOrder logo acima do nome desta classe
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome("Indonesia");
		cliente.setPopulacao("435");
		cliente.setArea("435");
		copia = new Cliente();
		copia.setId(id);
		copia.setNome("Indonesia");
		copia.setFone("435");
		copia.setEmail("435");
		clienteService = new ClienteService();
		System.out.println(cliente);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("carregar");
		//para funcionar o cliente 1 deve ter sido carregado no banco por fora
		Cliente fixture = new Cliente();
		fixture.setId(1);
		fixture.setNome("Indonesia");
		fixture.setPopulacao("435");
		fixture.setArea("435");
		ClienteService novoService = new ClienteService();
		Cliente novo = novoService.carregar(1);
		assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Criar() {
		System.out.println("criar");
		id = clienteService.criar(pais);
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", pais, copia);
	}

	@Test
	public void test02Atualizar() {
		System.out.println("atualizar");
		cliente.setPopulacao("999999");
		copia.setPopulacao("999999");		
		clienteService.atualizar(pais);
		cliente = clienteService.carregar(pais.getId());
		assertEquals("testa atualizacao", pais, copia);
	}

	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(null);
		copia.setArea(null);
		clienteService.excluir(id);
		cliente = clienteService.carregar(id);
		assertEquals("testa exclusao", pais, copia);
	}
}
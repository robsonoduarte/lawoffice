/**
 *
 */
package br.com.lawoffice.dados;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Custa;
import br.com.lawoffice.dominio.Lancamento;
import br.com.lawoffice.dominio.Pessoa;
import br.com.lawoffice.persistencia.CustaDao;
import br.com.lawoffice.persistencia.LancamentoDao;
import br.com.lawoffice.persistencia.PessoaDao;

/**
 *
 * Classe de teste de unidade para {@link DadosBaseBean}.
 *
 * @author robson
 *
 */
public class PessoaServiceBeanTest {


	@Mock
	private PessoaDao pessoaDao;


	@Mock
	private LancamentoDao lancamentoDao;


	@Mock
	private CustaDao custaDao;


	@InjectMocks
	PessoaServiceBean pessoaServiceBean;


	@Before
	public void setUp(){
		pessoaServiceBean = new PessoaServiceBean();
		MockitoAnnotations.initMocks(this);
	}


	@After
	public void tearDown(){
		pessoaServiceBean =null;
		reset(pessoaDao);
	}


	@Test(expected=IllegalArgumentException.class)
	public void deveLancarUmaExcecaoQuandoClassNulaQuandoPesquisaNome() {
		pessoaServiceBean.listarPorNome(null, "robson");
	}


	@Test(expected=IllegalArgumentException.class)
	public void deveLancarUmaExcecaoQuandoNomeNuloQuandoPesquisaNome() {
		pessoaServiceBean.listarPorNome(Pessoa.class, null);
	}


	@Test()
	public void deveRetornaUmaListaDePessoaPeloNomePassado() {

		when(
			pessoaDao.listarPorNome(Pessoa.class, "nome")
		).thenReturn(new ArrayList<Pessoa>());


		List<Pessoa> pessoas =
			pessoaServiceBean.listarPorNome(Pessoa.class, "robson");

		assertNotNull(pessoas);
	}


	@Test(expected=IllegalArgumentException.class)
	public void deveLancarUmaExcecaoQuandoEntidadeNulaParaAtualizar() {
		pessoaServiceBean.atualizar(null);
	}


	@Test()
	public void deveRetonarUmaEntidadeQuandoAtualizada() {

		// pessoa a atualizar
		Pessoa pessoaAtualizar = new Pessoa();
		pessoaAtualizar.setNome("Robson");

		// pessoa atualizada
		Pessoa pessoaAtualizada = new Pessoa();
		pessoaAtualizada.setNome("Robson Oliveira Duarte");

		when(
			pessoaDao.atualizar(pessoaAtualizar)
		).thenReturn(pessoaAtualizada);


		assertEquals("Robson Oliveira Duarte", pessoaServiceBean.atualizar(pessoaAtualizar).getNome());
	}


	@Test(expected=IllegalArgumentException.class)
	public void deveLancaExcecaoQuandoClassNulaParaRemover() {
		pessoaServiceBean.remover(null, new Colaborador());
	}


	@Test(expected=IllegalArgumentException.class)
	public void deveLancaExcecaoQuandoEntidadeNulaParaRemover() {
		pessoaServiceBean.remover(Pessoa.class, null);
	}




	@Test()
	public void deveRemoverAEntidadePassada() {

		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		lancamentos.add(new Lancamento().addCusta(new Custa()));

		Cliente cliente = new Cliente();

		when(lancamentoDao.getLancamentos(cliente)).thenReturn(lancamentos);

		pessoaServiceBean.remover(Pessoa.class, cliente);

		verify(lancamentoDao).getLancamentos(cliente);
	}



	@Test(expected=IllegalArgumentException.class)
	public void deveLancaExcecaoQuandoClassNulaParaLocalizar() {
		pessoaServiceBean.localizar(null, new Pessoa());
	}





	@Test(expected=IllegalArgumentException.class)
	public void deveLancaExcecaoQuandoEntidadeNulaParaLocalizar() {
		pessoaServiceBean.localizar(Pessoa.class, null);
	}




	@Test()
	public void deveLocalizarAEntidadePassada() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1l);

		when(
			pessoaDao.localizar(Pessoa.class, pessoa)
		).thenReturn(new Pessoa());

		pessoa = pessoaServiceBean.localizar(Pessoa.class, pessoa);

		assertNotNull(pessoa);
	}





	@Test(expected=IllegalArgumentException.class)
	public void deveLancaExcecaoQuandoEntidadeNulaParaSalvar() {
		pessoaServiceBean.salvar(null);
	}




	@Test()
	public void deveSalvarEntidadePassada() {

		Pessoa pessoa = new Pessoa();

		when(
			pessoaDao.salvar(pessoa)
		).thenReturn(new Pessoa());

		pessoa = pessoaServiceBean.salvar(pessoa);

		assertNotNull(pessoa);

	}



	@Test(expected=IllegalArgumentException.class)
	public void deveLancaExcecaoQuandoClassNulaParaListar() {
		pessoaServiceBean.listar(null);
	}


	@Test()
	public void deveListarComClassPassado() {

		when(
			pessoaDao.listar(Pessoa.class)
		).thenReturn(new ArrayList<Pessoa>());

		List<Pessoa> pessoas = pessoaServiceBean.listar(Pessoa.class);

		assertNotNull(pessoas);
	}
}

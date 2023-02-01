package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto p1 = produtoDao.buscarPorId(1l);
		Produto p2 = produtoDao.buscarPorId(2l);
		Produto p3 = produtoDao.buscarPorId(3l);
		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cl1 = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();		
		
		Pedido pd1 = new Pedido(cl1);		
		pd1.adicionarItem(new ItemPedido(10, pd1, p1));		
		pd1.adicionarItem(new ItemPedido(40, pd1, p2));
		
		Pedido pd2 = new Pedido(cl1);		
		pd1.adicionarItem(new ItemPedido(2, pd2, p3));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pd1);
		pedidoDao.cadastrar(pd2);

		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("Valor Total: " + totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
	}

	/*Código copiado da classe CadastroDeProduto apenas para ganhar tempo no teste 
	e por estar sendo usado um bando de dados em memória*/
	private static void popularBancoDeDados() {
		Categoria c1 = new Categoria("celulares");
		Categoria c2 = new Categoria("videogames"); 
		Categoria c3 = new Categoria("informatica");		
		
		Produto p1 = new Produto(
				"Xiaomi Redmi", "8 Gb RAM, 64 Gb",  new BigDecimal("800"), c1);
		Produto p2 = new Produto(
				"PS5", "Playstation 5", new BigDecimal("5500"), c2);
		Produto p3 = new Produto(
				"Macbook", "Macbook top", new BigDecimal("8500"), c3);
		
		Cliente cl1 = new Cliente("ClienteTeste", "123456");
		
		EntityManager em = JPAUtil.getEntityManager();
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(c1);
		categoriaDao.cadastrar(c2);
		categoriaDao.cadastrar(c3);
		produtoDao.cadastrar(p1);
		produtoDao.cadastrar(p2);
		produtoDao.cadastrar(p3);
		clienteDao.cadastrar(cl1);
		em.getTransaction().commit();
		em.close();
	}
}

package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {		
		cadastrarProduto();
		Long id = 1l;
		EntityManager em = JPAUtil.getEntityManager();		
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(id);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarTodos();
		todos.forEach(prods -> System.out.println(p.getNome()));
		
		List<Produto> xiaomi = produtoDao.buscarPorNome("Xiaomi Redmi");
		xiaomi.forEach(prods -> System.out.println(p.getNome()));
		
		List<Produto> celulares = produtoDao.buscarPorCategoria("CELULARES");
		celulares.forEach(prods -> System.out.println(p.getNome()));
		
		BigDecimal precoProduto = produtoDao.buscarPrecoProduto("Xiaomi Redmi");
		System.out.println("Preco do produto: " + precoProduto);
	}

	private static void cadastrarProduto() {
		Categoria c1 = new Categoria("celulares");
		Produto p1 = new Produto(
				"Xiaomi Redmi", "8 Gb RAM, 64 Gb",  new BigDecimal("800"), c1);
		
		EntityManager em = JPAUtil.getEntityManager(); //***
		
		CategoriaDao categoriaDao = new CategoriaDao(em); //***
		ProdutoDao produtoDao = new ProdutoDao(em); //***
		
		//precisa ser usado devido à propriedade javax.persistence.jdbc.driver estar com valro "RESOURCE_LOCAL"
		em.getTransaction().begin(); //***
		categoriaDao.cadastrar(c1);
		produtoDao.cadastrar(p1);
		em.getTransaction().commit(); //***
		em.clear(); //***
		
		//*** essas linhas não seriam escritas em uma aplicação real com uso de algum framework
	}
}

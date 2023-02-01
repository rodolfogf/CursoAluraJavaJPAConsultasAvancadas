package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadastrar(Produto p) {		
		this.em.persist(p);			
	}
	
	public void atualizar(Produto p) {		
		this.em.merge(p);			
	}
	
	public void remover(Produto p) {		
		p = em.merge(p);		
		this.em.remove(p);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome){
		//aqui nos dois pontos é o nome da entidade, do atributo, não da coluna
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		//String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				//.setParameter(1, nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorCategoria(String nomeCategoria){
		//String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
				.setParameter("nome", nomeCategoria)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoProduto(String nome){
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
}

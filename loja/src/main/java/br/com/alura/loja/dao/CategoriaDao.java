package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {
	
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria c) {		
		this.em.persist(c);			
	}
	
	public void atualizar(Categoria c) {
		this.em.merge(c); //serve apenas para o caso da entidade estar no estado DETACHED
	}
	
	public void remover(Categoria c) {
		c = em.merge(c);
		this.em.remove(c);
	}
}

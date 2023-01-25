package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {
	
	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente c) {		
		this.em.persist(c);			
	}

	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	}
}

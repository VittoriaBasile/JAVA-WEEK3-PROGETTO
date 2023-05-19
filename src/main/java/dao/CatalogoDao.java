package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import lombok.extern.slf4j.Slf4j;
import progetto.catalogo.Catalogo;
import progetto.catalogo.Libro;

@Slf4j
public class CatalogoDao {
	private final EntityManager em;

	public CatalogoDao(EntityManager em) {
		this.em = em;
	}

	public void save(Catalogo e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Catalogo salvato!");

	}

	public Catalogo getById(UUID id) {

		Catalogo found = em.find(Catalogo.class, id);
		return found;
	}

	public void delete(UUID id) {
		Catalogo found = em.find(Catalogo.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Catalogo con ISBN " + id + " eliminato!");
		} else {
			log.info("Catalogo con ISBN " + id + " non trovato!");
		}
	}

	public List<Catalogo> getByYear(int year) {
		TypedQuery<Catalogo> query = em.createNamedQuery("findByYear", Catalogo.class);
		query.setParameter("anno", year);

		return query.getResultList();

	}

	public List<Libro> getByAuthor(String author) {
		TypedQuery<Libro> query = em.createNamedQuery("findByAuthor", Libro.class);
		query.setParameter("autore", author);

		return query.getResultList();

	}

	public List<Catalogo> getByTitle(String title) {
		TypedQuery<Catalogo> query = em.createNamedQuery("findByTitle", Catalogo.class);
		query.setParameter("titolo", title);

		return query.getResultList();

	}
}

package dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import lombok.extern.slf4j.Slf4j;
import progetto.prestito.Prestito;

@Slf4j
public class PrestitoDao {
	private final EntityManager em;

	public PrestitoDao(EntityManager em) {
		this.em = em;
	}

	public void save(Prestito e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Prestito salvato!");

	}

	public Prestito getById(UUID id) {

		Prestito found = em.find(Prestito.class, id);
		return found;
	}

	public void delete(UUID id) {
		Prestito found = em.find(Prestito.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Prestito con id " + id + " eliminato!");
		} else {
			log.info("Prestito con id " + id + " non trovato!");
		}
	}

	public List<Prestito> getByNumeroDiTessera(UUID numeroTessera) {
		TypedQuery<Prestito> query = em.createNamedQuery("findByNumeroTessera", Prestito.class);
		query.setParameter("numeroTessera", numeroTessera);

		return query.getResultList();

	}

	public List<Prestito> getPrestitiScadutiENonRestituiti() {
		TypedQuery<Prestito> query = em.createNamedQuery("findPrestitiScadutiNonRestituiti", Prestito.class);
		query.setParameter("oggi", LocalDate.now());
		return query.getResultList();
	}

}

package progetto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.CatalogoDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import lombok.extern.slf4j.Slf4j;
import progetto.catalogo.Catalogo;
import progetto.catalogo.Libro;
import progetto.catalogo.Periodicità;
import progetto.catalogo.Rivista;
import progetto.prestito.Prestito;
import progetto.prestito.Utente;
import utils.JpaUtil;

@Slf4j
public class Application {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		CatalogoDao cd = new CatalogoDao(em);
		PrestitoDao pd = new PrestitoDao(em);
		UtenteDao ud = new UtenteDao(em);
		Catalogo libro1 = new Libro("HarryPotter", 2001, 400, "Fantasy", "J.K.Rowling");
		Catalogo libro2 = new Libro("HarryPotter2", 2002, 400, "Fantasy", "J.K.Rowling");

		Catalogo rivista1 = new Rivista("Vogue", 2022, 50, Periodicità.MENSILE);
		Catalogo rivista2 = new Rivista("Vogue", 2022, 50, Periodicità.MENSILE);

		Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1980, 9, 16));
		Utente utente2 = new Utente("Maria", "Rossi", LocalDate.of(1967, 1, 29));
		Utente utente3 = new Utente("Giovanni", "Bianchi", LocalDate.of(1992, 11, 17));
		Utente utente4 = new Utente("Giovanna", "Bianchi", LocalDate.of(1991, 11, 17));

		Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.of(2023, 3, 17), LocalDate.of(2023, 3, 30));
		Prestito prestito2 = new Prestito(utente2, libro2, LocalDate.of(2023, 4, 17), LocalDate.of(2023, 4, 30));
		Prestito prestito3 = new Prestito(utente3, rivista1, LocalDate.of(2023, 5, 2), LocalDate.of(2023, 5, 18));
		Prestito prestito4 = new Prestito(utente4, rivista1, LocalDate.of(2023, 5, 2));
		Prestito prestito5 = new Prestito(utente4, rivista2, LocalDate.of(2023, 4, 2));

		cd.save(libro1);
		cd.save(libro2);
		cd.save(rivista1);
		cd.save(rivista2);

		ud.save(utente1);
		ud.save(utente2);
		ud.save(utente3);
		ud.save(utente4);

		pd.save(prestito1);
		pd.save(prestito2);
		pd.save(prestito3);
		pd.save(prestito4);
		pd.save(prestito5);

		cd.delete(UUID.fromString("216d34a7-7a44-4c01-b205-66f9de3a7688"));

		log.info(
				"***************************************TROVATO PER ANNO************************************************");
		List<Catalogo> trovatoPerAnno = cd.getByYear(2022);
		if (trovatoPerAnno.size() > 0) {
			trovatoPerAnno.stream().forEach(c -> log.info(c.toString()));
		} else {
			log.info("NESSUN CATALOGO TROVATO SCRITTO NELL' ANNO INDICATO");
		}

		log.info(
				"***************************************TROVATO PER TITOLO************************************************");
		List<Catalogo> trovatoPerTitolo = cd.getByTitle("Harry");
		if (trovatoPerTitolo.size() > 0) {
			trovatoPerTitolo.stream().forEach(c -> log.info(c.toString()));
		} else {
			log.info("NESSUN CATALOGO TROVATO AVENTE IL TITOLO INDICATO");
		}

		log.info(
				"**************************************TROVATO PER AUTORE***************************************************");
		List<Libro> trovatoPerAutore = cd.getByAuthor("J.K.Rowling");
		if (trovatoPerAutore.size() > 0) {
			trovatoPerAutore.stream().forEach(l -> log.info(l.toString()));
		} else {
			log.info("NESSUN LIBRO TROVATO AVENTE L' AUTORE INDICATO");
		}

		log.info(
				"**************************************PRESTITO ATTIVO TROVATO PER NUMERO DI TESSERA***************************************************");
		List<Prestito> trovatoPerNumeroDiTessera = pd
				.getByNumeroDiTessera(UUID.fromString("c944ecba-67f7-4b68-836b-5063e77a8c8f"));
		if (trovatoPerNumeroDiTessera.size() > 0) {
			trovatoPerNumeroDiTessera.stream().forEach(l -> log.info(l.toString()));
		} else {
			log.info("NESSUN PRESTITO TROVATO PER L' UTENTE AVENTE IL NUMERO DI TESSERA INSERITO");
		}

		log.info(
				"**************************************PRESTITO SCADUTO TROVATO***************************************************");
		List<Prestito> trovatoPrestitiScaduti = pd.getPrestitiScadutiENonRestituiti();

		if (trovatoPrestitiScaduti.size() > 0) {
			trovatoPrestitiScaduti.stream().forEach(c -> log.info(c.toString()));
		} else {
			log.info("NESSUN PRESTITO SCADUTO O NON RESTITUITO TROVATO");
		}

		em.close();
		emf.close();

	}

}

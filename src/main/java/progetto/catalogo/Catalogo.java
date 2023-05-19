package progetto.catalogo;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import progetto.prestito.Prestito;

@Entity
@Table(name = "cataloghi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "findByYear", query = "SELECT  c FROM Catalogo c WHERE c.annoPubblicazione = :anno")
@NamedQuery(name = "findByTitle", query = "SELECT c FROM Catalogo c WHERE c.titolo LIKE CONCAT('%', :titolo, '%')")
public abstract class Catalogo {
	@Id
	@GeneratedValue
	private UUID codiceISBN;
	private String titolo;
	private int annoPubblicazione;
	private int numeroPagine;
	@OneToMany(mappedBy = "elementoPrestato", cascade = CascadeType.ALL)

	private Set<Prestito> cataloghiPrestati;

	public Catalogo(String titolo, int annoPubblicazione2, int numeroPagine) {
		setTitolo(titolo);
		setAnnoPubblicazione(annoPubblicazione2);
		setNumeroPagine(numeroPagine);

	}

	@Override
	public abstract String toString();
}

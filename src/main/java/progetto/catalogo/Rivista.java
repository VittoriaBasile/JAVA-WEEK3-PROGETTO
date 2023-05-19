package progetto.catalogo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rivista extends Catalogo {
	@Enumerated(EnumType.STRING)
	private Periodicità periodicità;

	public Rivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicità periodicità) {
		super(titolo, annoPubblicazione, numeroPagine);
		setPeriodicità(periodicità);

	}

	@Override
	public String toString() {
		return "codiceISBN : " + getCodiceISBN() + " , " + "titolo: " + getTitolo() + " , " + "anno di pubblicazione : "
				+ getAnnoPubblicazione() + " , " + "numero di pagine : " + getNumeroPagine() + " , " + "genere : "
				+ "periodicità : " + getPeriodicità() + "\n";
	}

}

package progetto.catalogo;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "findByAuthor", query = "SELECT  l FROM Libro l WHERE l.autore = :autore")

public class Libro extends Catalogo {

	private String genere;
	private String autore;

	public Libro(String titolo, int annoPubblicazione, int numeroPagine, String genere, String autore) {
		super(titolo, annoPubblicazione, numeroPagine);
		setGenere(genere);
		setAutore(autore);

	}

	@Override
	public String toString() {
		return "codiceISBN : " + getCodiceISBN() + " , " + "titolo: " + getTitolo() + " , " + "anno di pubblicazione : "
				+ getAnnoPubblicazione() + " , " + "numero di pagine : " + getNumeroPagine() + " , " + "genere : "
				+ getGenere() + " , " + "autore : " + getAutore() + "\n";
	}
}

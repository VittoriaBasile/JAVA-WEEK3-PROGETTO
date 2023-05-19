package progetto.prestito;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import progetto.catalogo.Catalogo;

@Entity
@Table(name = "prestiti")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "findByNumeroTessera", query = "SELECT p FROM Prestito p WHERE p.utente.numeroDiTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL")
@NamedQuery(name = "findPrestitiScadutiNonRestituiti", query = "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL")
public class Prestito {
	@Id
	@GeneratedValue
	private UUID id;
	@ManyToOne
	private Utente utente;
	@ManyToOne
	private Catalogo elementoPrestato;
	private LocalDate dataInizioPrestito;
	private LocalDate dataRestituzionePrevista;
	private LocalDate dataRestituzioneEffettiva;

	public Prestito(Utente utente, Catalogo elementoPrestato, LocalDate dataInizioPrestito,
			LocalDate dataRestituzioneEffettiva) {
		this.utente = utente;
		this.elementoPrestato = elementoPrestato;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	public Prestito(Utente utente, Catalogo elementoPrestato, LocalDate dataInizioPrestito) {
		this.utente = utente;
		this.elementoPrestato = elementoPrestato;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
	}

	@Override
	public String toString() {
		return "Prestito [id=" + id + ", utente=" + utente + ", elementoPrestato=" + elementoPrestato
				+ ", dataInizioPrestito=" + dataInizioPrestito + ", dataRestituzionePrevista="
				+ dataRestituzionePrevista + ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva + "]";
	}

}

package progetto.prestito;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor

public class Utente {
	@Id
	@GeneratedValue
	private UUID numeroDiTessera;
	private String nome;
	private String cognome;
	private LocalDate dataDiNascita;
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	private Set<Prestito> prestiti;

	public Utente(String nome, String cognome, LocalDate dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}

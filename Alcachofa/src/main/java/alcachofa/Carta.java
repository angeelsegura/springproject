package alcachofa;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "cartas")
public class Carta {
	public enum TipoCarta {
		CARXOFA, CEBOLLA, PORRO, PATATA, REMOLATXA, BROCOLI, BLAT_DE_MORO, PASTANAGA, ALBERGINIA, PEBROT, PESOLS
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCarta;

	@Enumerated(EnumType.STRING)
	@Column(name = "nom", nullable = false)
	private TipoCarta nom;

	@Column(name = "descripcio")
	private String descripcio;

	@ManyToMany(mappedBy = "cartas")
	private Set<Jugador> jugadores = new HashSet<>();

	@ManyToMany(mappedBy = "cartas")
	private Set<Partida> partidas = new HashSet<>();

	public int getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}

	public TipoCarta getNom() {
		return nom;
	}

	public void setNom(TipoCarta nom) {
		this.nom = nom;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Set<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(Set<Partida> partidas) {
		this.partidas = partidas;
	}
}

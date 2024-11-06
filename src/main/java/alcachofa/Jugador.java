package alcachofa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "jugadores")
public class Jugador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idJugador;

	@Column(name = "alias", nullable = false)
	private String alias;

	@Column(name = "numero_partides", nullable = false)
	private int numeroPartides = 0;

	@Column(name = "guanyar", nullable = false)
	private boolean guanyar = false;

	@ManyToMany
	@JoinTable(name = "jugador_carta", joinColumns = @JoinColumn(name = "id_jugador"), inverseJoinColumns = @JoinColumn(name = "id_carta"))
	private Set<Carta> cartas = new HashSet<>();

	@ManyToMany(mappedBy = "jugadores")
	private Set<Partida> partidas = new HashSet<>();

	@OneToMany(mappedBy = "jugador")
	private Set<Jugar> jugadas = new HashSet<>();

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getNumeroPartides() {
		return numeroPartides;
	}

	public void setNumeroPartides(int numeroPartides) {
		this.numeroPartides = numeroPartides;
	}

	public boolean isGuanyar() {
		return guanyar;
	}

	public void setGuanyar(boolean guanyar) {
		this.guanyar = guanyar;
	}

	public Set<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(Set<Carta> cartas) {
		this.cartas = cartas;
	}

	public Set<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(Set<Partida> partidas) {
		this.partidas = partidas;
	}

	public Set<Jugar> getJugadas() {
		return jugadas;
	}

	public void setJugadas(Set<Jugar> jugadas) {
		this.jugadas = jugadas;
	}
}

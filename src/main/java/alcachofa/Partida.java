package alcachofa;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "partidas")
public class Partida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPartida;

	@Column(name = "num_jugadors", nullable = false)
	private int numJugadors = 0;

	@ManyToMany
	@JoinTable(name = "partida_jugador", joinColumns = @JoinColumn(name = "id_partida"), inverseJoinColumns = @JoinColumn(name = "id_jugador"))
	private Set<Jugador> jugadores = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "partida_carta", joinColumns = @JoinColumn(name = "id_partida"), inverseJoinColumns = @JoinColumn(name = "id_carta"))
	private Set<Carta> cartas = new HashSet<>();

	@OneToMany(mappedBy = "partida")
	private Set<Jugar> jugadas = new HashSet<>();

	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public int getNumJugadors() {
		return numJugadors;
	}

	public void setNumJugadors(int numJugadors) {
		this.numJugadors = numJugadors;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Set<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(Set<Carta> cartas) {
		this.cartas = cartas;
	}

	public Set<Jugar> getJugadas() {
		return jugadas;
	}

	public void setJugadas(Set<Jugar> jugadas) {
		this.jugadas = jugadas;
	}
}

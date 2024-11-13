package alcachofa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import alcachofa.Carta.TipoCarta;

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
	private List<Carta> cartas = new ArrayList<>();

	private List<Carta> tienda = new ArrayList<Carta>();

	@OneToMany(mappedBy = "partida")
	private Set<Jugar> jugadas = new HashSet<>();

	public int getIdPartida() {
		return idPartida;
	}

	public Partida(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
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

	public List<Carta> getCartas() {
		return cartas;
	}

	public void generarBaraja() {
		List<Carta> cartas = new ArrayList<Carta>();
		for (TipoCarta c : TipoCarta.values()) {
			for (int i = 0; i < 6; i++) {
				Carta carta = new Carta();
				carta.setTipo(c);
				cartas.add(carta);
			}
		}
		 this.cartas = cartas;
	}

	public void generarTienda() {
		if (cartas.size() >= 5) {
			for (int i = 0; i < 5; i++) {
				tienda.add(cartas.get(i));
			}
			for (int i = 0; i < 5; i++) {
				cartas.remove(0);
			}
		} else if (cartas.size() > 0) {
			tienda = cartas;
			cartas.clear();
		}
	}
	
	
	public void mostrarTienda() {
        for (int i = 0; i < tienda.size(); i++) {
            System.out.println(i + " - " + tienda.get(i).getTipo());
        }
	}
	

	public void escogerVerdura(int idx, int jugadorId) {
		if (tienda.size() > idx) {
			for (Jugador j : jugadores) {
				if (j.getIdJugador() == jugadorId) {
					j.recibirVerdura(tienda.get(idx));
					tienda.remove(idx);
					actualizarTienda();
					break;
				}
			}
		}	
	}
	
	
	public void actualizarTienda() {
		if (cartas.size() > 0 && tienda.size() < 5) {
			tienda.add(cartas.get(0));
			cartas.remove(0);
		}
	}

	public void mezclarBaraja() {
		Random rd = new Random();
		int max = cartas.size();

		List<Carta> barajada = new ArrayList<Carta>();
		while (max != 0) {
			int idx = rd.nextInt(max);
			barajada.add(cartas.get(idx));
			cartas.remove(idx);
			max--;
		}

		cartas = barajada;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public Set<Jugar> getJugadas() {
		return jugadas;
	}

	public void setJugadas(Set<Jugar> jugadas) {
		this.jugadas = jugadas;
	}
}

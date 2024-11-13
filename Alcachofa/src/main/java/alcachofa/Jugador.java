package alcachofa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import alcachofa.Carta.TipoCarta;
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
	private List<Carta> cartas = new ArrayList<>();
	
	private List<Carta> mano = new ArrayList<Carta>();
	
	private List<Carta> descartes = new ArrayList<Carta>();
	
	private List<Carta> alcachofas = new ArrayList<Carta>();

	@ManyToMany(mappedBy = "jugadores")
	private Set<Partida> partidas = new HashSet<>();

	 

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

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
	
	public Jugador(String nombre) {
		this.alias = nombre;
	}
	
	public void inicializarBaraja() {
		for (int i = 0; i < 10; i++) {
			Carta carta = new Carta();
			carta.setTipo(TipoCarta.CARXOFA);
			alcachofas.add(carta);
		}
	}
	
	public void llenarMano() {
		if (alcachofas.size() >= 5) {
			for (int i = 0; i < 5; i++) {
				mano.add(alcachofas.get(i));
			}
			for (int i = 0; i < 5; i++) {
				alcachofas.remove(0);
			}
		} else {
			recuperarBaraja();
			llenarMano();
		}
	}
	
	public void recibirVerdura(Carta carta) {
		mano.add(carta);
	}
	
	public boolean comprovarVictoria() {
		for (Carta carta : mano) {
			if (carta.getTipo() == TipoCarta.CARXOFA) {
				return false;
			}
		} return true;
	}
	
	public void mostrarMano() {
		for (int i = 0; i < mano.size(); i++) {
			System.out.println(i + " - " + mano.get(i).getTipo());
		}
	}
	
	public void mostrarDescartes() {
		for (int i = 0; i < descartes.size(); i++) {
			System.out.println(i + " - " + descartes.get(i).getTipo());
		}
	}
	
	public void descartarMano() {
		for (Carta carta : mano) {
			descartes.add(carta);
		}
		mano.clear();
	}
	
	public void recuperarBaraja() {
		for (Carta carta : descartes) {
			alcachofas.add(carta);
		}
		descartes.clear();
		mezclarBaraja();
	}
	
	public void mezclarBaraja() {
		Random rd = new Random();
		int max = alcachofas.size();

		List<Carta> barajada = new ArrayList<Carta>();
		while (max != 0) {
			int idx = rd.nextInt(max);
			barajada.add(alcachofas.get(idx));
			alcachofas.remove(idx);
			max--;
		}

		alcachofas = barajada;
	}

	public Set<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(Set<Partida> partidas) {
		this.partidas = partidas;
	}

	 
}

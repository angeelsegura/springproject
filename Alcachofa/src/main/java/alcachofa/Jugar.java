package alcachofa;

import jakarta.persistence.*;

@Entity
@Table(name = "jugadas")
public class Jugar {

	public enum EstatJugar {
		DESCARTADA_JUGADOR, TAULA_JUGADOR, MA_JUGADOR, BARALLA_JUGADOR, COMPOSTA, BARALLA
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idJugar;

	@Enumerated(EnumType.STRING)
	@Column(name = "estat", nullable = false)
	private EstatJugar estat;

	@ManyToOne
	@JoinColumn(name = "id_jugador")
	private Jugador jugador;

	@ManyToOne
	@JoinColumn(name = "id_partida")
	private Partida partida;

	public int getIdJugar() {
		return idJugar;
	}

	public void setIdJugar(int idJugar) {
		this.idJugar = idJugar;
	}

	public EstatJugar getEstat() {
		return estat;
	}

	public void setEstat(EstatJugar estat) {
		this.estat = estat;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	

}

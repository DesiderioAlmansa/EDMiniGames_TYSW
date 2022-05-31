package edu.uclm.esi.tys2122.model;

import java.io.IOException;
import java.util.UUID;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import edu.uclm.esi.tys2122.dao.UserRepository;
import edu.uclm.esi.tys2122.websockets.WrapperSession;

@Entity
@Table(name = "partida")
public abstract class Match {
	@Id
	@Column(length = 36)
	private String id;

	@Transient
	private Board board;

	@Transient
	protected Vector<User> players;

	@Transient
	protected User playerWithTurn;

	@Transient
	protected boolean ready;
	
	@Transient
	protected User winner;
	
	@Transient
	@Autowired
	protected UserRepository userRepo;

	public Match() {
		this.id = UUID.randomUUID().toString();
		this.players = new Vector<>();
		this.board = newBoard();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	// TODO : no se puede añadir dos veces el mismo jugador
	public void addPlayer(User user) {
		this.players.add(user);
		checkReady();
	}
	
	public void addCPU(User user) {
		this.players.add(user);
		checkReady();
	}

	public boolean isReady() {
		return ready;
	}

	public User getPlayerWithTurn() {
		return playerWithTurn;
	}
	
	public User getWinner() {
		return winner;
	}
	
	public abstract User getLooser();

	@Transient
	public Vector<User> getPlayers() {
		return players;
	}

	protected abstract void checkReady();

	protected abstract Board newBoard();

	public abstract void move(String userId, JSONObject jso) throws Exception;

	public void notifyNewState(String userId) {
		JSONObject jso = new JSONObject();
		jso.put("type", "BOARD");
		for (User player : this.players) {
			if (!player.getId().equals(userId))
				try {
					player.sendMessage(jso);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public void avisaOponente(String type, User... users) {
		JSONObject jso = new JSONObject();
		jso.put("type", type);
		jso.put("matchId", this.id);
		byte[] payload = jso.toString().getBytes();
		TextMessage msg = new TextMessage(payload);

		for (User user : this.players) {
			if (isUser(user, users))
				continue;
			Runnable r = new Runnable() {
				@Override
				public void run() {
					WrapperSession ws = user.getSession();
					WebSocketSession wss = ws.getWsSession();
					try {
						wss.sendMessage(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			};
			new Thread(r).start();
		}
		
	}
	
	

	private boolean isUser(User user, User[] users) {
		for (int i = 0; i < users.length; i++) {
			if (user == users[i])
				return true;
		}
		return false;
	}
	
	public String getGame() {
		return this.getClass().getSimpleName();
	}
	
	public void setWinner(User winner) {
		this.winner = winner;
	}

	public abstract void closeMatchByUser(User user);

	
	public abstract void moveConMaquina(String userId, JSONObject jso) throws Exception;

}

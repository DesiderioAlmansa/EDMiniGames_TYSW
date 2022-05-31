package edu.uclm.esi.tys2122.tictactoe;

import java.security.SecureRandom;

import org.json.JSONObject;

import edu.uclm.esi.tys2122.http.Manager;
import edu.uclm.esi.tys2122.model.Board;
import edu.uclm.esi.tys2122.model.Match;
import edu.uclm.esi.tys2122.model.User;

public class TictactoeMatch extends Match {
	
	private User winner, looser;
	private boolean draw;
	
	@Override
	protected Board newBoard() {
		return new TictactoeBoard();
	}

	@Override
	protected void checkReady() {
		this.ready = this.players.size() == 2;
		if (this.ready) {
			this.playerWithTurn = this.players.get(1);
			super.avisaOponente("PARTIDA_LISTA", this.players.get(1));
		}
	}

	public int getSquare(Integer x, Integer y) {
		TictactoeBoard board = (TictactoeBoard) this.getBoard();
		return board.getSquares()[x][y];
	}

	public void setSquare(Integer x, Integer y, int value) {
		TictactoeBoard board = (TictactoeBoard) this.getBoard();
		board.getSquares()[x][y]=value;
	}
	
	public void setSquareMaquina() {
		TictactoeBoard board = (TictactoeBoard) this.getBoard();
		boolean sigue = true;
		int[][] squares = board.getSquares();
		for (int i=0; i<3 && sigue; i++) {
			for (int j=0; j<3 && sigue; j++) {
				if (squares[i][j]==0) {
					board.getSquares()[i][j]=2;
					sigue = false;
				}
			}
		}
		checkWinner();
		
		if (this.filled() && this.winner==null)
			this.draw = true;
		else {
			this.playerWithTurn = this.getPlayerWithTurn()==this.getPlayers().get(0) ?
				this.getPlayers().get(1) : this.getPlayers().get(0);
		}
		
		if(this.winner != null ) {
			Manager.get().obtenerRepositorioMatch().saveMatch(this.getGame(), this.getId(), this.getLooser(), this.getWinner());
			super.avisaOponente("PARTIDA_FINALIZADA");
		} else {
			super.avisaOponente("PARTIDA_ACTUALIZADA");
		}
	}

	@Override
	public void move(String userId, JSONObject jsoMovimiento) throws Exception {
		if (this.filled())
			throw new Exception("La partida ya terminó");
		
		if (!this.getPlayerWithTurn().getId().equals(userId))
			throw new Exception("No es tu turno");
		
		Integer x = jsoMovimiento.getInt("x");
		Integer y = jsoMovimiento.getInt("y");
		
		if (this.getSquare(x, y)!=0)
			throw new Exception("Casilla ocupada");
		
		int value = this.getPlayerWithTurn()==this.getPlayers().get(0) ? 1 : 2;
		this.setSquare(x, y, value);
		
		checkWinner();
		
		if (this.filled() && this.winner==null)
			this.draw = true;
		else {
			this.playerWithTurn = this.getPlayerWithTurn()==this.getPlayers().get(0) ?
				this.getPlayers().get(1) : this.getPlayers().get(0);
		}
		
		if(this.winner != null ) {
			Manager.get().obtenerRepositorioMatch().saveMatch(this.getGame(), this.getId(), this.getLooser(), this.getWinner());
			super.avisaOponente("PARTIDA_FINALIZADA");
		} else {
			super.avisaOponente("PARTIDA_ACTUALIZADA");
		}
	}
	
	@Override
	public void moveConMaquina(String userId, JSONObject jsoMovimiento) throws Exception {
		if (this.filled())
			throw new Exception("La partida ya terminó");

		Integer x = jsoMovimiento.getInt("x");
		Integer y = jsoMovimiento.getInt("y");
		
		if (this.getSquare(x, y)!=0)
			throw new Exception("Casilla ocupada");
		
		
		this.setSquare(x, y, 1);
		
		
		checkWinner();
		
		if (this.filled() && this.winner==null)
			this.draw = true;
		else {
			this.playerWithTurn = this.getPlayerWithTurn()==this.getPlayers().get(0) ?
				this.getPlayers().get(1) : this.getPlayers().get(0);
		}
		
		if(this.winner != null ) {
			Manager.get().obtenerRepositorioMatch().saveMatch(this.getGame(), this.getId(), this.getLooser(), this.getWinner());
			super.avisaOponente("PARTIDA_FINALIZADA");
		} else {
			super.avisaOponente("PARTIDA_ACTUALIZADA");
		}
		
	
		this.setSquareMaquina();
	}

	private boolean filled() {
		TictactoeBoard board = (TictactoeBoard) this.getBoard();
		int[][] squares = board.getSquares();
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				if (squares[i][j]==0)
					return false;
		return true;
	}
	
	private void checkWinner() {
		TictactoeBoard board = (TictactoeBoard) this.getBoard();
		int[][] squares = board.getSquares();
		
		if (squares[0][0]!=0 && squares[0][0]==squares[0][1] && squares[0][1]==squares[0][2] ||
				squares[1][0]!=0 && squares[1][0]==squares[1][1] && squares[1][1]==squares[1][2] ||
				squares[2][0]!=0 && squares[2][0]==squares[2][1] && squares[2][1]==squares[2][2]) {
			this.winner = this.getPlayerWithTurn();
		} else if (squares[0][0]!=0 && squares[0][0]==squares[1][0] && squares[1][0]==squares[2][0] ||
				squares[0][1]!=0 && squares[0][1]==squares[1][1] && squares[1][1]==squares[2][1] ||
				squares[0][2]!=0 && squares[0][2]==squares[1][2] && squares[2][1]==squares[2][2]) {
			this.winner = this.getPlayerWithTurn();
		} else if (squares[0][0]!=0 && squares[0][0]==squares[1][1] && squares[1][1]==squares[2][2] ||
				squares[0][2]!=0 && squares[0][2]==squares[1][1] && squares[1][1]==squares[2][0]) {
			this.winner = this.getPlayerWithTurn();
		}
		if (this.winner!=null) {
			this.looser = this.winner==this.players.get(0) ? this.players.get(1) : this.players.get(0);
		}
	}

	
	
	public User getWinner() {
		return winner;
	}
	
	public User getLooser() {
		return looser;
	}
	
	public boolean isDraw() {
		return draw;
	}
	
	public void setWinner(User winner) {
		this.winner = winner;
	}

	public void setLooser(User looser) {
		this.looser = looser;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	@Override
	public void closeMatchByUser(User user) {
		this.setLooser(user);
		for (User u : this.players)
			if(!u.equals(user))
				this.setWinner(u);
		
		Manager.get().obtenerRepositorioMatch().saveMatch(this.getGame(), this.getId(), this.getLooser(), this.getWinner());
		avisaOponente("PARTIDA_FINALIZADA", user);
	}
	
}

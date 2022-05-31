package edu.uclm.esi.tys2122.http;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import edu.uclm.esi.tys2122.model.Game;
import edu.uclm.esi.tys2122.model.Match;
import edu.uclm.esi.tys2122.model.User;
import edu.uclm.esi.tys2122.services.GamesService;
import edu.uclm.esi.tys2122.services.UserService;

@RestController
@RequestMapping("games")
public class GamesController extends CookiesController {
	@Autowired
	private GamesService gamesService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getGames")
	public List<Game> getGames(HttpSession session) throws Exception {
		return gamesService.getGames();
	}

	@GetMapping("/joinGame/{gameName}")
	public Match joinGame(HttpSession session, @PathVariable String gameName) throws Exception {
		User user;
		if (session.getAttribute("user")!=null) {
			user = (User) session.getAttribute("user");
		} else {
			user = new User();
			user.setName("anonimo" + new SecureRandom().nextInt(1000));
			session.setAttribute("user", user);
		}

		Manager.get().add(session);
		
		Game game = Manager.get().findGame(gameName);
		if (game==null)
			throw new Exception("No se encuentra el juego " + gameName);
		
		Match match = getMatch(game);
		match.addPlayer(user);
		if (match.isReady()) {
			game.getPendingMatches().remove(match);
			game.getPlayingMatches().add(match);
		}
		gamesService.put(match);
		return match;
	}
	
	@GetMapping("/joinGameMaquina/{gameName}")
	public Match joinGameMaquina(HttpSession session, @PathVariable String gameName) throws Exception {
		User user;
		User cpu = new User("EDMiniGamesCPU", "cpu@cpu.com");
		
		if (session.getAttribute("user")!=null) {
			user = (User) session.getAttribute("user");
		} else {
			user = new User();
			user.setName("anonimo" + new SecureRandom().nextInt(1000));
			session.setAttribute("user", user);
		}
		Manager.get().add(session);
		
		
		Game game = Manager.get().findGame(gameName);
		if (game==null)
			throw new Exception("No se encuentra el juego " + gameName);
		
		Match match = getMatch(game);
		match.addPlayer(cpu);
		match.addPlayer(user);
		
		
		if (match.isReady()) {
			game.getPendingMatches().remove(match);
			game.getPlayingMatches().add(match);
		}
		gamesService.put(match);
		return match;
	}
	
	
	
	@PostMapping("/move")
	public Match move(HttpSession session, @RequestBody Map<String, Object> movement) throws Exception {
		User user = (User) session.getAttribute("user");
		JSONObject jso = new JSONObject(movement);
		Match match = gamesService.getMatch(jso.getString("matchId"));
		match.move(user.getId(), jso);
		return match;
	}
	
	@PostMapping("/moveJuegoContraMaquina")
	public Match moveJuegoContraMaquina(HttpSession session, @RequestBody Map<String, Object> movement) throws Exception {
		User user = (User) session.getAttribute("user");
		JSONObject jso = new JSONObject(movement);
		Match match = gamesService.getMatch(jso.getString("matchId"));
		match.moveConMaquina(user.getId(), jso);
		return match;
	}
	
	@GetMapping("/findMatch/{matchId}")
	public Match findMatch(@PathVariable String matchId) {
		return gamesService.getMatch(matchId);
	}
	
	@PostMapping("/sendMessageChat")
	public void sendMessageChat(HttpSession session, @RequestBody Map<String, Object> messageInfo) {
		JSONObject jso = new JSONObject(messageInfo);
		String msg = jso.getString("msg");
		User user = (User) session.getAttribute("user");
		
		JSONObject jsoMsg = new JSONObject();
		jsoMsg.put("user", user.getName());
		jsoMsg.put("msg", msg);
		byte[] payload = jsoMsg.toString().getBytes();
		TextMessage wsmsg = new TextMessage(payload);
		
		for (WebSocketSession ws : Manager.get().getChatSessions()) {
			try {
				ws.sendMessage(wsmsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Match getMatch(Game game) {
		Match match;
		if (game.getPendingMatches().isEmpty()) {
			match = game.newMatch();
			game.getPendingMatches().add(match);
		} else {
			match = game.getPendingMatches().get(0);
		}
		return match;
	}
	
	@GetMapping("/getEstadisticas")
	public int[] getStatistics(HttpSession session) {
		int[] statistics = { 0, 0, 0, 0, 0 };
		System.out.println(statistics[0]);
		System.out.println(statistics[1]);
		System.out.println(statistics[2]);
		System.out.println(statistics[3]);
		System.out.println(statistics[4]);
		User user = null;
		user = (User) session.getAttribute("user");
		
		System.out.println(user);

		if (user != null) {
			Object[] array = (Object[]) Manager.get().obtenerRepositorioMatch().getStatistics(user.getId())[0];
			boolean token = false;

			for (int i = 0; i < array.length; i++) {
				token = ((BigInteger) array[i]).intValue() == 0 ? token : true;
				statistics[i + 1] = ((BigInteger) array[i]).intValue();
			}
			statistics[0] = token ? 1 : 0;
		}
		return statistics;
	}
	
	@GetMapping("/rendirse/{matchId}")
	public void surrender(HttpSession session, @PathVariable String matchId) throws Exception {
		User user = (User) session.getAttribute("user");
		Match auxMatch = gamesService.getMatch(matchId);
		auxMatch.closeMatchByUser(user);
	}
}

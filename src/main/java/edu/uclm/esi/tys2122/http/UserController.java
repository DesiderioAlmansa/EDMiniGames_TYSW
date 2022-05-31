package edu.uclm.esi.tys2122.http;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.esi.tys2122.model.Email;
import edu.uclm.esi.tys2122.model.User;
import edu.uclm.esi.tys2122.services.UserService;

@RestController
@RequestMapping("user")
public class UserController extends CookiesController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> credenciales) {
		 
		
		JSONObject jso = new JSONObject(credenciales);
		
		if (jso.has("pwd")) {
			String name = jso.getString("name");
			String pwd = jso.getString("pwd");
			String ip = request.getRemoteAddr();
			
			User user = userService.doLogin(name, pwd, ip);
			
			Cookie cookie = readOrCreateCookie(request, response);
			userService.insertLogin(user, ip, cookie);
			request.getSession().setAttribute("user", user);
		}
		else {
			String name = jso.getString("name");
			String email = jso.getString("email");
			String id = jso.getString("id");
			String ip = request.getRemoteAddr();
			User userGuardado = Manager.get().obtenerRepositorioUser().findByEmail(email);
			if (userGuardado ==  null) {
				User user = new User(id,name,email);
				Cookie cookie = readOrCreateCookie(request, response);
				Manager.get().obtenerRepositorioUser().save(user);
				userService.insertLogin(user, ip, cookie);
				request.getSession().setAttribute("user", user);
			} else {
				User user = new User(id,name,email);
				Cookie cookie = readOrCreateCookie(request, response);
				userService.insertLogin(user, ip, cookie);
				request.getSession().setAttribute("user", user);
			}
		}
	}

	@PutMapping("/register")
	@ResponseBody
	public String register(@RequestBody Map<String, Object> credenciales) {
		JSONObject jso = new JSONObject(credenciales);
		String userName = jso.optString("userName");
		String email = jso.optString("email");
		String pwd1 = jso.optString("pwd1");
		String pwd2 = jso.optString("pwd2");
		String picture = jso.optString("picture");
		
		
		
		if (!pwd1.equals(pwd2))
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: las contraseñas no coinciden");
		if (userName.equals("") || email.equals(""))
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: los campos de \"Nombre de usuario\" y \"Correo electronico\"");
		if (pwd1.equals(""))
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: no has introducido ninguna contraseña");
		if (pwd1.length()<4)
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: la contraseña debe tener al menos cuatro caracteres");
		if (pwd2.equals(""))
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: debes confirmar la contraseña");
		
		//cifrado de contraseña
		pwd1 = org.apache.commons.codec.digest.DigestUtils.sha512Hex(pwd1);
		
		try {
			User user = new User();
			user.setName(userName);
			user.setEmail(email);
			user.setPwd(pwd1);
			user.setPicture(picture);
			
			User aux = userService.findUserByEmail(email); 
			
			if(aux != null) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: Ya existe un usuario con este email.");
			}
			else {
				userService.save(user);
				//enviarCorreo(credenciales, "register");
				return "Te hemos enviado un correo para confirmar tu registro";
			}
			
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: No se ha podido crear el usuario.");
		}
	}
	
	@DeleteMapping("/remove/{userId}")
	public void remove(@PathVariable String userId) {
		System.out.println("Borrar el usuario con id " + userId);		
	}
	
	@GetMapping("/validateAccount/{tokenId}")
	public void validateAccount(HttpServletRequest request, HttpServletResponse response, @PathVariable String tokenId) {
		userService.validateToken(tokenId);
		// Ir a la base de datos, buscar el token con ese tokenId en la tabla, ver que no ha caducado
		// y actualizar la confirmationDate del user
		System.out.println(tokenId);
		try {
			response.sendRedirect(Manager.get().getConfiguration().getString("home"));
		} catch (IOException e) {
			
		}
	}
	
	private void enviarCorreo(Map<String, Object> credenciales, String type) {
		JSONObject applicationData = Manager.get().getConfiguration();
		JSONObject emailDefaultData = Manager.get().getConfiguration().getJSONObject("email");

		JSONObject jso = new JSONObject(credenciales);
		String email = jso.getString("email");

		User user = userService.findUserByEmail(email);
		if (user != null) {
			Email auxEmail = new Email();
			switch (type) {
			case "register":
				auxEmail.send(email, (String) emailDefaultData.get("registerMsgTopic"), (String) emailDefaultData.get("registerMsgContent"));
				break;
			case "recovery":
				String auxToken = UUID.randomUUID().toString();
				auxEmail.send(email, (String) emailDefaultData.get("recoveryMsgTopic"), (String) emailDefaultData.get("recoveryMsgContent") + (String) applicationData.get("home") + "/" + (String) applicationData.get("changePassword") + "/" + auxToken);
				//Manager.get().obtenerRepositorioUser().setTokenByEmail(auxToken, email);
				break;
			}
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No existe ningún usuario con ese correo");
		}
	}

}

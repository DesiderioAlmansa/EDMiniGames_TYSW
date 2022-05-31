package edu.uclm.esi.tys2122.junit;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Random;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.uclm.esi.tys2122.MvcTestCase;

class JupiterTest extends MvcTestCase{
	
	@Test
	void JupTest() throws Exception{
		testRegistrar();
		testLogin();
	}
	
	private void testRegistrar() throws Exception {

		//Genera una persona aleatoria cada vez que pruebe el metodo
		JSONObject p1 = genRandPerson();

		
		// Se registra bien
		doPut("/user/register", null, 
				"userName", p1.getString("name"),
				"email", p1.getString("email"),
				"pwd1", p1.getString("pwd"),
				"pwd2", p1.getString("pwd")
				).andExpect(status().isOk());
		
		// No se registra bien, faltan campos por cubrir
		doPut("/user/register", null, 
				"userName", p1.getString("name")
				).andExpect(status().is4xxClientError());
		
		// Error, ya existe ese usuario
		doPut("/user/register", null, 
				"userName", p1.getString("name"),
				"email", p1.getString("email"),
				"pwd1", p1.getString("pwd"),
				"pwd2", p1.getString("pwd")
				).andExpect(status().is4xxClientError());
		
	}
	
	private JSONObject genRandPerson() {

		int rndValue = (int)(Math.random() * 1000);
		JSONObject jso = new JSONObject();
		
		String id = UUID.randomUUID().toString();
		String user = "prueba" + rndValue;
		String email = user;
		String pwd = user;

		jso.put("id", id);
		jso.put("name", user);
		jso.put("email", email);
		jso.put("pwd", pwd);
		
		return jso;
	}
	
	
	private void testLogin() throws Exception {

		JSONObject g = genRandPerson();
		
		// Se registra bien
		doPost("/user/login", null, 
				"name", g.getString("name"), 
				"email", g.getString("email"), 
				"id", g.getString("id")
				).andExpect(status().isOk());
		
		// No se registra bien, faltan campos por cubrir
		doPost("/user/login", null, 
				"name", g.getString("name"), 
				"pwd", " "
				).andExpect(status().is4xxClientError());		
	}
	

}
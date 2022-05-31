package edu.uclm.esi.tys2122.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SuppressWarnings("unused") // remember to delete this
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SeleniumTest {

	private WebDriver driverPepe;
	private WebDriver driverAna;
	
	WebElement msg;

	@BeforeEach
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Desiderio/OneDrive/Documentos/GitHub/EDMiniGames/src/main/resources/chromedriver.exe");
		this.driverPepe =new ChromeDriver();	
		this.driverAna =new ChromeDriver();
	}

	public WebDriver registrar(WebDriver driver,String suser, String semail, String spwd, String spwd2) throws InterruptedException {
		By btnCrearCuenta = By.id("btnCrearCuenta");
		By user = By.id("userNombre");
		By email = By.id("emailNombre");
		By pwd1 = By.id("pwd1");
		By pwd2 = By.id("pwd2");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://localhost:3000/?ojr=register");
		
		if(driver.findElement(btnCrearCuenta).isDisplayed()) {
			driver.findElement(user).sendKeys(suser);
			driver.findElement(email).sendKeys(semail);
			driver.findElement(pwd1).sendKeys(spwd);
			driver.findElement(pwd2).sendKeys(spwd2);
			driver.findElement(btnCrearCuenta).click();
			Thread.sleep(1000);
			
			
		}else {
			System.out.println("Algo no ha ido bien");
			
		}
		return driver;
	}
	
	public WebDriver iniciarSesion(WebDriver driver, String user, String pwd) throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div/div[2]/div/oj-navigation-list/div/div/ul/li[1]/a/span")).click();
		By userLogin = By.id("userLogin");
		By pwdLogin = By.id("pwdLogin");
		By btnLogin = By.id("btnLogin");
		driver.findElement(userLogin).sendKeys(user);
		driver.findElement(pwdLogin).sendKeys(pwd);
		driver.findElement(By.xpath("/html/body/div/oj-module/div[1]/form/button")).click();		
		driver.findElement(By.xpath("/html/body/div/div[2]/div/oj-navigation-list/div/div/ul/li[5]/a")).click();
		driver.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[1]/div/button")).click();
		//String p1 = "Pepe8";
		//String p2 = driver.findElement(By.id("jugadorConTurno")).toString();
		//System.out.println("BADAMPLAS");

		//}
		return driver;
		
	}

	
	public WebDriver jugarTresEnRaya(WebDriver driverPepe, WebDriver driverAna) {
		//if(p1.equals(p2)) {
			//if(driver.findElement(By.id("jugadorConTurno")).toString().equals("Pepe")) {
			
		
		try {	
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
			
			
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();

			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
			
			
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
		
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
			
		}catch(Exception ex) {
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
			driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
			
			
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
			driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
			
			
		}	
		/***********************************************************************
		try {	
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
			}catch(Exception ex) {
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
			}
				*****************************************************************************/
				/*
				//LIMPIA CAMPOS
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();	
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				//LIMPIA CAMPOS
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();	
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				/*
				//LIMPIA CAMPOS
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();	
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("0");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				
				//LIMPIA CAMPOS
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();	
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				
				//LIMPIA CAMPOS
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();	
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("1");		
				driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				
				
				
				//LIMPIA CAMPOS
				this.driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				this.driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();	
				this.driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				this.driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				this.driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				this.driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				this.driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				this.driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("1");
				this.driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("2");		
				this.driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				
				//LIMPIA CAMPOS
				this.driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				this.driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();	
				this.driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).clear();
				this.driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).clear();
				
				this.driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
				this.driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				this.driverPepe.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				
				this.driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[1]")).sendKeys("2");
				this.driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/input[2]")).sendKeys("0");		
				this.driverAna.findElement(By.xpath("/html/body/div/oj-module/div[1]/div[2]/div/div/div[2]/div/div[1]/ol/li/button")).click();
				*/

			//}			
			
			
		return driverPepe;
	}
	@Test
	public void seleniumTest() throws InterruptedException {

		this.driverPepe = registrar(this.driverPepe, "Pepe", "Pepe@pepe.com", "pepe1234", "pepe1234");
		this.driverAna = registrar(this.driverAna, "Ana", "Ana@ana.com", "ana1234", "ana1234");
		Thread.sleep(2000);
		this.driverPepe = iniciarSesion(this.driverPepe, "Pepe", "pepe1234");
		this.driverAna = iniciarSesion(this.driverAna, "Ana", "ana1234");
		Thread.sleep(2000);
		this.driverPepe = jugarTresEnRaya(this.driverPepe, this.driverAna);
		//this.driverAna = jugarTresEnRaya(this.driverAna);	
		//msg = driverPepe.findElement(By.id("errorRegistro"));
		//assertEquals("Error: las contraseñas no coinciden", msg.getText());
		
		
	}
	
}

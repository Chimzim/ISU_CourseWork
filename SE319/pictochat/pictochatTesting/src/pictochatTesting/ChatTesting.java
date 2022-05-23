package pictochatTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;

class ChatTesting {
	static String chromeDriverPath = "C:/IowaState/SE319/SeleniumWebDriver/chromedriver.exe";
	String LoginPagePath = "C:/Users/chimz/g46/Socket.io_Work/index.html";
	static WebDriver myDriver;
	
	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		myDriver = new ChromeDriver();
		myDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public static void closeBrowser() {
		myDriver.quit();
	}

	@Test
	public void SendChat() throws InterruptedException {
		myDriver.get(LoginPagePath);
		SendChat2();
		myDriver.manage().window().maximize();
		myDriver.findElement(By.id("name-field")).sendKeys("UserTest1");
		
		Thread.sleep(2000);
		myDriver.findElement(By.id("login-form-submit")).click();
		
		Thread.sleep(3000);
		myDriver.findElement(By.id("join-room-submit")).click();
		
		Thread.sleep(2000);
		myDriver.findElement(By.id("chat-message")).sendKeys("Hello World!");
		
		Thread.sleep(1000);
		myDriver.findElement(By.id("message-submit")).click();
		
	}
	
	
	public void SendChat2() throws InterruptedException {
		myDriver.get(LoginPagePath);
		myDriver.manage().window().maximize();
		myDriver.findElement(By.id("name-field")).sendKeys("UserTest2");
		
		Thread.sleep(2000);
		myDriver.findElement(By.id("login-form-submit")).click();
		
		Thread.sleep(3000);
		myDriver.findElement(By.id("join-room-submit")).click();
		
		Thread.sleep(2000);
		myDriver.findElement(By.id("chat-message")).sendKeys("Hello World!");
		
		Thread.sleep(1000);
		myDriver.findElement(By.id("message-submit")).click();
	}
}

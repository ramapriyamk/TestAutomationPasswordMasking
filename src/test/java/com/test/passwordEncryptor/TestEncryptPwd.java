package com.test.passwordEncryptor;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.main.passwordEncrypter.EncryptPwd;

import io.github.bonigarcia.wdm.WebDriverManager;



public class TestEncryptPwd {
	EncryptPwd encryptPwd = new EncryptPwd();
	
	private WebDriver driver;
	public static Properties Config;
	public static File file;
	public static FileInputStream Configfile;
	
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		Config = new Properties();
		file = new File(System.getProperty("user.dir")+File.separator + "config.properties");
		Configfile = new FileInputStream(file);
		Config.load(Configfile);
	}
  
@Test	
public void test() throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	String EncryptedPwd = Config.getProperty("PASS");
	System.out.println("Encrypted Password: "+EncryptedPwd);
	String password = encryptPwd.decrypt(EncryptedPwd, System.getProperty("KeyValue"));
	System.out.println("Decrypted Password: "+password);
	driver.get("https://login.salesforce.com/");
	driver.findElement(By.id("username")).sendKeys(Config.getProperty("USER"));
	driver.findElement(By.id("password")).sendKeys(password);
	driver.findElement(By.id("Login")).click();
	Thread.sleep(5000);
	driver.close();
		/*
		 * System.out.
		 * println("Trying out Password Encryption for \nUsername: test@test.com and Password: Password@123"
		 * ); String EncryptedPwd = "FQ8QAQ4fBg0vX3Nd";
		 * System.out.println("Encrypted Password : FQ8QAQ4fBg0vX3Nd" ); String password
		 * = encryptPwd.decrypt(EncryptedPwd, System.getProperty("KeyValue"));
		 * System.out.println("Decrypted Password: "+password);
		 * Assert.assertEquals(password, "Password@123",
		 * "The password is not Matching");
		 */
  }
}

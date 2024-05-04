package org.campus;


import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import net.bytebuddy.jar.asm.Type;


public class Academicmodule {
	 WebDriver driver;
	@BeforeClass
	private void launchthebrowser() {
		System.out.println("Launch Browser");
	}
		@AfterClass
			private void closethebrowser() {
			System.out.println("Browser Close");
				
			}
		@DataProvider( name="login")
		
		private Object[][] data() {
			
			return new Object[][] {
				{"ssasesr","xdxtdd"},
				{"esrsrdtd","frtgfyuf"},
				{"Dave","Demo!@3PwdAa"},
				{"wrdwrdt","ddtsfst"}
				
			};
		}
		


	@Test(dataProvider = "login")
	private void TC01(String name, String pass) throws InterruptedException {
		// login Function
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();
		WebElement user = driver.findElement(By.xpath("//input[@type='email']"));
		user.sendKeys(name);
		
		WebElement password = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
		password.sendKeys(pass);
		
		WebElement log = driver.findElement(By.id("kt_login_signin_submit"));
		log.click();
		Thread.sleep(3000);
		
			
		
		
		
		
	}	
		
	
	
	@Test(enabled=false)
	private void TC02() {
		//school
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();
		WebElement user = driver.findElement(By.xpath("//input[@type='email']"));
		user.sendKeys("Dave");
		WebElement pass = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
		pass.sendKeys("Demo!@3PwdAa");
		WebElement login = driver.findElement(By.id("kt_login_signin_submit"));
		login.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownBasic1")));
		menu.click();
		WebElement academic = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='col-md-9'])[3]")));
		academic.click();
		WebElement school = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='kt-menu__link-text ng-star-inserted'])[3]")));
		school.click();
		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='mat-button-wrapper'])[4]")));
		add.click();
		WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-3")));
		name.sendKeys("school of Technology");
		//WebElement divisioncode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Division Code *']")));
		//divisioncode.sendKeys("Comd12");
		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("form-submit")));
		submit.click();	
		WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'attention')]")));
		String text2 = text.getText();
		System.out.println(text2);
		WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@class='la la-close'])[4]")));
		icon.click();
		WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//i[@class='la la-close'])[3]")));
		close.click();
		WebElement yes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='swal2-confirm swal2-styled']")));
		yes.click();
		WebElement sidemenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("kt_header_menu_wrapper")));
		sidemenu.click();
	     driver.close();	
	}
	@Test(enabled = false)
	private void TC03() {
		//department
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();
		WebElement user = driver.findElement(By.xpath("//input[@type='email']"));
		user.sendKeys("Dave");
		WebElement pass = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
		pass.sendKeys("Demo!@3PwdAa");
		WebElement login = driver.findElement(By.id("kt_login_signin_submit"));
		login.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement menu = driver.findElement(By.id("dropdownBasic1"));
		menu.click();
		WebElement academic = driver.findElement(By.xpath("(//div[@class='col-md-9'])[3]"));
		academic.click();
		WebElement department = driver.findElement(By.xpath("(//span[@class='kt-menu__link-text ng-star-inserted'])[4]"));
		department.click();
		WebElement add = driver.findElement(By.xpath("(//span[@class='mat-button-wrapper'])[4]"));
		add.click();
		WebElement name = driver.findElement(By.xpath("//input[@data-placeholder='Department Name *']"));
		name.sendKeys("geosecience");
		WebElement type = driver.findElement(By.xpath("//input[@placeholder='Department Type *']"));
		type.click();
		WebElement choose = driver.findElement(By.xpath("(//span[@class='mat-option-text'])[3]"));
		choose.click();
		WebElement next = driver.findElement(By.id("form-saveNext"));
		next.click();
		WebElement submit = driver.findElement(By.id("form-submit"));
		submit.click();
		WebElement close = driver.findElement(By.xpath("(//i[@class='la la-close'])[3]"));
		close.click();
		WebElement yes = driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']"));
		yes.click();
		driver.close();
		
	}	
		@Test(enabled = false)
		private void TCO4() {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.get("https://demo.creatrixcampus.com/auth/login");
			driver.manage().window().maximize();
			WebElement user = driver.findElement(By.xpath("//input[@type='email']"));
			user.sendKeys("Dave");
			WebElement pass = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
			pass.sendKeys("Demo!@3PwdAa");
			WebElement login = driver.findElement(By.id("kt_login_signin_submit"));
			login.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement menu = driver.findElement(By.id("dropdownBasic1"));
			menu.click();
			WebElement academic = driver.findElement(By.xpath("(//div[@class='col-md-9'])[3]"));
			academic.click();
			WebElement program = driver.findElement(By.xpath("(//span[@class='kt-menu__link-text ng-star-inserted'])[5]"));
			program.click();
			WebElement allprogram = driver.findElement(By.xpath("(//span[@class='kt-menu__link-text ng-star-inserted'])[6]"));
			allprogram.click();
			JavascriptExecutor js=(JavascriptExecutor)driver;
			WebElement add = driver.findElement(By.xpath("(//span[@class='mat-button-focus-overlay'])[4]"));
			js.executeScript("arguments[0].click()", add);
			driver.findElement(By.id("mat-input-3")).sendKeys("python");
		//WebElement department = driver.findElement(By.xpath("//span[text()='Departments *']"));
			//js.executeScript("arguments[0].scrollIntoView(false)", department);
			//WebElement name = driver.findElement(By.xpath("//div[@class='mat-form-field-infix ng-tns-c99-92']"));
			WebElement name = driver.findElement(By.xpath("//span[text()='Departments *']"));
			js.executeScript("arguments[0].click()", name);
			WebElement choosename = driver.findElement(By.xpath("//span[text()='Department of Computer program ']"));
			js.executeScript("arguments[0].click()", choosename);
			WebElement submit =driver.findElement(By.id("form-submit"));
			js.executeScript("arguments[0].click()", submit);	
			

		}
		@Test(enabled = false)
		private void TC05() {
			//program study
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				driver.get("https://demo.creatrixcampus.com/auth/login");
				driver.manage().window().maximize();
				WebElement user = driver.findElement(By.xpath("//input[@type='email']"));
				user.sendKeys("Dave");
				WebElement pass = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
				pass.sendKeys("Demo!@3PwdAa");
				WebElement login = driver.findElement(By.id("kt_login_signin_submit"));
				login.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				WebElement menu = driver.findElement(By.id("dropdownBasic1"));
				menu.click();
				WebElement academic = driver.findElement(By.xpath("(//div[@class='col-md-9'])[3]"));
				academic.click();
				WebElement program = driver.findElement(By.xpath("(//span[@class='kt-menu__link-text ng-star-inserted'])[5]"));
				program.click();
				WebElement programplanner = driver.findElement(By.xpath("(//span[@class='kt-menu__link-text ng-star-inserted'])[9]"));
				programplanner.click();
				WebElement setting = driver.findElement(By.xpath("(//i[@class='flaticon-settings-1'])[2]"));
				setting.click();
				WebElement next = driver.findElement(By.xpath("//button[@type='submit']"));
				next.click();
			

		}
		
		
	
		
		
		
		

	}
	

	
	
	
	
	
	


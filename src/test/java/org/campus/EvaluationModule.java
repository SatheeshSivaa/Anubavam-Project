package org.campus;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EvaluationModule {
	WebDriver driver;
	
	
	
	@Test(enabled = false)
	private void TC01() {
		// navigate to Question bank  page 
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();
		
		// login to the system 
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Dave");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Demo!@3PwdAa");
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
		
		//click on menu button 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();

		// Choose Evaluation  Application from the grid /list
		driver.findElement(By.xpath("//span[text()='Evaluation']")).click();
		driver.findElement(By.xpath("//span[text()='Settings']")).click();
		driver.findElement(By.xpath("//a[text()='Question Library']")).click();
		driver.navigate().refresh();
		driver.get("https://demo.creatrixcampus.com/questionbuilder/questionbank/list");
		
	}

	@Test(enabled = false)
	private void TC02() throws InterruptedException, AWTException {
		// create a question bank
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();
		
		// login to the system
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Dave");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Demo!@3PwdAa");
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
		//click on menu button 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();
		// go to question bank 
		driver.navigate().refresh();
		driver.get("https://demo.creatrixcampus.com/questionbuilder/questionbank/list");
		// click on add button 
		WebDriverWait wait =new WebDriverWait(driver, 10);
		JavascriptExecutor js =(JavascriptExecutor) driver;
		
		WebElement add = driver.findElement(By.xpath("(//span[@class='mat-ripple mat-button-ripple'])[4]"));
		js.executeScript("arguments[0].click();", add);
		Thread.sleep(3000);
		WebElement Evalation = driver.findElement(By.xpath("//h5[text()=' Evaluation ']"));
		js.executeScript("arguments[0].click();", Evalation);
		Thread.sleep(3000);
		WebElement name = driver.findElement(By.xpath("//input[@placeholder='Question Bank Name *']"));
		name.sendKeys("Test bank Question");
		Thread.sleep(3000);
		Actions a =new Actions(driver);
		WebElement descripe = driver.findElement(By.xpath("//span[@class='angular-editor-placeholder']"));
		a.moveToElement(descripe).click().sendKeys("Test bank Question").build().perform();
		Thread.sleep(3000);
		//js.executeScript("arguments[0].setAttribute('value','Test bank Question')", descripe);
		driver.findElement(By.xpath("//button[text()=' Submit ']")).click();
		
	}	

	@Test()
	private void TC03() throws InterruptedException, AWTException {
		
		// Create a question
		// navigate to Question bank page
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();

		// login to the system
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Dave");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Demo!@3PwdAa");
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();

		// click on menu button
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();

		// Choose Evaluation Application from the grid /list
		WebElement element = driver.findElement(By.xpath("//span[text()='Evaluation']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		driver.findElement(By.xpath("//span[text()='Settings']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Question Library']")).click();
		driver.navigate().refresh();
		driver.get("https://demo.creatrixcampus.com/questionbuilder/questionbank/list");
		// click on setting icon
		WebElement seting = driver.findElement(By.xpath("(//span[@class='mat-button-focus-overlay'])[9]"));
		js.executeScript("arguments[0].click();", seting);
		Thread.sleep(2000);	
		// click on delete
		driver.findElement(By.xpath("//i[@class='flaticon-delete']")).click();
		driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']")).click();
		Thread.sleep(3000);
		
		// click on add
		 WebElement add = driver.findElement(By.xpath("//i[@class='fa fa-caret-down ml-3']"));
		 js.executeScript("arguments[0].click();", add);
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//button[text()=' Single Choice ']")).click();

		WebElement edit = driver.findElement(By.xpath("//i[@class='flaticon-edit']"));
		edit.click();
		Thread.sleep(3000);
		driver.findElement(By.id("tab-options")).click();
        //Create  the single  choice question 
		Actions a = new Actions(driver);
		WebElement option1 = driver.findElement(By.xpath("(//input[@formcontrolname='label'])[1]"));
		a.moveToElement(option1).click().doubleClick().build().perform();
		option1.clear();
		option1.sendKeys("Strongly agree");
		WebElement score = driver.findElement(By.xpath("(//input[@formcontrolname='score'])[1]"));
		a.moveToElement(score).click().doubleClick();
		score.clear();
		score.sendKeys("4");
		Thread.sleep(3000);
		
		
		WebElement option2 = driver.findElement(By.xpath("(//input[@formcontrolname='label'])[2]"));
		a.moveToElement(option2).click().doubleClick().build().perform();
		option2.clear();
		option2.sendKeys("Agree");
		WebElement score1 = driver.findElement(By.xpath("(//input[@formcontrolname='score'])[2]"));
		a.moveToElement(score1).click().doubleClick();
		score1.clear();
		score1.sendKeys("3");
		Thread.sleep(3000);
		
		
		WebElement option3 = driver.findElement(By.xpath("(//input[@formcontrolname='label'])[3]"));
		a.moveToElement(option3).click().doubleClick().build().perform();
		option3.clear();
		option3.sendKeys("Neutral");
		WebElement score2 = driver.findElement(By.xpath("(//input[@formcontrolname='score'])[3]"));
		a.moveToElement(score2).click().doubleClick();
		score2.clear();
		score2.sendKeys("2");
		Thread.sleep(3000);
		

		WebElement option4 = driver.findElement(By.xpath("(//input[@formcontrolname='label'])[4]"));
		a.moveToElement(option4).click().doubleClick().build().perform();
		option4.clear();
		option4.sendKeys("Disagree");
		WebElement score3 = driver.findElement(By.xpath("(//input[@formcontrolname='score'])[4]"));
		a.moveToElement(score3).click().doubleClick();
		score3.clear();
		score3.sendKeys("1");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//span[@class='la la-plus-circle text-primary'])[4]")).click();
		WebElement option5 = driver.findElement(By.xpath("(//input[@formcontrolname='label'])[5]"));
		a.moveToElement(option5).click().doubleClick().build().perform();
		option5.clear();
		option5.sendKeys("Strongly Disagree");
		WebElement score4 = driver.findElement(By.xpath("(//input[@formcontrolname='score'])[5]"));
		a.moveToElement(score4).click().doubleClick();
		score4.clear();
		score4.sendKeys("0");
		Thread.sleep(3000);
		driver.findElement(By.id("tab-label")).click();
		Evaluationpojo eb=new Evaluationpojo();
		eb.getAdvance().click();
	
		
		
	/*	WebElement advanced = driver.findElement(By.xpath("//i[@class='fa fa-plus']"));
		js.executeScript("arguments[0].click();", advanced);
		// edit questions 
		WebElement section = driver.findElement(By.xpath("//span[text()='Question Section']"));
		js.executeScript("arguments[0].click();",section );
		driver.findElement(By.xpath("//span[text()='Course Wise Questions ']")).click();
		Thread.sleep(3000);
		
	   WebElement question = driver.findElement(By.xpath("(//span[text()='Enter text here...'])[2]"));
	   a.moveToElement(question).click().doubleClick().build().perform();
	  //  js.executeScript("arguments[0].click();",question );
	  
		//r.keyPress(KeyEvent.VK_CONTROL);
		//r.keyPress(KeyEvent.VK_C);
		//a.moveToElement(question).build().perform();
		
		//(//span[@class='angular-editor-placeholder'])[2]*/
			
	
	}
	

	@Test(enabled = false)
	private void TC004() throws InterruptedException {
		// create a template 
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();

		// login to the system
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Dave");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Demo!@3PwdAa");
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();

		// click on menu button
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();

		// Choose Evaluation Application from the grid /list
		driver.findElement(By.xpath("//span[text()='Evaluation']")).click();
		driver.findElement(By.xpath("//span[text()='Settings']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Questionnaires']")).click();
		driver.navigate().refresh();
		driver.get("https://demo.creatrixcampus.com/settings/courseevaluationtemplate/list");
		// click on add button
		WebElement add = driver.findElement(By.xpath("//i[@class='flaticon2-plus']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", add);
		// enter a template name 
		Actions a = new Actions(driver);
		WebElement template = driver.findElement(By.xpath("//mat-label[text()='Template Name']"));
		a.moveToElement(template).click().sendKeys("TEST BANK TEMPLATE").build().perform();
		Thread.sleep(3000);
		// enter a descripe 
		WebElement descripe = driver.findElement(By.xpath("//span[text()='Enter text here...']"));
		a.moveToElement(descripe).click().sendKeys("TEST BANK TEMPLATE").build().perform();
		Thread.sleep(3000);
		//choose a template type 
		WebElement type = driver.findElement(By.xpath("(//span[text()='Template Type'])[2]"));
		js.executeScript("arguments[0].click();", type);
		driver.findElement(By.xpath("//span[text()='System Templates ']")).click();
		Thread.sleep(3000);
		WebElement submit = driver.findElement(By.xpath("//button[text()=' Submit ']"));
		js.executeScript("arguments[0].click();",submit);
	}
	@Test(enabled = false)
	private void TC05() throws InterruptedException {
		//     question map in template 
				WebDriverManager.chromedriver().setup();
				WebDriver driver = new ChromeDriver();
				driver.get("https://demo.creatrixcampus.com/auth/login");
				driver.manage().window().maximize();

				// login to the system
				driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Dave");
				driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Demo!@3PwdAa");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[text()='LOGIN']")).click();

				// click on menu button
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();
				driver.navigate().refresh();
				driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();

				// Choose Evaluation Application from the grid /list
				driver.findElement(By.xpath("//span[text()='Evaluation']")).click();
				Thread.sleep(3000);
				// click on template 
				driver.findElement(By.xpath("//span[text()='Template']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//span[@class='mat-tooltip-trigger weight-500 ng-star-inserted'])[1]")).click();
				Thread.sleep(3000);
				// click question and choose a question bank  name 
				WebElement question = driver.findElement(By.xpath("//mat-label[text()='Question Bank']"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", question);
				Thread.sleep(3000);
				Actions a = new Actions(driver);
				a.moveToElement(question).sendKeys(Keys.PAGE_DOWN).build().perform();
		        WebElement select = driver.findElement(By.xpath("//span[text()=' Test bank Question ']"));
		        js.executeScript("arguments[0].click();", select);
		        Thread.sleep(3000);
		        WebElement check = driver.findElement(By.xpath("//input[@type='checkbox']"));
		        js.executeScript("arguments[0].click();", check);
		        
		        
	}
	@Test(enabled = false)
	private void TC06() throws InterruptedException {
		// create a planner 
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();

		// login to the system
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Dave");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Demo!@3PwdAa");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();

		// click on menu button
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();

		// Choose Evaluation Application from the grid /list
		driver.findElement(By.xpath("//span[text()='Evaluation']")).click();
		Thread.sleep(3000);
	    driver.findElement(By.xpath("//span[text()='Planner']")).click();
	    Thread.sleep(3000);
	    WebElement add = driver.findElement(By.xpath("//i[@class='flaticon2-plus']"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", add);
		Actions a = new Actions(driver);
        // enter the name 
		WebElement evaluation = driver.findElement(By.xpath("//mat-label[text()='Evaluation Name']"));
		a.moveToElement(evaluation).click().sendKeys("Test Evaluation planner").build().perform();
		Thread.sleep(3000);
		//click on submit button 
		WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));
		js.executeScript("arguments[0].click();",submit);
			
	}
	
	@Test(enabled = false)
	private void TCO6() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();

		// login to the system
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Dave");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Demo!@3PwdAa");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();

		// click on menu button
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();

		// Choose Evaluation Application from the grid /list
		driver.findElement(By.xpath("//span[text()='Evaluation']")).click();
		Thread.sleep(3000);
	    driver.findElement(By.xpath("//span[text()='Planner']")).click();
	    Thread.sleep(3000);
	    WebElement add = driver.findElement(By.xpath("(//span[@class='mat-tooltip-trigger weight-500 ng-star-inserted'])[1]"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", add);
		// choose startdate 
		WebElement start = driver.findElement(By.xpath("	//mat-label[text()='Start Date']"));
		js.executeScript("arguments[0].click();", start);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[@class='owl-dt-control-content owl-dt-control-button-content'])[2]")).click();
		driver.findElement(By.xpath("//span[text()='2024']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Mar']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[text()='26'])[2]")).click();
		Thread.sleep(3000);
		WebElement hour = driver.findElement(By.xpath("//input[@class='owl-dt-timer-input']"));
		Actions a = new Actions(driver);
		a.moveToElement(hour).click().doubleClick().sendKeys("10").build().perform();
		Thread.sleep(3000);
		WebElement minute = driver.findElement(By.xpath("//span[text()='Minute']"));
		a.moveToElement(minute).click().doubleClick().sendKeys("05").build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Set']")).click();
		//choose end date 
		WebElement end = driver.findElement(By.xpath("	//mat-label[text()='End Date']"));
		js.executeScript("arguments[0].click();", end);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[@class='owl-dt-control-content owl-dt-control-button-content'])[2]")).click();
		driver.findElement(By.xpath("//span[text()='2024']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Mar']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='31']")).click();
		Thread.sleep(3000);
		WebElement hour1 = driver.findElement(By.xpath("//input[@class='owl-dt-timer-input']"));
		Thread.sleep(3000);
		a.moveToElement(hour1).click().doubleClick().sendKeys("4").build().perform();
		WebElement minute1 = driver.findElement(By.xpath("//span[text()='Minute']"));
		a.moveToElement(minute1).click().doubleClick().sendKeys("05").build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Set']")).click();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	}
		
	
		
		
		
		
	
		
		

		
		
		
		
		
		

	
	



	
	


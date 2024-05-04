package org.campus;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

import javax.security.auth.login.LoginContext;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.dockerjava.transport.DockerHttpClient.Request.Method;
import com.google.gson.annotations.Until;

import io.github.bonigarcia.wdm.WebDriverManager;

public class courseevaluation {
	
	
WebDriver driver;
ExtentReports extent;
ExtentSparkReporter report;
ExtentTest test;



	@BeforeSuite
	public void launch() {
		extent=new ExtentReports();
		report=new ExtentSparkReporter("testreport.html");
		extent.attachReporter(report);
		report.config().setReportName("Course Evaluation Module ");
		report.config().setTheme(Theme.STANDARD);
		report.config().setDocumentTitle("Automation report ");
		
		

	}
	@AfterSuite
	public void closebrowesr() {
		driver.quit();
		extent.flush();
	
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult testresult) throws IOException{
		if (testresult.getStatus()==ITestResult.FAILURE) {
			//String datename=new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
			test.log(Status.FAIL,MarkupHelper.createLabel("test case failed",ExtentColor.BLUE));
			test.log(Status.FAIL, testresult.getThrowable());
			test.log(Status.FAIL, testresult.getName());
			TakesScreenshot tk=(TakesScreenshot)driver;
			File screen = tk.getScreenshotAs(OutputType.FILE);
			File f=new File("failed.png");
			FileUtils.copyDirectory(screen, f);
			test.addScreenCaptureFromPath("failed.png");	
		}
	
	
		else if (testresult.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "testcase skip");
			
		}
		else {
			test.log(Status.PASS, "test case pass");
		}
			
	}
	
	
	@Test(priority = 1,description = "log in functionality")
	private void TC01() throws InterruptedException {
		test = extent.createTest("verify the login functionality").assignAuthor("satheesh").assignCategory("smoke")
				.assignDevice("chrome");
		// navigate to Question bank page
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 test.pass("navigate to logg in page ");
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();
		
		// login to the system 
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Dave");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Demo!@3PwdAa");
	      WebElement login = driver.findElement(By.xpath("//button[text()='LOGIN']"));
	      test.pass("succesfully log in");
	      login.click();
		Thread.sleep(5000);
	}
	

	@Test(priority = 2)
	private void TC02() throws InterruptedException, AWTException {
		// create a question bank
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();
		
		test=extent.createTest("Create a question bank ");
		test.pass(" login to the system");
		
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Dave");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Demo!@3PwdAa");
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
		//click on menu button 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();
		driver.navigate().refresh();
		driver.findElement(By.xpath("(//div[@class='dropdown-toggle kt-header__topbar-wrapper'])[5]")).click();

		// Choose Evaluation Application from the grid /list
		driver.findElement(By.xpath("//span[text()='Evaluation']")).click();
		driver.findElement(By.xpath("//span[text()='Settings']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Question Library']")).click();
		driver.navigate().refresh();
		
		// go to question bank 
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
		name.sendKeys("Test bank Question3");
		Thread.sleep(3000);
		Actions a =new Actions(driver);
		WebElement descripe = driver.findElement(By.xpath("//span[@class='angular-editor-placeholder']"));
		a.moveToElement(descripe).click().sendKeys("Test bank Question3").build().perform();
		Thread.sleep(3000);
		//js.executeScript("arguments[0].setAttribute('value','Test bank Question')", descripe);
		driver.findElement(By.xpath("//button[text()=' Submit ']")).click();
		Thread.sleep(3000);
		driver.quit();
		test.pass("succesfully create a question ");
		
	}	

	@Test(priority = 3,enabled = false)
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
		driver.findElement(By.xpath("//span[text()='Evaluation']")).click();
		driver.findElement(By.xpath("//span[text()='Settings']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Question Library']")).click();
		driver.navigate().refresh();
		driver.get("https://demo.creatrixcampus.com/questionbuilder/questionbank/list");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// click on setting icon
		WebElement seting = driver.findElement(By.xpath("(//span[@class='mat-button-focus-overlay'])[9]"));
		js.executeScript("arguments[0].click();", seting);
		Thread.sleep(2000);	
		Actions a = new Actions(driver);
		Robot r=new Robot();
		WebDriverWait wait =new WebDriverWait(driver, 10);
		// click on delete
		//driver.findElement(By.xpath("//i[@class='flaticon-delete']")).click();
		//driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']")).click();
		Thread.sleep(3000);
		//currently close
		 // click on add
		 WebElement add = driver.findElement(By.xpath("//i[@class='fa fa-caret-down ml-3']"));
		 js.executeScript("arguments[0].click();", add);
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//button[text()=' Single Choice ']")).click();
        // click on edit
		WebElement edit = driver.findElement(By.xpath("//i[@class='flaticon-edit']"));
		edit.click();
		Thread.sleep(3000);
		driver.findElement(By.id("tab-options")).click();
        //Create  the single  choice question 
		
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
		//click on question
		driver.findElement(By.id("tab-label")).click();
		//click on adavance
		WebElement advanced = driver.findElement(By.xpath("//i[@class='fa fa-plus']"));
		js.executeScript("arguments[0].click();", advanced);
		Thread.sleep(3000);
		// choose a  questions section
		WebElement section = driver.findElement(By.xpath("//span[text()='Question Section']"));
		js.executeScript("arguments[0].click();",section );
		driver.findElement(By.xpath("//span[text()='Course Wise Questions ']")).click();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_A);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='angular-editor-textarea'])[2]")));
		until.sendKeys("python is a good course");
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		// enter a mark 
		WebElement mark = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-placeholder='Marks / Score *']")));
		mark.sendKeys("5");
		js.executeScript("window.scrollBy(0,600)", "");
		Thread.sleep(3000);
		WebElement submit = driver.findElement(By.xpath("//button[text()=' Submit ']"));
		submit.click();
		Thread.sleep(3000);
		
		
		// create a short answer
		// click on add short answer
		WebElement add1 = driver.findElement(By.xpath("//i[@class='fa fa-caret-down ml-3']"));
		js.executeScript("arguments[0].click();", add1);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()=' Short Answer ']")).click();
		Thread.sleep(3000);
		// click on edit
		WebElement edit1 = driver.findElement(By.xpath("(//i[@class='flaticon-edit'])[1]"));
		edit1.click();
		Thread.sleep(3000);
		driver.findElement(By.id("tab-options")).click();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("1");
		//click on question 
		driver.findElement(By.id("tab-label")).click();
		//click on adavance
		WebElement advanced1 = driver.findElement(By.xpath("//i[@class='fa fa-plus']"));
		js.executeScript("arguments[0].click();", advanced1);
		// choose a questions section
		WebElement section1 = driver.findElement(By.xpath("//span[text()='Question Section']"));
		js.executeScript("arguments[0].click();", section1);
		driver.findElement(By.xpath("//span[text()='Course Wise Questions ']")).click();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_A);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement until1 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='angular-editor-textarea'])[2]")));
		until1.sendKeys("what is python short answer");
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		// enter a mark
		WebElement mark1 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-placeholder='Marks / Score *']")));
		mark1.sendKeys("5");
		js.executeScript("window.scrollBy(0,600)", "");
		Thread.sleep(3000);
		WebElement submit1 = driver.findElement(By.xpath("//button[text()=' Submit ']"));
		submit1.click();
		Thread.sleep(3000);
		// create a rating 
		// click on rating 
		WebElement add2 = driver.findElement(By.xpath("//i[@class='fa fa-caret-down ml-3']"));
		js.executeScript("arguments[0].click();", add2);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()=' Rating ']")).click();
		Thread.sleep(3000);
		// click on edit
		js.executeScript("window.scrollBy(0,-100)", "");
		WebElement edit2 = driver.findElement(By.xpath("(//i[@class='flaticon-edit'])[1]"));
		js.executeScript("arguments[0].click();", edit2);
		Thread.sleep(3000);
		driver.findElement(By.id("tab-options")).click();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("1");
		//click on question 
		driver.findElement(By.id("tab-label")).click();
		//click on adavance
		WebElement advanced2 = driver.findElement(By.xpath("//i[@class='fa fa-plus']"));
		js.executeScript("arguments[0].click();", advanced2);
		// choose a questions section
		WebElement section2 = driver.findElement(By.xpath("//span[text()='Question Section']"));
		js.executeScript("arguments[0].click();", section2);
		driver.findElement(By.xpath("//span[text()='Course Wise Questions ']")).click();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_A);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement until2 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='angular-editor-textarea'])[2]")));
		until2.sendKeys("Rating of python");
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		// enter a mark
		WebElement mark2 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-placeholder='Marks / Score *']")));
		mark2.sendKeys("5");
		js.executeScript("window.scrollBy(0,1500)", "");
		Thread.sleep(3000);
		WebElement submit2 = driver.findElement(By.xpath("//button[text()=' Submit ']"));
		submit2.click();
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,-500)", "");
		
		// create a long answer
		// click on long answer
		WebElement add3 = driver.findElement(By.xpath("//i[@class='fa fa-caret-down ml-3']"));
		js.executeScript("arguments[0].click();", add3);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()=' Long Answer ']")).click();
		Thread.sleep(3000);
		// click on edit
		js.executeScript("window.scrollBy(0,-500)", "");
		WebElement edit3 = driver.findElement(By.xpath("(//i[@class='flaticon-edit'])[1]"));
		js.executeScript("arguments[0].click();", edit3);
		Thread.sleep(3000);
		driver.findElement(By.id("tab-options")).click();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("1");
		//click on question 
		driver.findElement(By.id("tab-label")).click();
		//click on adavance
		WebElement advanced3 = driver.findElement(By.xpath("//i[@class='fa fa-plus']"));
		js.executeScript("arguments[0].click();", advanced3);
		// choose a questions section
		WebElement section3 = driver.findElement(By.xpath("//span[text()='Question Section']"));
		js.executeScript("arguments[0].click();", section3);
		driver.findElement(By.xpath("//span[text()='Course Wise Questions ']")).click();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_A);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement until3 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='angular-editor-textarea'])[2]")));
		until3.sendKeys("elaborate of python ");
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		// enter a mark
		WebElement mark3 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-placeholder='Marks / Score *']")));
		mark3.sendKeys("5");
		js.executeScript("window.scrollBy(0,1500)", "");
		Thread.sleep(3000);
		WebElement submit3 = driver.findElement(By.xpath("//button[text()=' Submit ']"));
		submit3.click();
		Thread.sleep(3000);
		
		// create a matrix 
		// click on matrix 
		
		WebElement add4 = driver.findElement(By.xpath("//i[@class='fa fa-caret-down ml-3']"));
		js.executeScript("arguments[0].click();", add4);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()=' Matrix ']")).click();
		Thread.sleep(3000);
		// click on edit
		js.executeScript("window.scrollBy(0,-1000)", "");
		WebElement edit4 = driver.findElement(By.xpath("(//i[@class='flaticon-edit'])[1]"));
		js.executeScript("arguments[0].click();", edit4);
		Thread.sleep(3000);
		driver.findElement(By.id("tab-options")).click();
		
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement option20 = driver.findElement(By.xpath("(//input[@formcontrolname='label'])[1]"));
		option20.sendKeys("Strongly agree");
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement score11 = driver.findElement(By.xpath("(//input[@formcontrolname='score'])[1]"));
		score11.sendKeys("4");
		Thread.sleep(3000);
		
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement option21 = driver.findElement(By.xpath("(//input[@formcontrolname='label'])[2]"));
		option21.sendKeys("Agree");
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement score12 = driver.findElement(By.xpath("(//input[@formcontrolname='score'])[2]"));
		score12.sendKeys("3");
		Thread.sleep(3000);
		
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement option31 = driver.findElement(By.xpath("(//input[@formcontrolname='label'])[3]"));
		option31.sendKeys("Neutral");
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement score13 = driver.findElement(By.xpath("(//input[@formcontrolname='score'])[3]"));
		score13.sendKeys("2");
		Thread.sleep(3000);
		
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement option41 = driver.findElement(By.xpath("(//input[@formcontrolname='label'])[4]"));
		option41.sendKeys("Disagree");
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement score14 = driver.findElement(By.xpath("(//input[@formcontrolname='score'])[4]"));
		score14.sendKeys("1");
		Thread.sleep(3000);
	
		driver.findElement(By.xpath("(//span[@class='la la-plus-circle text-primary'])[4]")).click();
		WebElement option51 = driver.findElement(By.xpath("(//input[@formcontrolname='label'])[5]"));
		option51.sendKeys("Strongly Disagree");
		WebElement score15 = driver.findElement(By.xpath("(//input[@formcontrolname='score'])[5]"));
		score15.sendKeys("0");
		Thread.sleep(3000);
		
		// click on question
		driver.findElement(By.id("tab-label")).click();
		
		// click on adavance
		WebElement advanced4 = driver.findElement(By.xpath("//i[@class='fa fa-plus']"));
		js.executeScript("arguments[0].click();", advanced4);
		
		// choose a questions section
		WebElement section4 = driver.findElement(By.xpath("//span[text()='Question Section']"));
		js.executeScript("arguments[0].click();", section4);
		driver.findElement(By.xpath("//span[text()='Instructor Questions ']")).click();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_A);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		WebElement until4 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='angular-editor-textarea'])[2]")));
		until4.sendKeys("instructor");
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
		// enter a mark
		WebElement mark4 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-placeholder='Marks / Score *']")));
		mark4.sendKeys("5");
		js.executeScript("window.scrollBy(0,1000)", "");
		Thread.sleep(3000);
		WebElement submit4 = driver.findElement(By.xpath("//button[text()=' Submit ']"));
		submit4.click();
		Thread.sleep(5000);
		driver.quit();
		
	
		
	}
	@Test( priority= 4,enabled = false)
	private void TC04() throws InterruptedException {
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
		a.moveToElement(template).click().sendKeys("TEST BANK TEMPLATE3").build().perform();
		Thread.sleep(3000);
		// enter a descripe 
		WebElement descripe = driver.findElement(By.xpath("//span[text()='Enter text here...']"));
		a.moveToElement(descripe).click().sendKeys("TEST BANK TEMPLATE3").build().perform();
		Thread.sleep(3000);
		//choose a template type 
		WebElement type = driver.findElement(By.xpath("(//span[text()='Template Type'])[2]"));
		js.executeScript("arguments[0].click();", type);
		driver.findElement(By.xpath("//span[text()='System Templates ']")).click();
		Thread.sleep(3000);
		WebElement submit = driver.findElement(By.xpath("//button[text()=' Submit ']"));
		js.executeScript("arguments[0].click();",submit);
		Thread.sleep(3000);
		driver.quit();
	}
	@Test(priority = 5 ,enabled = false)
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
		        WebElement select = driver.findElement(By.xpath("//span[text()=' Test bank Question3 ']"));
		        js.executeScript("arguments[0].click();", select);
		        Thread.sleep(3000);
		        // click on check box
		        WebElement check1 =driver.findElement(By.id("mat-checkbox-1-input"));
		        js.executeScript("arguments[0].click();", check1);
		        
		        WebElement check2 = driver.findElement(By.id("mat-checkbox-2-input"));
		        js.executeScript("arguments[0].click();", check2);
		        
		        WebElement check3 = driver.findElement(By.id("mat-checkbox-3-input"));
		        js.executeScript("arguments[0].click();", check3);
		        
		        
		        WebElement check4 = driver.findElement(By.id("mat-checkbox-4-input"));
		        js.executeScript("arguments[0].click();", check4);
		        
		        
		        WebElement check5 = driver.findElement(By.id("mat-checkbox-5-input"));
		        js.executeScript("arguments[0].click();", check5);
		        Thread.sleep(3000);
		        driver.quit();
	}
	@Test(priority = 6,enabled = false)
	private void TC06() throws InterruptedException, AWTException {
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
		Robot r=new Robot();
		Actions a = new Actions(driver);
        // enter the name 
		WebElement evaluation = driver.findElement(By.xpath("//mat-label[text()='Evaluation Name']"));
		a.moveToElement(evaluation).click().sendKeys("Test evaluation planner3").build().perform();
		Thread.sleep(3000);
		//click on submit button 
		WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));
		js.executeScript("arguments[0].click();",submit);
		Thread.sleep(3000);
		
		// choose startdate 
		WebElement start = driver.findElement(By.xpath("	//mat-label[text()='Start Date']"));
		js.executeScript("arguments[0].click();", start);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[@class='owl-dt-control-content owl-dt-control-button-content'])[2]")).click();
		driver.findElement(By.xpath("//span[text()='2024']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Mar']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[text()='28'])[2]")).click();
		Thread.sleep(3000);
		WebElement hour = driver.findElement(By.xpath("//input[@class='owl-dt-timer-input']"));
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
		driver.findElement(By.xpath("//span[text()='Apr']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='27']")).click();
		Thread.sleep(3000);
		WebElement hour1 = driver.findElement(By.xpath("//input[@class='owl-dt-timer-input']"));
		Thread.sleep(3000);
		a.moveToElement(hour1).click().doubleClick().sendKeys("4").build().perform();
		WebElement minute1 = driver.findElement(By.xpath("//span[text()='Minute']"));
		a.moveToElement(minute1).click().doubleClick().sendKeys("05").build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Set']")).click();
		
		// choose on academic year 
		driver.findElement(By.xpath("//span[text()='Academic Year']")).click();
		driver.findElement(By.xpath("//span[text()='AY 2020-2021 ']")).click();
		Thread.sleep(3000);
		
		// choose a semester 
		WebElement sem = driver.findElement(By.xpath("//div[text()='Semester']"));
		a.moveToElement(sem).click().build().perform();
		js.executeScript("arguments[0].click();", sem);
		driver.findElement(By.xpath("//span[text()='Even Semester 2022']")).click();
		js.executeScript("window.scrollBy(0,1000)", "");
		//click on save 
		WebElement save = driver.findElement(By.id("form-saveNext"));
		js.executeScript("arguments[0].click();", save);
		//choose a template 
		driver.findElement(By.xpath("(//div[@class='desc ellipsis font-12 mb-2'])[2]")).click();
		// click on next
		driver.findElement(By.xpath("(//button[@class='btn btn-theme-primary btn-bold ng-star-inserted'])[1]")).click();
		Thread.sleep(3000);
		//click on next 
		WebElement nexte = driver.findElement(By.xpath("(//button[@class='btn btn-theme-primary btn-bold ng-star-inserted'])[2]"));
		js.executeScript("arguments[0].click();", nexte);
		Thread.sleep(3000);
		// program and course checkbox 
		
		WebElement bcom = driver.findElement(By.xpath("(//span[@class='mat-checkbox-inner-container'])[1]"));
		js.executeScript("arguments[0].click();", bcom);
		WebElement account = driver.findElement(By.xpath("(//span[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin'])[1]"));
		js.executeScript("arguments[0].click();", account);
		Thread.sleep(3000);
		WebElement manage = driver.findElement(By.xpath("(//span[@class='mat-checkbox-inner-container'])[3]"));
		js.executeScript("arguments[0].click();", manage);
		WebElement english = driver.findElement(By.xpath("(//span[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin'])[1]"));
		js.executeScript("arguments[0].click();", english);
		Thread.sleep(3000);
		WebElement mba = driver.findElement(By.xpath("(//span[@class='mat-checkbox-inner-container'])[4]"));
		js.executeScript("arguments[0].click();", mba);
		WebElement english1 = driver.findElement(By.xpath("(//span[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin'])[1]"));
		js.executeScript("arguments[0].click();", english1);
		driver.findElement(By.xpath("(//button[@class='btn btn-theme-primary btn-bold ng-star-inserted'])[3]")).click();
		// choose a template 
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='ng-arrow-wrapper'])[2]")));
		until.click();
		Thread.sleep(2000);
		WebElement email = driver.findElement(By.xpath("//span[text()='Course Evaluation Email']"));
		js.executeScript("arguments[0].click();", email);
		// click on next
		WebElement nex = driver.findElement(By.xpath("(//button[@class='btn btn-theme-primary btn-bold ng-star-inserted'])[3]"));
		js.executeScript("arguments[0].click();", nex);
		driver.findElement(By.xpath("(//button[@class='btn btn-theme-primary btn-bold ng-star-inserted'])[4]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()=' View Participants ']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//i[@class='la la-close'])[3]")).click();
		Thread.sleep(3000);
		//click on send 
		WebElement send = driver.findElement(By.xpath("//button[text()=' Send']"));
		js.executeScript("arguments[0].click();", send);
		Thread.sleep(3000);
		driver.quit();
	
	}
	@Test(priority = 7)
	private void TC07() throws InterruptedException, AWTException {
		
		WebDriverManager.chromedriver().setup();
         WebDriver driver = new ChromeDriver();
		driver.get("https://demo.creatrixcampus.com/auth/login");
		driver.manage().window().maximize();
		
		
		test=extent.createTest("student ");
		test.pass("navigate student page ");
        
		// login to the system
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("CGU007773");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("d73f0c70a10c8de3fb6da9adadfd0d77");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
		Thread.sleep(5000);
		Actions a = new Actions(driver);
		Robot r=new Robot();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='links'])[8]")));
		a.moveToElement(until).click().build().perform();
		Thread.sleep(2000);
		test.pass("question bank");
		 driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
		WebElement instru = driver.findElement(By.xpath("(//span[@class='mat-ripple-element mat-radio-persistent-ripple'])[1]"));
		js.executeScript("arguments[0].click();", instru);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		WebElement ela = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//textarea[@formcontrolname='c_answered_content'])[1]")));
		ela.sendKeys("good");
		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(3000);
		WebElement rating = driver.findElement(By.xpath("(//span[text()='★'])[5]"));
		js.executeScript("arguments[0].click();", rating);
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(3000);
		WebElement ag = driver.findElement(By.xpath("(//span[@class='mat-radio-label-content'])[7]"));
		js.executeScript("arguments[0].click();", ag);
		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//textarea[@formcontrolname='c_answered_content'])[2]")));
		el.sendKeys("ok");
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(3000);
        WebElement submit = driver.findElement(By.xpath("//div[text()=' Submit ']"));
		js.executeScript("arguments[0].click();", submit);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		Thread.sleep(3000);
		WebElement ok = driver.findElement(By.xpath("//button[text()='OK']"));
		js.executeScript("arguments[0].click();", ok);
		Thread.sleep(5000);
		WebElement until1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='links'])[8]")));
		a.moveToElement(until1).click().build().perform();
		test.pass("succesfully attend the question ");
		
}
	}

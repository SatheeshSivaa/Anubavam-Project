package org.campus;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;


import io.github.bonigarcia.wdm.WebDriverManager;

public class exam {
	
WebDriver driver;
ExtentReports extent;
ExtentSparkReporter report;
ExtentTest test;



	@BeforeSuite
	public void launch() {
		extent=new ExtentReports();
		report=new ExtentSparkReporter("evaluation.html");
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
	@Parameters("browser")

	@BeforeMethod
	public void setUp() throws Exception {
		String browser="firefoxbrowser";
		
		if (browser.equals("chromebrowser")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}
		else if (browser.equals("firefoxbrowser")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else  {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		
		
		
		
		
		
       
		
       // WebDriverManager.chromedriver().setup();
       // driver = new ChromeDriver();
       // driver = new FirefoxDriver();
        // driver = new EdgeDriver();
	}

	
	
@AfterMethod
	public void checkresult(ITestResult testresult) throws IOException {
	    if (testresult.getStatus() == ITestResult.FAILURE) {
	        test.log(Status.FAIL, MarkupHelper.createLabel("Test case failed", ExtentColor.RED));
	        test.log(Status.FAIL, testresult.getThrowable());
	        test.log(Status.FAIL, testresult.getName());
	        
	        TakesScreenshot tk = (TakesScreenshot) driver;
	        File source = tk.getScreenshotAs(OutputType.FILE);
	        
	        
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        String times = formatter.format(new Date());
	        String screenshotName = "failed_" + times + ".png";
	        String screenshotPath = "D://" + screenshotName; // Define path for the screenshot
	       
	       // String screenshotPath = "D://" + screenshotName; // Define path for the screenshot
	        File destination = new File(screenshotPath);
	        
	        // Copy the screenshot to the defined path
	        FileUtils.copyFile(source, destination);
	        
	        // Attach the screenshot to the repor
	        test.addScreenCaptureFromPath(screenshotPath);

	}
	         
		else if (testresult.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "testcase skip");
			
		}
		else {
			test.log(Status.PASS, "test case pass");
		}
			
}

	@Test(priority = 1,description = "log in functionality")
	private void TC01() throws InterruptedException, IOException {
    	
		test = extent.createTest("verify the login functionality").assignAuthor("satheesh").assignCategory("smoke")
				.assignDevice("chrome");
		//test=extent.createTest("verify").
		// navigate to Question bank page
		//WebDriverManager.chromedriver().setup();
		// driver = new ChromeDriver();
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
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		int size = elements.size();
		System.out.println(size);
	}
	@Test(priority = 2,enabled = false)
	private void TC02() throws InterruptedException, AWTException {
		// create a question bank
		//WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
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
	@Test(priority = 7,enabled = false)
	private void TC07() throws InterruptedException, AWTException {
		
		//WebDriverManager.chromedriver().setup();
        // WebDriver driver = new ChromeDriver();
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

	
	
	


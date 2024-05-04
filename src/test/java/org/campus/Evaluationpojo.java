package org.campus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Evaluationpojo  {
	WebDriver driver;
	
	public Evaluationpojo() {
		PageFactory.initElements(driver, this);
	}
	//click on adavance 
	@FindBy(xpath ="//i[@class='fa fa-plus']") 
	private WebElement advance;
	@FindBy(xpath = "//span[text()='Question Section']")
	private WebElement section;
	@FindBy(xpath = "//span[text()='Course Wise Questions ']")
	private WebElement course;
	public WebElement getAdvance() {
		return advance;
	}
	public WebElement getSection() {
		return section;
	}
	public WebElement getCourse() {
		return course;
	}
		
		
		
	}


package homework2;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//@Test(groups={"smoke"})
public class HomeWork2 {
	private WebDriver driver;
	private String url;
	private int waitTime = 10;
	

	public WebElement getElement(WebDriver dr, By by)
	{
		WebDriverWait wait = new WebDriverWait(dr,waitTime);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		
	}
	
	// define the method for switching to the latest pop-up window
	public void switchToLatestWindow(WebDriver dr)
	{
		 Set<String> windows = dr.getWindowHandles();
		 if(windows!=null){
			 String lastWindow = (String) windows.toArray()[windows.size()-1];
			 dr.switchTo().window(lastWindow);
			 dr.manage().window().maximize();
		 }
	}
	
  @BeforeTest(alwaysRun = true)
  public void setUp(){
	  driver = new FirefoxDriver();
	  url ="http://www.baidu.com";
  }
  
  
  @Test(priority = 0,groups={"smoke"})
  public void SearchInBaidu() {
	  
	  driver.get(url);
	  driver.manage().window().maximize();
	  
	  // find the input-box in Baidu page type "163" into the input-box
	  WebElement inputBox = getElement(driver,By.id("kw1"));
	  inputBox.sendKeys("163");
	  
	  // find the submit-button and click on it
	  WebElement button = getElement(driver,By.id("su1"));
	  button.click();

	  
	  // find the "Wang Yi Xin Wen" link and click it 
      WebElement newsLink = getElement(driver,By.xpath("//div[@id='3']/h3/a[1]"));
      newsLink.click();
	  
  }
  
  @Test(priority = 1, groups = {"smoke"})
  public void AccessNewsIndex(){
	  // switch to the "163 News" page
	  switchToLatestWindow(driver);
	  
	  // find the first news link and click it 
	  WebElement firstNews = getElement(driver,By.xpath("//div[@id='news']/h2/a[1]"));
	  firstNews.click();
  }
  
  @Test(priority = 2, groups = {"smoke"})
  public void AccessNewsCenter(){
	  // switch to the page of the first news
	  switchToLatestWindow(driver);

	  // find the textarea and type "Hello World" in it
	  WebElement txtArea = getElement(driver,By.xpath("//textarea[@rows='8']"));
	  txtArea.sendKeys("Hello World!");
	  
	  // find the submit button and click on it
	  WebElement button = getElement(driver,By.xpath("//div[@class='tie-author-login']/a"));
      button.click();
	  
      // switch to the pop-up alert and click OK 
	  Alert alert = driver.switchTo().alert();
	  alert.accept();
  }
  
  @AfterTest(alwaysRun = true)
  public void tearDown(){
	  driver.quit();
  }
}

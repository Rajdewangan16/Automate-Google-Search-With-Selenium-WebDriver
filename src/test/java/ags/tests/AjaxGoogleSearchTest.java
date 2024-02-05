package ags.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AjaxGoogleSearchTest {

	@Test
	public void googleSearch() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		
		driver.findElement(By.name("q")).sendKeys("selenium tutorial" , Keys.ENTER);

		//wait for suggestions
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));

		WebElement list=driver.findElement(By.id("search"));
		List<WebElement> link = list.findElements(By.tagName("a"));
		int linkCount = link.size();
		
		Actions a = new Actions(driver);
		
		for(int i=0; i<linkCount ; i++) {
			a.moveToElement(link.get(i)).keyDown(Keys.CONTROL).click().build().perform();
			if(i == 2) {
				break;
			}
		}
	}
}

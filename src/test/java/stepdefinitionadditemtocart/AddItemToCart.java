package stepdefinitionadditemtocart;
import static org.junit.Assert.assertEquals;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
public class AddItemToCart {
	WebDriver driver;	
	WebDriverWait wait;
	@Given("the user is on the eBay homepage")	
	public void givenTheUserIsOnTheEBayHomepage() {	
		
		WebDriverManager.chromedriver().setup();	
		ChromeOptions options = new ChromeOptions();	
		options.addArguments("--remote-allow-origins=*");		
		driver = new ChromeDriver(options);	
		driver.get("https://www.ebay.com");	
		driver.manage().window().maximize();	
	}

	@When("the user searches for {string}")	
	public void whenTheUserSearchesFor(String searchTerm) {		
		WebElement searchBox = driver.findElement(By.id("gh-ac"));
		searchBox.sendKeys(searchTerm);		
		WebElement searchButton = driver.findElement(By.id("gh-search-btn"));	
		searchButton.click();	
	}
	
	@When("the user clicks on the first item in the search results")	
	public void whenTheUserClicksOnTheFirstItem() {	
		List<WebElement> firstItem = driver.findElements(By.xpath("//*[contains(@id,'item')]/div/div[2]/a/div/span"));		
		firstItem.get(0).click();	
	}
	@When("the user clicks on the \"Add to cart\" button")	
	public void whenTheUserClicksOnAddToCart() throws InterruptedException {
		Set<String> allWindowHandles = driver.getWindowHandles();	
		String originalTab = driver.getWindowHandle();
		// Switch to the new tab	
		for (String handle : allWindowHandles) {		
			if (!handle.equals(originalTab)) {			
				driver.switchTo().window(handle);			
				break; // Exit the loop after switching to the new tab				
			}
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));		
		WebElement addToCartButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("atcBtn_btn_1")));		
		addToCartButton.click();	
}
	@Then("the shopping cart should display {string} item")	
	public void thenTheShoppingCartShouldDisplayItem(String expectedItemCount) {
	WebElement cartIcon = driver.findElement(By.xpath("//span[@class='gh-cart__icon']"));	
	String cartCount = cartIcon.getText();		
	assertEquals(expectedItemCount, cartCount);		
	driver.quit(); // Close the browser after the test	}
	}
	
}

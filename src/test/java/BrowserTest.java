import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BrowserTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://www.optus.com.au/";

    @BeforeEach
    public void setUp() {
        // Set Chrome Driver Location
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\acer\\Downloads\\chromedriver-win64\\chromedriver.exe");

        // Create a new Driver Instance
        driver = new ChromeDriver();

        // Maximize Screen Size
        driver.manage().window().maximize();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        // Access BASE_URL
        driver.get(BASE_URL);
    }

    @Test
    public void testBrowserActions() {
        // Click Tablets & Watches
        WebElement tabletsElm = driver.findElement(By.cssSelector("[aria-label='Tablets & Watches']"));
        tabletsElm.click();

        // Click iPad
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header__level-3")));
        System.out.println("headerlve3 is displayed");

        driver.findElement(By.partialLinkText("iPad")).click();
        System.out.println("iPad is clicked");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[aria-label='Outright']")));
        WebElement radioInput = driver.findElement(By.cssSelector("input[aria-label='Outright']"));
        radioInput.click();

        // Wait for card list to show
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("par_responsive_container_sd_component_sfcc_de_1814684871")));
        System.out.println("card list shows");

        // Select all cards
        List<WebElement> cardList = driver.findElements(By.cssSelector("div.kUuERK"));
        String price = "0";
        for (WebElement card : cardList) {
            String h4Text = card.findElement(By.tagName("h4")).getText();
            System.out.println(h4Text);
            if (h4Text.equalsIgnoreCase("iPad (9th gen)")) {
                price = card.findElement(By.cssSelector("span.price")).getText();
                System.out.println(price);
                break;
            }
        }

        Assertions.assertEquals("$778", price);

    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}

package azureAutomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.awt.*;
import java.lang.reflect.Method;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AzureAutomation {

  private static final String URL = "https://www.simtech.nl";
  private ChromeDriver chromeDriver;

  @BeforeMethod
  public void setup(Method method) {
    System.setProperty("webdriver.chrome.silentOutput", "true");
    setupTheWebDriver();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox"); // Bypass OS security model
    options.addArguments("--headless");
    options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
    chromeDriver = new ChromeDriver(options);
    chromeDriver.get(URL);
    chromeDriver.manage().window().maximize();
  }

  @AfterMethod
  public void tearDown() {
    chromeDriver.quit();
  }

  private void setupTheWebDriver() {
    WebDriverManager.chromedriver().setup();
  }

  @Test
  public void testAzurePipeline() {
    String expectedResult = "Home IT migration, optimization, and managed services.";
    Assert.assertEquals(chromeDriver.getTitle(), expectedResult);
  }

  @Test
  public void failingTestAzurePipeline() {
    WebElement expectedElement =
        chromeDriver.findElementByXPath("//*[contains(text(),'Privacy Policy')]/..");
    String text = expectedElement.getText();
    Assert.assertTrue(text.contains("2021"));
  }
}

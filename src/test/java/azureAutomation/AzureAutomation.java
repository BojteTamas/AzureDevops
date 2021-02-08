package azureAutomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.lang.reflect.Method;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AzureAutomation {

  private static final String URL = "https://qa-automation-practice.netlify.app/";
  private ChromeDriver chromeDriver;

  @BeforeMethod
  public void setup(Method method) {
    System.setProperty("webdriver.chrome.silentOutput", "true");
    setupTheWebDriver();
    chromeDriver = new ChromeDriver();
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
    System.out.println("Say hello for Azure");
  }

  @Test
  public void failingTestAzurePipeline() {
    Assert.assertFalse(true);
  }
}

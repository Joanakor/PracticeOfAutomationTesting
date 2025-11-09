import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginPageTest {
    WebDriver driver;
    String correctPassword;
    String correctUsername;

    @BeforeMethod
    public void setup() {
        correctUsername = "student";
        correctPassword = "Password123";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @Test(priority = 1)
    public void PositiveLogInTest() {

        driver.findElement(By.id("username")).sendKeys(correctUsername);
        driver.findElement(By.id("password")).sendKeys(correctPassword);
        driver.findElement(By.id("submit")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='post-content']/p/strong")).getText().contains("successfully logged in"));

        Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());
    }

    @Test(priority = 2)
    public void NegativeUsernameTest() {
        String username = "incorrectUser";

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(correctPassword);
        driver.findElement(By.id("submit")).click();

        Assert.assertTrue(driver.findElement(By.id("error")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.id("error")).getText(), "Your username is invalid!");
    }

    @Test(priority = 3)
    public void NegativePasswordTest() {
        String password = "incorrectPassword";

        driver.findElement(By.id("username")).sendKeys(correctUsername);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();

        Assert.assertTrue(driver.findElement(By.id("error")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.id("error")).getText(), "Your password is invalid!");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

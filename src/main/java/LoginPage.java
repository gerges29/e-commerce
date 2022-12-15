import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public WebElement emailPOM (WebDriver driver)
    {
        return driver.findElement(By.id("Email"));
    }

    public WebElement passPOM (WebDriver driver)
    {
        return driver.findElement(By.id("Password"));
    }

    public WebElement passChangePOM (WebDriver driver)
    {
        return driver.findElement(By.xpath("//p[@class=\"content\"]"));

    }

    public void LoginSteps (WebDriver driver)
    {
        driver.findElement(By.className("ico-login")).click();
        emailPOM(driver).sendKeys("Gerges@gmail.com");
        passPOM(driver).sendKeys("gerges");
        driver.findElement(By.xpath("//button[text()='Log in']\n")).click();
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenBrowser {

    WebDriver driver = null;
    LoginPage login;


    @BeforeTest
    public void OpenBrowser() throws InterruptedException {
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.out.println(chromePath);
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();

        login = new LoginPage();

        driver.manage().window().maximize();

        Thread.sleep(3000);
    }

    @Test
    public void Registration()
    {
        driver.navigate().to("https://demo.nopcommerce.com/");
        driver.findElement(By.className("ico-register")).click();
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Gerges");
        driver.findElement(By.id("LastName")).sendKeys("Nashaat");
        //day
        Select day = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthDay\"]")));
        day.selectByVisibleText("Day");
        day.selectByIndex(29);
        //month
        Select month = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthMonth\"]")));
        month.selectByVisibleText("Month");
        month.selectByIndex(1);
        //year
        Select year = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthYear\"]")));
        year.selectByVisibleText("Year");
        year.selectByIndex(90);

        login.emailPOM(driver).sendKeys("Gerges@gmail.com");
        driver.findElement(By.id("Company")).sendKeys("FWD");
        driver.findElement(By.id("Password")).sendKeys("gerges");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("gerges");
        driver.findElement(By.id("register-button")).click();

        //confirmMessage
         String expectedResult = "Your registration completed";
         String actualResult = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();
         Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void Login()
    {
        // login
        driver.navigate().to("https://demo.nopcommerce.com/");
        login.LoginSteps(driver);
    }

    @Test
    public void changePass()
    {
        //login
        driver.navigate().to("https://demo.nopcommerce.com/");
        driver.findElement(By.className("ico-login")).click();
        driver.findElement(By.id("Email")).sendKeys("Gerges@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("gerges");
        driver.findElement(By.xpath("//button[text()='Log in']\n")).click();

        //change pass
        driver.findElement(By.className("ico-account")).click();
        driver.findElement(By.xpath("//a[@href=\"/customer/changepassword\"]")).click();
        driver.findElement(By.id("OldPassword")).click();
        driver.findElement(By.id("OldPassword")).sendKeys("gerges");
        driver.findElement(By.id("NewPassword")).click();
        driver.findElement(By.id("NewPassword")).sendKeys("nashaat");
        driver.findElement(By.id("ConfirmNewPassword")).click();
        driver.findElement(By.id("ConfirmNewPassword")).sendKeys("nashaat");
        driver.findElement(By.xpath("//button[@class=\"button-1 change-password-button\"]")).click();

        //confirmation

        SoftAssert soft = new SoftAssert();

        String expectedResult = "Password was changed";
        String actualResult = login.passChangePOM(driver).getText();
        soft.assertEquals(actualResult,expectedResult);

        soft.assertAll();
    }

    @Test
    public void search() throws InterruptedException {
        //login
        login.LoginSteps(driver);

        //search
        driver.findElement(By.xpath("//input[@id='small-searchterms']\n")).sendKeys("Apple");
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        driver.findElement(By.xpath("//button[@class=\"button-2 product-box-add-to-cart-button\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_4']\n")).clear();
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_4']\n")).sendKeys("1");
        Thread.sleep(3000);
    }

    @Test
    public void changeCurrencies() throws InterruptedException {
        //login
        login.LoginSteps(driver);

        //changeCurrencies
        Select currencies = new Select(driver.findElement(By.xpath("//select[@id=\"customerCurrency\"]")));
        currencies.selectByVisibleText("US Dollar");
        currencies.selectByIndex(1);
    }


    @Test
    public void selectDifferentCategories()
    {
        //login
        login.LoginSteps(driver);

        //selectDifferentCategories
        driver.findElement(By.xpath("//a[text()='Books ']\n")).click();
        driver.findElement(By.xpath("//li[@class=\"inactive\"]//a[@href=\"/apparel\"]")).click();
        driver.findElement(By.xpath("//li[@class=\"inactive\"]//a[@href=\"/shoes\"]")).click();
    }

    @Test
    public void filterWithColor()
    {
        //login
        login.LoginSteps(driver);

        //filter With Color
        driver.findElement(By.xpath("//a[text()='Books ']\n")).click();
        driver.findElement(By.xpath("//li[@class=\"inactive\"]//a[@href=\"/apparel\"]")).click();
        driver.findElement(By.xpath("//h2[@class=\"title\"]//a[@href=\"/shoes\"]")).click();
        driver.findElement(By.id("attribute-option-15")).click();
    }

    @Test
    public void selectDifferentTags()
    {
        // login
        login.LoginSteps(driver);

        //selectDifferentTags
        driver.findElement(By.xpath("//a[text()='Books ']\n")).click();
        driver.findElement(By.xpath("//a[@href=\"/apparel-2\"]\n")).click();
        driver.findElement(By.xpath("//a[@href=\"/cool\"]\n")).click();
    }

    @Test
    public void ShoppingCart() throws InterruptedException {
        // login
        login.LoginSteps(driver);

        //addDifferentProductsToShoppingCart
        driver.findElement(By.xpath("//a[text()='Digital downloads ']\n")).click();
        driver.findElement(By.xpath("//h2[@class=\"product-title\"]//a[@href=\"/if-you-wait-donation\"]")).click();
        driver.findElement(By.id("add-to-cart-button-35")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@href=\"/books\"]")).click();
        driver.findElement(By.xpath("//h2[@class=\"product-title\"]//a[@href=\"/fahrenheit-451-by-ray-bradbury\"]")).click();
        driver.findElement(By.id("add-to-cart-button-37")).click();

        //Update Quantity
        driver.findElement(By.xpath("//span[@class=\"cart-label\"]")).click();
        driver.findElement(By.xpath("//td[@class=\"quantity\"]//input[@class=\"qty-input\"]")).clear();
        driver.findElement(By.xpath("//td[@class=\"quantity\"]//input[@class=\"qty-input\"]")).sendKeys("2");
        driver.findElement(By.xpath("//td[@class=\"quantity\"]//input[@class=\"qty-input\"]")).sendKeys(Keys.ENTER);

        //Remove Product
        driver.findElement(By.xpath("//td[@class=\"remove-from-cart\"]//button[@class=\"remove-btn\"]")).click();
    }

    @Test
    public void addDifferentProductsToWishlist() throws InterruptedException {
        // login
        login.LoginSteps(driver);

        //addDifferentProductsToWishlist
        driver.findElement(By.xpath("//a[text()='Apparel ']\n")).click();
        driver.findElement(By.xpath("//li[@class=\"inactive\"]//a[@href=\"/accessories\"]")).click();
        driver.findElement(By.xpath("//h2[@class=\"product-title\"]//a[@href=\"/ray-ban-aviator-sunglasses\"]")).click();
        driver.findElement(By.id("add-to-wishlist-button-33")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[text()='Digital downloads ']\n")).click();
        driver.findElement(By.xpath("//h2[@class=\"product-title\"]//a[@href=\"/if-you-wait-donation\"]")).click();
        driver.findElement(By.id("add-to-wishlist-button-35")).click();
    }

    @Test
    public void addDifferentProductsToCompareList() throws InterruptedException {
        //login
        login.LoginSteps(driver);

        //addDifferentProductsToCompareList
        driver.findElement(By.xpath("//a[text()='Apparel ']\n")).click();
        driver.findElement(By.xpath("//li[@class=\"inactive\"]//a[@href=\"/accessories\"]")).click();
        driver.findElement(By.xpath("//h2[@class=\"product-title\"]//a[@href=\"/ray-ban-aviator-sunglasses\"]")).click();
        driver.findElement(By.xpath("//button[@class=\"button-2 add-to-compare-list-button\"]")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[text()='Digital downloads ']\n")).click();
        driver.findElement(By.xpath("//h2[@class=\"product-title\"]//a[@href=\"/if-you-wait-donation\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"compare-products\"]//button[@class=\"button-2 add-to-compare-list-button\"]")).click();
    }

    @Test
    public void CreateSuccessfulOrder() throws InterruptedException {
        //login
        login.LoginSteps(driver);

        //CreateSuccessfulOrder
        driver.findElement(By.xpath("//a[text()='Digital downloads ']\n")).click();
        driver.findElement(By.xpath("//h2[@class=\"product-title\"]//a[@href=\"/if-you-wait-donation\"]")).click();
        driver.findElement(By.id("add-to-cart-button-35")).click();
        driver.findElement(By.xpath("//span[@class=\"cart-label\"]")).click();
        driver.findElement(By.id("termsofservice")).click();
        driver.findElement(By.id("checkout")).click();

        Select country = new Select(driver.findElement(By.id("BillingNewAddress_CountryId")));
        country.selectByVisibleText("Select country");
        country.selectByIndex(123);
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Luxor");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("TVstreet");
        driver.findElement(By.id("BillingNewAddress_Address2")).sendKeys("TVstreet");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("1234");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("01201201201");
        driver.findElement(By.id("BillingNewAddress_FaxNumber")).sendKeys("0789");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@class=\"button-1 new-address-next-step-button\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@class=\"button-1 payment-method-next-step-button\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@class=\"button-1 payment-info-next-step-button\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@class=\"button-1 confirm-order-next-step-button\"]")).click();

        // هنا في ايرور

//        String expectedResult = "Your order has been successfully processed!";
//        String actualResult = driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!'\n]")).getText();
//        Assert.assertEquals(actualResult,expectedResult);
    }

    @AfterTest
    public void CloseDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}

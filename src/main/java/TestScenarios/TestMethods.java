package TestScenarios;

import Objects.Home;
import Objects.Register;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestMethods {
    WebDriver driver = new ChromeDriver();
    Home home = new Home(driver);
    Register register = new Register(driver);

    @BeforeTest
    public void setup() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.get("https://phptravels.org/");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void viewProdOperation(){
        home.viewProd();
    }
    @Test(priority = 2)
    public void addToCartOperation(){
        home.addToCart();
    }
    @Test(priority = 4)
    public void removeItemOperation(){
        home.removeItems();
    }
    @Test(priority = 5)
    public void orderSummaryOperation(){
      home.orderSummary();
    }
    @Test(priority = 6)
    public void promocodeOperation(){
        home.promoCode();
    }
    @Test(priority = 7)
    public void checkoutOperation(){
        home.checkout();
    }
    @Test(priority = 8)
    public void regOperation(){
        register.fillRegFormPersonalInfo();
    }
    @Test(priority = 9)
    public void fillBillOperation(){
        register.fillRegFormBilling();
    }
    @Test(priority = 10)
    public void fillPassOperation(){
        register.fillRegFormPassword();
    }
    @Test(priority = 11)
    public void captchaOperation(){
        register.captcha();
    }
    @Test(priority = 12)
    public void servicesOperation(){
        home.services();
    }
    @Test(priority = 13)
    public void apiOperation(){
        home.api();
    }
    @Test(priority = 14)
    public void searchBarOpeartion(){
        home.search_bar();
    }


    @AfterTest
    public void teardown(){
        driver.quit();
    }

}

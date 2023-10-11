package Objects;

import org.openqa.selenium.*;
import utilities.Utility;
import java.util.List;

public class Home {
    WebDriver driver;
    public Utility utility;

    public Home(WebDriver driver) {
        this.driver = driver;
        this.utility = new Utility(driver);
    }

    By ClickStore = By.id("Primary_Navbar-Store");
    By ClickInstallation = By.id("Primary_Navbar-Store-Installation");
    By ClickProd1 = By.id("product1-order-button");
    By ClickProd2 = By.id("product3-order-button");
    By Form = By.xpath("//form[@action='/cart.php?a=view']");
    By Cross = By.xpath("//div[@class='col-sm-1 hidden-xs d-none d-sm-block']//button[@class='btn btn-link btn-xs btn-remove-from-cart']//i[@class='fas fa-times']");
    By Popup = By.xpath("//div[@class='modal-content']");
    By PopupYes = By.xpath("//div[@class='modal-content']//div[@class='modal-footer justify-content-center']//button[@class='btn btn-primary']");
    By Items = By.xpath("//div[@class='item']//div[@class='row']//div[@class='col-sm-7']//span[@class='item-title']");
    By Price = By.xpath("//div[@class='item']//div[@class='row']//div[@class='col-sm-4 item-price']");
    By EnterPromo = By.id("inputPromotionCode");
    By ValidatePromo = By.xpath("//form[@action='cart.php?a=view']//button[@value='Validate Code']");
    By ValidateWarning = By.xpath("//div[@class='secondary-cart-body']//div[@class='alert alert-warning text-center']");
    By Checkout = By.id("checkout");

    By clickServices = By.id("Secondary_Sidebar-Categories-Services");
    By getServicesErr = By.xpath("//div[@class='alert alert-danger']");
    By clickApi = By.id("Secondary_Sidebar-Categories-API");
    By getApiErr = By.xpath("//div[@class='alert alert-danger']");
    By search = By.xpath("//input[@placeholder='Search our knowledgebase...']");
    By searchErr = By.xpath("//div[@class='list-group list-group-flush']");

    public void viewProd() {
        driver.findElement(ClickStore).click();
        driver.findElement(ClickInstallation).click();
    }

    public void addToCart() {
        driver.findElement(ClickProd1).click();
        driver.navigate().back();
        driver.findElement(ClickProd2).click();
        driver.navigate().back();
        driver.findElement(ClickProd2).click();
    }

    public void removeItems() {
        WebElement form = driver.findElement(Form);
        List<WebElement> removeItem = form.findElements(Cross);
        if (!removeItem.isEmpty()) {
            removeItem.get(0).click();
        } else {
            utility.logerror("No items to remove");
        }

        WebElement popup = driver.findElement(Popup);
        utility.waitforvisibility(popup);

        boolean disp = popup.isDisplayed();
        if (disp) {
            try {
                driver.findElement(PopupYes).click();
                utility.loginfo("1 Item removed from cart");
            } catch (Exception e) {
                utility.logerror("Item is not removed");
            }
        } else {
            utility.loginfo("Popup is not displayed");
        }
    }

    public void orderSummary() {
        WebElement form = driver.findElement(Form);
        List<WebElement> formItems = form.findElements(Items);
        List<WebElement> formPrice = form.findElements(Price);

        if (formItems.size() == formPrice.size()) {
            for (int i = 0; i < formItems.size(); i++) {
                String itemText = formItems.get(i).getText();
                String itemPrice = formPrice.get(i).getText();
                System.out.println("\nOrdered Item: " + itemText + ", Price: " + itemPrice);
            }
        } else {
            utility.logerror("Number of items and prices do not match.");
        }

    }

    public void promoCode() {
        driver.findElement(EnterPromo).sendKeys("promo");
        WebElement validateButton = driver.findElement(ValidatePromo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", validateButton);
        validateButton.click();
        String promoCodeErr = driver.findElement(ValidateWarning).getText();
        utility.logerror(promoCodeErr);
    }

    public void checkout() {
        driver.findElement(Checkout).click();
    }

    public void services() {
        driver.findElement(clickServices).click();
        String ServiceErr = driver.findElement(getServicesErr).getText();
        utility.logerror(ServiceErr);
    }

    public void api() {
        driver.findElement(clickApi).click();
        String ApiErr = driver.findElement(getApiErr).getText();
        utility.logerror(ApiErr);
    }

    public void search_bar() {
        driver.findElement(search).sendKeys("Installation");
        driver.findElement(search).sendKeys(Keys.RETURN);
        String searchText = driver.findElement(searchErr).getText();
        utility.loginfo(searchText);
    }


}

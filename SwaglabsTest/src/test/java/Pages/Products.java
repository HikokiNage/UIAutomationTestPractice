package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class Products extends HomePage{



    @FindBy(className = "title")
    WebElement productsTitle;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuBtn;

    @FindBy(id = "logout_sidebar_link")
    WebElement logout;

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    @FindBy(className = "product_sort_container")
    WebElement productSortContainer;



    public Products(WebDriver driver) {
        super(driver);
    }

    public void isLoaded() {
        wait.until(ExpectedConditions.visibilityOf(productsTitle));
    }

    public void doLogout() {
        wait.until(ExpectedConditions.visibilityOf(menuBtn));
        menuBtn.click();
        wait.until(ExpectedConditions.visibilityOf(logout));
        logout.click();
    }

    public void addToCartAllProducts() {

        ArrayList<WebElement> buttons = new ArrayList<WebElement>(driver.findElements(By.tagName("button")));

        for (WebElement addToCart : buttons) {
            if (addToCart.getText().equals("Add to cart")) {
                addToCart.click();
            }
        }
    }

    public WebElement getProductsTitle() {
        return productsTitle;
    }


}
package Tests;

import static org.assertj.core.api.Assertions.*;

import Pages.HomePage;
import Pages.Products;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class LoginTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    public void validLogin(String username, String password) {

        HomePage homePage = new HomePage(driver);
        Products products = new Products(driver);

        homePage.clearInputField();

        homePage.login(username, password);

        products.isLoaded();

        assertThat(products.getProductsTitle().isDisplayed()).isTrue();

        products.doLogout();
    }

    @Test
    public void lockedOutUser() {
        HomePage homePage = new HomePage(driver);

        homePage.login("locked_out_user", "secret_sauce");

        assertThat(homePage.getErrorMessage()).isEqualTo("Epic sadface: Sorry, this user has been locked out.");

        homePage.getErrorMessageCloseButton().click();
    }

    @Test
    public void invalidLogin() {
        HomePage homePage = new HomePage(driver);

        homePage.login("invalidUsername", "invalidPassword");

        assertThat(homePage.getErrorMessage()).isEqualTo("Epic sadface: Username and password do not match any user in this service");

        homePage.getErrorMessageCloseButton().click();
    }
}

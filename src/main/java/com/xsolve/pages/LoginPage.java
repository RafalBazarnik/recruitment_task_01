package com.xsolve.pages;

import com.xsolve.common.Browser;
import com.xsolve.common.PropertiesLoader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPage extends AbstractPage {

    public LoginPage() {
        PageFactory.initElements(Browser.driver, this);
    }

    // elements
    @FindBy(how = How.ID, using = "username")
    private WebElement usernameField;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordField;

    @FindBy(how = How.NAME, using = "login")
    private WebElement loginButton;

    @FindBy(how = How.CLASS_NAME, using = "quick-login")
    private WebElement quickLoginField;

    @FindBy(how = How.CSS, using = "#username_logged_in .username")
    private WebElement loggedUser;

    public LoginPage inputPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage inputUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage checkVisibilityOfQuickLogin() {
        assertTrue("Quick Login Field is not visible on current page", quickLoginField.isDisplayed());
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        Browser.wait.until(ExpectedConditions.elementToBeClickable(loggedUser));
        return this;
    }

    public LoginPage checkLoggedUser(String username) {
        assertEquals("User not logged in or logged user is wrong", loggedUser.getText(), username);
        return this;
    }

    public LoginPage navigateToLoginPage() {
        Browser.getPage(PropertiesLoader.get("mainPage"));
        Browser.wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        return this;
    }
}

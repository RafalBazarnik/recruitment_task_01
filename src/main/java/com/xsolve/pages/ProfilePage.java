package com.xsolve.pages;

import com.xsolve.common.Browser;
import com.xsolve.common.PropertiesLoader;
import com.xsolve.enums.CommonEnums;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static junit.framework.TestCase.assertTrue;


public class ProfilePage extends AbstractPage {
    public ProfilePage() {
        PageFactory.initElements(Browser.driver, this);
    }

    // elements
    @FindBy(how = How.CSS, using = ".header-profile a")
    WebElement headerProfileLink;

    @FindBy(how = How.CSS, using = "#message-box #signature")
    WebElement signatureInputField;

    @FindBy(how = How.NAME, using = "submit")
    WebElement submitButton;

    @FindBy(how = How.CSS, using = "#message .inner p")
    WebElement actionMessage;


    public ProfilePage navigateToSignatureSetting() {
        Browser.wait.until(ExpectedConditions.visibilityOf(headerProfileLink));
        String profileMainUrl = headerProfileLink.getAttribute(CommonEnums.Attribute.HREF);
        String signatureUrlPart = PropertiesLoader.get("signatureUrlPart");
        String fullUrl = profileMainUrl.concat(signatureUrlPart);
        Browser.getPage(fullUrl);
        return this;
    }

    public ProfilePage inputNewSignature(String signature) {
        Browser.wait.until(ExpectedConditions.visibilityOf(signatureInputField));
        signatureInputField.clear();
        signatureInputField.sendKeys(signature);
        return this;
    }

    public ProfilePage submitNewSignature() {
        submitButton.click();
        Browser.wait.until(ExpectedConditions.visibilityOf(actionMessage));
        String infoMessageText = actionMessage.getText();
        assertTrue("Probably signature was not updated successfully", infoMessageText.contains(PropertiesLoader.get("profileUpdated")));
        return this;
    }

}

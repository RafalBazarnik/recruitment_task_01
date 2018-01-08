package com.xsolve.pages;

import com.xsolve.common.Browser;
import com.xsolve.common.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardPage extends AbstractPage {
    public BoardPage() {
        PageFactory.initElements(Browser.driver, this);
    }

    // elements
    @FindBy(how = How.CSS, using = ".bar-top .button")
    WebElement createTopicButton;

    @FindBy(how = How.CSS, using = ".panel .inner strong")
    WebElement boardIsEmpty;

    public BoardPage clickCreateTopicButton() {
        Browser.wait.until(ExpectedConditions.visibilityOf(createTopicButton));
        createTopicButton.click();
        return this;
    }

    public BoardPage checkIfTopicNotOnBoard(String subject) {
        List<WebElement> draftsList = Browser.driver.findElements(By.linkText(subject));
        assertTrue(String.format("Removed Draft with subject: %s was found on forum board", subject), draftsList.size() == 0);
        return this;
    }
}

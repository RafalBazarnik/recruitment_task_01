package com.xsolve.pages;

import com.xsolve.common.Browser;
import com.xsolve.common.PropertiesLoader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static org.junit.Assert.assertEquals;

public class BoardPage extends AbstractPage {
    public BoardPage() {
        PageFactory.initElements(Browser.driver, this);
    }

    // elements
    @FindBy(how = How.CSS, using = ".bar-top .button .fa-pencil")
    WebElement createTopicButton;

    @FindBy(how = How.CSS, using = ".panel .inner strong")
    WebElement boardIsEmpty;

    public BoardPage clickCreateTopicButton() {
        createTopicButton.click();
        return this;
    }

    public BoardPage checkIfBoardIsEmpty() {
        Browser.wait.until(ExpectedConditions.visibilityOf(createTopicButton));
        assertEquals("Board is not empty or message has changed", boardIsEmpty.getText(), PropertiesLoader.get("boardIsEmpty"));
        return this;
    }
}

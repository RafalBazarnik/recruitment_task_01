package com.xsolve.pages;

import com.xsolve.common.Browser;
import com.xsolve.enums.CommonEnums;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PostPage {
    public PostPage() {
        PageFactory.initElements(Browser.driver, this);
    }

    // elements
    @FindBy(how = How.CSS, using = "a[title='Delete post']")
    WebElement deleteTopicButton;

    @FindBy(how = How.CSS, using = "a[title='Edit post']")
    WebElement editTopicButton;

    @FindBy(how = How.ID, using = "delete_permanent")
    WebElement deletePermanentlyCheckbox;

    @FindBy(how = How.CLASS_NAME, using = "button1")
    WebElement confirmDeleteButton;

    @FindBy(how = How.CSS, using = ".first a")
    WebElement topicSubject;

    @FindBy(how = How.CLASS_NAME, using = "content")
    WebElement topicMessage;

    @FindBy(how = How.CSS, using = ".content img")
    List<WebElement> topicMessageImages;


    public PostPage clickDeleteTopicButton() {
        deleteTopicButton.click();
        return this;
    }

    public PostPage clickEditTopicButton() {
        editTopicButton.click();
        return this;
    }

    public PostPage clickDeletePermanentlyCheckbox() {
        deletePermanentlyCheckbox.click();
        return this;
    }

    public PostPage clickConfirmDeleteButton() {
        confirmDeleteButton.click();
        return this;
    }

    public PostPage assertTopicSubject(String subject) {
        Browser.wait.until(ExpectedConditions.visibilityOf(topicSubject));
        assertEquals("topic subject is different than it should", subject, topicSubject.getText());
        return this;
    }

    public PostPage assertTopicMessage(String message, String type) {
        switch (type) {
            case "simple":
                assertEquals("topic message is different than it should", message, topicMessage.getText());
                break;
            case "smiley":
                assertEquals("smiley icon is different than it should", message,
                        topicMessageImages.get(0).getAttribute(CommonEnums.Attributes.ALT.getValue()));
                break;
        }

        return this;
    }
}

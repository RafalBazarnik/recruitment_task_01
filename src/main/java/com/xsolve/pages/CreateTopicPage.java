package com.xsolve.pages;


import com.xsolve.common.Browser;
import com.xsolve.common.PropertiesLoader;
import com.xsolve.enums.CommonEnums;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreateTopicPage extends TextFormattingPage {
    public CreateTopicPage() {
        PageFactory.initElements(Browser.driver, this);
    }

    // elements
    @FindBy(how = How.ID, using = "subject")
    WebElement subjectField;

    @FindBy(how = How.ID, using = "message")
    WebElement messageField;

    @FindBy(how = How.CSS, using = "#preview h3")
    WebElement previewSubject;

    @FindBy(how = How.CSS, using = "#preview .content")
    WebElement previewMessage;

    @FindBy(how = How.CSS, using = "#preview .content img")
    List<WebElement> previewMessageImages;

    @FindBy(how = How.NAME, using = "save")
    WebElement saveDraftButton;

    @FindBy(how = How.NAME, using = "preview")
    WebElement previewButton;

    @FindBy(how = How.NAME, using = "post")
    WebElement postTopicButton;

    @FindBy(how = How.CSS, using = "#smiley-box a img")
    List<WebElement> smileysList;

    @FindBy(how = How.ID, using = "disable_bbcode")
    WebElement disableBBCodeCheckBox;

    @FindBy(how = How.ID, using = "disable_smilies")
    WebElement disableSmiliesCheckBox;

    @FindBy(how = How.ID, using = "disable_magic_url")
    WebElement disableMagicUrlCheckBox;

    @FindBy(how = How.ID, using = "attach_sig")
    WebElement attachSignatureCheckBox;

    @FindBy(how = How.ID, using = "attach-panel-tab")
    WebElement attachmentsPanelTab;

    @FindBy(how = How.ID, using = "options-panel-tab")
    WebElement optionsPanelTab;

    @FindBy(how = How.CLASS_NAME, using = "error")
    WebElement validationError;


    public CreateTopicPage inputSubjectField(String subject, Boolean clear) {
        if (clear) {
            subjectField.clear();
        }
        subjectField.sendKeys(subject);
        return this;
    }

    public CreateTopicPage inputMessageField(String message, Boolean clear) {
        if (clear) {
            messageField.clear();
        }
        messageField.sendKeys(message);
        return this;
    }

    public CreateTopicPage clickSubmitButton() {
        postTopicButton.click();
        return this;
    }

    public CreateTopicPage clickPreviewButton() {
        previewButton.click();
        return this;
    }

    public CreateTopicPage checkTopicSubjectEmptyError(Boolean shouldBeVisible) {
        if (shouldBeVisible) {
            assertTrue("Subject error is wrong or is not visible", validationError.getText().contains(PropertiesLoader.get("emptySubject")));
        } else {
            assertFalse("Subject error is visible", validationError.getText().contains(PropertiesLoader.get("emptySubject")));
        }
        return this;
    }

    public CreateTopicPage checkTopicMessageEmptyError(Boolean shouldBeVisible) {
        if (shouldBeVisible) {
            assertTrue("Message error is wrong or is not visible", validationError.getText().contains(PropertiesLoader.get("emptyMessage")));
        } else {
            assertFalse("Message error is visible", validationError.getText().contains(PropertiesLoader.get("emptyMessage")));
        }
        return this;
    }

    public CreateTopicPage clickSmileyByAltAttribute(String smiley) {
        String selector = String.format("#smiley-box a img[alt='%s']", smiley);
        Browser.driver.findElement(By.cssSelector(selector)).click();
        return this;
    }

    public CreateTopicPage checkIfSmileyIsInputMessageField(String smiley) {
        assertEquals("Smiley not in message input field", smiley, messageField.getAttribute("value").replace(" ", ""));
        return this;
    }

    public CreateTopicPage checkIfSmileyIsInPreviewMessageField(String smiley, Boolean disabledSmileys) {
        Browser.wait.until(ExpectedConditions.visibilityOf(previewMessage));
        if (disabledSmileys) {
            assertEquals("Smiley text not in message preview", smiley, previewMessage.getText());

        } else {
            assertEquals("Smiley image not in message preview", smiley, previewMessageImages.get(0).getAttribute(CommonEnums.Attributes.ALT.getValue()));
        }
        return this;
    }

    public CreateTopicPage toggleSmilies(Boolean disable) {
        if ((disable && !disableSmiliesCheckBox.isSelected()) || (!disable && disableSmiliesCheckBox.isSelected())) {
            disableSmiliesCheckBox.click();
        }
        return this;
    }
}


package com.xsolve.pages;

import com.xsolve.common.Browser;
import com.xsolve.common.Helpers;
import com.xsolve.common.PropertiesLoader;
import com.xsolve.enums.CommonEnums;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Path;
import java.nio.file.Paths;
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

    @FindBy(how = How.CSS, using = "#preview .content span")
    WebElement previewMessageFormattedText;

    @FindBy(how = How.CSS, using = "#preview .content blockquote div")
    WebElement previewMessageQuoteFormatting;

    @FindBy(how = How.CSS, using = "#preview .content .codebox code")
    WebElement previewMessageCodeFormatting;

    @FindBy(how = How.CSS, using = "#preview .content ul li")
    WebElement previewMessageUnorderedFormatting;

    @FindBy(how = How.CSS, using = "#preview .content ol li")
    WebElement previewMessagOrderedFormatting;

    @FindBy(how = How.CSS, using = "#preview .content img")
    WebElement previewMessageImage;

    @FindBy(how = How.CSS, using = "#preview .content a")
    WebElement previewMessageUrl;

    @FindBy(how = How.CSS, using = "#preview .content img")
    List<WebElement> previewMessageImages;

    @FindBy(how = How.NAME, using = "save")
    WebElement saveDraftButton;

    @FindBy(how = How.NAME, using = "load")
    WebElement loadDraftButton;

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

    @FindBy(how = How.NAME, using = "confirm")
    WebElement confirmButton;

    @FindBy(how = How.CSS, using = "#message p")
    WebElement resultInfo;

    @FindBy(how = How.ID, using = "add_files")
    WebElement inputButton;

    @FindBy(how = How.CSS, using = "input[type='file']")
    WebElement fileInput;

    @FindBy(how = How.CSS, using = ".attach-status .file-uploaded")
    WebElement fileUploadedStatus;

    @FindBy(how = How.CSS, using = "#preview .attachbox .file .attach-image .postimage")
    WebElement previewMessageAttachedImage;

    @FindBy(how = How.CSS, using = "#phpbb_alert p")
    WebElement forbiddenFileTypeAlertText;

    @FindBy(how = How.CSS, using = "#phpbb_alert .alert_close")
    WebElement forbiddenFileTypeAlertClose;

    @FindBy(how = How.CSS, using = "#preview .signature")
    WebElement signatureInPreview;



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
        Helpers.uglyWait(1000);
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
            assertEquals("Smiley image not in message preview", smiley, previewMessageImages.get(0).getAttribute(CommonEnums.Attribute.ALT));
        }
        return this;
    }

    public CreateTopicPage toggleSmilies(Boolean disable) {
        if ((disable && !disableSmiliesCheckBox.isSelected()) || (!disable && disableSmiliesCheckBox.isSelected())) {
            disableSmiliesCheckBox.click();
        }
        return this;
    }

    public CreateTopicPage toggleBBCode(Boolean disable) {
        if ((disable && !disableBBCodeCheckBox.isSelected()) || (!disable && disableBBCodeCheckBox.isSelected())) {
            disableBBCodeCheckBox.click();
        }
        return this;
    }

    public CreateTopicPage addSimpleTextFormatting(String formatting, String text) {
        switch(formatting) {
            case "bold":
                boldTextButton.click();
                break;
            case "italic":
                italicTextButton.click();
                break;
            case "underline":
                underlineTextButton.click();
                break;
            case "quote":
                quoteTextButton.click();
                break;
            case "code":
                codeTextButton.click();
                break;
            case "unorderedList":
                unorderedListTextButton.click();
                break;
            case "orderedList":
                orderedListTextButton.click();
                break;
            case "asterix":
                asterixTextButton.click();
                break;
            case "img":
                imgButton.click();
                break;
            case "url":
                urlButton.click();
                break;
        }
        messageField.sendKeys(text);
        return this;
    }

    public CreateTopicPage assertSimpleTextFormattingInMessageField(String formatting, String text) {
        String rawMessageText = messageField.getAttribute(CommonEnums.Attribute.VALUE).replace(" ", "");
        switch(formatting) {
            case "bold":
                assertEquals("Bolded text in message input field does not match", String.format("[b]%s[/b]", text), rawMessageText);
                break;
            case "italic":
                assertEquals("Italic text in message input field does not match", String.format("[i]%s[/i]", text), rawMessageText);
                break;
            case "underline":
                assertEquals("Underlined text in message input field does not match", String.format("[u]%s[/u]", text), rawMessageText);
                break;
            case "quote":
                assertEquals("Quoted text in message input field does not match", String.format("[quote]%s[/quote]", text), rawMessageText);
                break;
            case "code":
                assertEquals("Code text in message input field does not match", String.format("[code]%s[/code]", text), rawMessageText);
                break;
            case "unorderedList":
                assertEquals("Unordered List formatting text in message input field does not match", String.format("[list]%s[/list]", text), rawMessageText);
                break;
            case "orderedList":
                assertEquals("Ordered List formatting text in message input field does not match", String.format("[list=]%s[/list]", text), rawMessageText);
                break;
            case "asterix":
                assertEquals("Astrix formatting text in message input field does not match", String.format("[*]%s", text), rawMessageText);
                break;
            case "img":
                assertEquals("Image in message input field does not match", String.format("[img]%s[/img]", text), rawMessageText);
                break;
            case "url":
                assertEquals("Url in message input field does not match", String.format("[url]%s[/url]", text), rawMessageText);
                break;
        }
        return this;
    }

    public CreateTopicPage assertSimpleTextFormattingInPreviewMessageField(String formatting, String text, Boolean disabledBBCode) {
        String assertionMessage;
        String rawMessageText = messageField.getAttribute(CommonEnums.Attribute.VALUE).replace(" ", "");
        String disabledBBCodeStatus = disabledBBCode.toString();
        switch(formatting) {
            case "bold":
                assertionMessage = "Bolded text in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
                if (disabledBBCode) {
                    assertEquals(assertionMessage, String.format("[b]%s[/b]", text), rawMessageText);
                } else {
                    assertEquals(assertionMessage, "font-weight: bold;", previewMessageFormattedText.getAttribute(CommonEnums.Attribute.STYLE));
                }
                break;
            case "italic":
                assertionMessage = "Italic text in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
                if (disabledBBCode) {
                    assertEquals(assertionMessage, String.format("[i]%s[/i]", text), rawMessageText);
                } else {
                    assertEquals(assertionMessage, "font-style: italic;", previewMessageFormattedText.getAttribute(CommonEnums.Attribute.STYLE));
                }
                break;
            case "underline":
                assertionMessage = "Underlined text in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
                if (disabledBBCode) {
                    assertEquals(assertionMessage, String.format("[u]%s[/u]", text), rawMessageText);
                } else {
                    assertEquals(assertionMessage, "text-decoration: underline;", previewMessageFormattedText.getAttribute(CommonEnums.Attribute.STYLE));
                }
                break;
            case "quote":
                assertionMessage = "Quote in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
                if (disabledBBCode) {
                    assertEquals(assertionMessage, String.format("[quote]%s[/quote]", text), rawMessageText);
                } else {
                    assertEquals(assertionMessage, text, previewMessageQuoteFormatting.getText());
                }
                break;
            case "code":
                assertionMessage = "Code in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
                if (disabledBBCode) {
                    assertEquals(assertionMessage, String.format("[code]%s[/code]", text), rawMessageText);
                } else {
                    assertEquals(assertionMessage, text, previewMessageCodeFormatting.getText());
                }
                break;
            case "unorderedList":
                assertionMessage = "Unordered list in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
                if (disabledBBCode) {
                    assertEquals(assertionMessage, String.format("[list]%s[/list]", text), rawMessageText);
                } else {
                    assertEquals(assertionMessage, text, previewMessageUnorderedFormatting.getText());
                }
                break;
            case "orderedList":
                assertionMessage = "Ordered list in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
                if (disabledBBCode) {
                    assertEquals(assertionMessage, String.format("[list=]%s[/list]", text), rawMessageText);
                } else {
                    assertEquals(assertionMessage, text, previewMessageUnorderedFormatting.getText());
                }
                break;
            case "asterix":
                assertionMessage = "Asterix in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
                assertEquals(assertionMessage, String.format("[*]%s", text), rawMessageText);
                break;
            case "img":
                assertionMessage = "Image in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
                if (disabledBBCode) {
                    assertEquals(assertionMessage, String.format("[img]%s[/img]", text), rawMessageText);
                } else {
                    assertEquals(assertionMessage, "https://rekrutacjaqa.xsolve.software/".concat(text), previewMessageImage.getAttribute(CommonEnums.Attribute.SRC));
                }
                break;
            case "url":
                assertionMessage = "Url in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
                if (disabledBBCode) {
                    assertEquals(assertionMessage, String.format("[url]%s[/url]", text), rawMessageText);
                } else {
                    assertEquals(assertionMessage, "https://rekrutacjaqa.xsolve.software/".concat(text), previewMessageUrl.getAttribute(CommonEnums.Attribute.HREF));
                }
                break;
        }
        return this;
    }

    public CreateTopicPage setColorText(String color) {
        colorPaletteButton.click();
        WebElement colorElement = Browser.driver.findElement(By.cssSelector(String.format("#color_palette_placeholder td a[alt='%s']", color)));
        colorElement.click();
        return this;
    }

    public CreateTopicPage assertColorInMessageInputField(String color, String text) {
        assertEquals("Color formatting text in message input field does not match", String.format("[color=%s]%s[/color]", color, text), messageField.getAttribute(CommonEnums.Attribute.VALUE).replace(" ", ""));
        return this;
    }

    public CreateTopicPage assertColorInMessagePreviewField(String color, String text, Boolean disabledBBCode) {
        String disabledBBCodeStatus = disabledBBCode.toString();
        String assertionMessage = "Color formatting text in message preview field does not match - BBCode disabled status: ".concat(disabledBBCodeStatus);
        if (disabledBBCode) {
            assertEquals(assertionMessage, String.format("[color=%s]%s[/color]", color, text), previewMessage.getText());
        } else {
            assertEquals(assertionMessage, String.format("color: %s;", color), previewMessageFormattedText.getAttribute(CommonEnums.Attribute.STYLE));
        }
        return this;
    }

    public CreateTopicPage setFontSize(int size) {
        Select sizeSelect = new Select(fontSizeSelect);
        sizeSelect.selectByValue(String.valueOf(size));
        return this;
    }

    public CreateTopicPage assertFontSizeInMessageInputField(int size, String text) {
        if (size == 100) {
            assertEquals("Font size in message input field does not match - expected no size code for normal size", text, messageField.getAttribute(CommonEnums.Attribute.VALUE).replace(" ", ""));
        } else {
            assertEquals("Font size in message input field does not match", String.format("[size=%s]%s[/size]", String.valueOf(size), text), messageField.getAttribute(CommonEnums.Attribute.VALUE).replace(" ", ""));
        }
        return this;
    }

    public CreateTopicPage assertFontSizeInMessagePreviewField(int size, String text, Boolean disabledBBCode) {
        String disabledBBCodeStatus = disabledBBCode.toString();
        String fontSize = String.valueOf(size);
        String assertionMessage = String.format("Font size in message preview field does not match - BBCode disabled status: %s, font size: %s", disabledBBCodeStatus, fontSize);
        if (disabledBBCode) {
            if (size == 100) {
                assertEquals(assertionMessage, text, previewMessage.getText());
            } else {
                assertEquals(assertionMessage, String.format("[size=%s]%s[/size]", fontSize, text), previewMessage.getText());
            }
        } else {
            if (size == 100) {
                assertEquals(assertionMessage, text, previewMessage.getText());
            } else {
                assertEquals(assertionMessage, String.format("font-size: %s", fontSize).concat("%; line-height: normal;"), previewMessageFormattedText.getAttribute(CommonEnums.Attribute.STYLE));
            }
        }
        return this;
    }

    public CreateTopicPage clickSaveDraftButton() {
        saveDraftButton.click();
        return this;
    }

    public CreateTopicPage clickLoadDraftButton() {
        loadDraftButton.click();
        return this;
    }


    public CreateTopicPage clickConfirmSaveDraftButton() {
        confirmButton.click();
        Browser.wait.until(ExpectedConditions.visibilityOf(resultInfo));
        return this;
    }

    public  CreateTopicPage checkIfDraftIsOnSavedDraftsList(String subject) {
        List<WebElement> draftsList = Browser.driver.findElements(By.linkText(subject));
        assertTrue(String.format("No draft with subject: %s", subject), draftsList.size() == 1);
        return this;
    }

    public  CreateTopicPage clickDraftIsOnSavedDraftsList(String subject) {
        List<WebElement> draftsList = Browser.driver.findElements(By.linkText(subject));
        draftsList.get(0).click();
        return this;
    }

    public CreateTopicPage toggleMagicUrls(Boolean disable) {
        if ((disable && !disableMagicUrlCheckBox.isSelected()) || (!disable && disableMagicUrlCheckBox.isSelected())) {
            disableMagicUrlCheckBox.click();
        }
        return this;
    }

    public CreateTopicPage assertUrlInPreview(String url, Boolean disabledMagicUrls) {
        if (disabledMagicUrls) {
            assertEquals("Wrong url in preview - it should not be automatically parsed and active", url, previewMessage.getText());
        } else {
            assertEquals("Wrong url in preview - it should be automatically parsed and active", url, previewMessageUrl.getText());
        }
        return this;
    }

    public CreateTopicPage toggleOptionsAndAttachementsPanels(Boolean activeOptions) {
        if (!activeOptions && optionsPanelTab.getAttribute(CommonEnums.Attribute.CLASS).contains("activetab")) {
            attachmentsPanelTab.click();
        } else if (activeOptions && attachmentsPanelTab.getAttribute(CommonEnums.Attribute.CLASS).contains("activetab")) {
            optionsPanelTab.click();
        }
        return this;
    }

    public CreateTopicPage inputAttachement(String filename) {
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "src", "main", "resources", filename);
        fileInput.sendKeys(path.toString());
        return this;
    }

    public  CreateTopicPage waitForFileUploadSuccess() {
        Browser.wait.until(ExpectedConditions.visibilityOf(fileUploadedStatus));
        return this;
    }

    public CreateTopicPage assertAttachedImageInPreviewMessage(String filename) {
        assertEquals("Wrong filename in preview attachement", filename, previewMessageAttachedImage.getAttribute(CommonEnums.Attribute.ALT));
        return this;
    }

    public CreateTopicPage assertForbiddenFileExtensionPopupPresenceAndDismiss(String filename) {
        Browser.wait.until(ExpectedConditions.visibilityOf(forbiddenFileTypeAlertText));
        assertEquals("Wrong text in invalid extension alert", String.format(PropertiesLoader.get("invalidFileExtension"), filename), forbiddenFileTypeAlertText.getText());
        forbiddenFileTypeAlertClose.click();
        return this;
    }

    public CreateTopicPage assertSignatureInPreviewMessage(String signature) {
        assertEquals("wrong signature in preview", signature, signatureInPreview.getText());
        return this;
    }
}


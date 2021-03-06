package com.xsolve;

import com.xsolve.common.Browser;
import com.xsolve.common.Helpers;
import com.xsolve.common.PropertiesLoader;
import com.xsolve.enums.TextFormattingEnums;
import com.xsolve.pages.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class NewTopicTests {

    @BeforeClass
    public static void prepareTest() {
        Browser.maximizeWindow();
        new LoginPage()
                .navigateToLoginPage()
                .checkVisibilityOfQuickLogin()
                .inputUsername(PropertiesLoader.get("username"))
                .inputPassword(PropertiesLoader.get("password"))
                .clickLoginButton()
                .checkLoggedUser(PropertiesLoader.get("username"));
        new AllBoardsPage()
                .navigateToBoardForum34();
    }

    @Test
    public void sanityTest() {
        String subject, message;
        new BoardPage()
                .clickCreateTopicButton();
        subject = Helpers.generateUniqueText();
        message = Helpers.generateText();
        new CreateTopicPage()
                .inputSubjectField(subject, false)
                .inputMessageField(message, false)
                .clickSubmitButton();
        new PostPage()
                .assertTopicSubject(subject)
                .assertTopicMessage(message, TextFormattingEnums.MessageAssertTypes.SIMPLE.getValue())
                .clickDeleteTopicButton()
                .clickDeletePermanentlyCheckbox()
                .clickConfirmDeleteButton();
        new BoardPage().checkIfTopicNotOnBoard(subject);
    }

    @Test
    public void validationTest() {
        new BoardPage()
                .clickCreateTopicButton();
        new CreateTopicPage()
                .inputSubjectField("", false)
                .inputMessageField("", false)
                .clickSubmitButton()
                .checkTopicSubjectEmptyError(true)
                .checkTopicMessageEmptyError(true)
                .inputSubjectField(Helpers.generateUniqueText(), false)
                .inputMessageField("", false)
                .clickSubmitButton()
                .checkTopicSubjectEmptyError(false)
                .checkTopicMessageEmptyError(true)
                .inputSubjectField("", true)
                .inputMessageField( Helpers.generateText(), false)
                .clickSubmitButton()
                .checkTopicSubjectEmptyError(true)
                .checkTopicMessageEmptyError(false)
                .clickBreadCrumbByIndex(1); // return to board view
    }

    @Test
    public void smileysTest() {
        String randomSmiley = Helpers.getRandomSmiley();
        String subject = Helpers.generateUniqueText();
        new BoardPage()
                .clickCreateTopicButton();
        new CreateTopicPage()
                .inputSubjectField(subject, false)
                .clickSmileyByAltAttribute(randomSmiley)
                .checkIfSmileyIsInputMessageField(randomSmiley)
                .clickPreviewButton()
                .checkIfSmileyIsInPreviewMessageField(randomSmiley, false)
                .toggleSmilies(true)
                .clickPreviewButton()
                .checkIfSmileyIsInPreviewMessageField(randomSmiley, true)
                .clickSubmitButton();
        new PostPage()
                .assertTopicSubject(subject)
                .assertTopicMessage(randomSmiley, TextFormattingEnums.MessageAssertTypes.SIMPLE.getValue())
                .clickEditTopicButton();
        new CreateTopicPage()
                .toggleSmilies(false)
                .clickSubmitButton();
        new PostPage()
                .assertTopicMessage(randomSmiley, TextFormattingEnums.MessageAssertTypes.SMILEY_IMG.getValue())
                .clickDeleteTopicButton()
                .clickDeletePermanentlyCheckbox()
                .clickConfirmDeleteButton();
        new BoardPage().checkIfTopicNotOnBoard(subject);
    }

    @Test
    public void simpleTextFormattingTest() {
        String randomSimpleTextFormatting = Helpers.getRandomStringFromArray(TextFormattingEnums.SimpleTextFormatting.FULL_LIST);
        String subject = Helpers.generateUniqueText();
        String message;
        if (randomSimpleTextFormatting == TextFormattingEnums.SimpleTextFormatting.URL) {
            message = "abc";
        } else {
            message = Helpers.generateText();
        }

        new BoardPage()
                .clickCreateTopicButton();
        new CreateTopicPage()
                .inputSubjectField(subject, false)
                .addSimpleTextFormatting(randomSimpleTextFormatting, message)
                .assertSimpleTextFormattingInMessageField(randomSimpleTextFormatting, message)
                .clickPreviewButton()
                .assertSimpleTextFormattingInPreviewMessageField(randomSimpleTextFormatting, message, false)
                .toggleBBCode(true)
                .clickPreviewButton()
                .assertSimpleTextFormattingInPreviewMessageField(randomSimpleTextFormatting, message, true)
                .clickBreadCrumbByIndex(1); // return to board view
    }

    @Test
    public void colorFormattingTest() {
        String subject = Helpers.generateUniqueText();
        String message = Helpers.generateText();
        String color = "#80BF00";
        new BoardPage()
                .clickCreateTopicButton();
        new CreateTopicPage()
                .inputSubjectField(subject, false)
                .setColorText(color)
                .inputMessageField(message, false)
                .assertColorInMessageInputField(color, message)
                .clickPreviewButton()
                .assertSimpleTextFormattingInPreviewMessageField(color, message, false)
                .toggleBBCode(true)
                .clickPreviewButton()
                .assertColorInMessagePreviewField(color, message, true)
                .clickBreadCrumbByIndex(1); // return to board view
    }

    @Test
    public void fontSizeFormattingTest() {
        String subject = Helpers.generateUniqueText();
        String message = Helpers.generateText();
        int fontSize = TextFormattingEnums.FontSizes.SMALL.getValue();
        new BoardPage()
                .clickCreateTopicButton();
        new CreateTopicPage()
                .inputSubjectField(subject, false)
                .setFontSize(fontSize)
                .inputMessageField(message, false)
                .assertFontSizeInMessageInputField(fontSize, message)
                .clickPreviewButton()
                .assertFontSizeInMessagePreviewField(fontSize, message, false)
                .toggleBBCode(true)
                .clickPreviewButton()
                .assertFontSizeInMessagePreviewField(fontSize, message, true)
                .clickBreadCrumbByIndex(1); // return to board view
    }

    @Test
    public void autoParsingUrlsTest() {
        String subject = Helpers.generateUniqueText();
        String message = "http://www.onet.pl";

        new BoardPage()
                .clickCreateTopicButton();
        new CreateTopicPage()
                .inputSubjectField(subject, false)
                .inputMessageField(message, false)
                .clickPreviewButton()
                .assertUrlInPreview(message, false)
                .toggleMagicUrls(true)
                .clickPreviewButton()
                .assertUrlInPreview(message, true)
                .clickBreadCrumbByIndex(1); // return to board view
    }

    @Test
    public void saveDraftTest() {
        String subject = Helpers.generateUniqueText();
        String message = Helpers.generateText();

        new BoardPage()
                .clickCreateTopicButton();
        new CreateTopicPage()
                .inputSubjectField(subject, false)
                .inputMessageField(message, false)
                .clickSaveDraftButton()
                .clickConfirmSaveDraftButton();
        new BoardPage()
                .clickCreateTopicButton();
        new CreateTopicPage()
                .clickLoadDraftButton()
                .checkIfDraftIsOnSavedDraftsList(subject)
                .clickDraftIsOnSavedDraftsList(subject)
                .clickSubmitButton();
        new PostPage()
                .assertTopicSubject(subject)
                .assertTopicMessage(message, TextFormattingEnums.MessageAssertTypes.SIMPLE.getValue())
                .clickDeleteTopicButton()
                .clickDeletePermanentlyCheckbox()
                .clickConfirmDeleteButton();
        new BoardPage().checkIfTopicNotOnBoard(subject);
    }

    @Test
    public void attachingSignatureTest() {
        String signature = Helpers.generateUniqueText();
        String subject = Helpers.generateUniqueText();
        String message = Helpers.generateText();
        new ProfilePage()
                .navigateToSignatureSetting()
                .inputNewSignature(signature)
                .submitNewSignature()
                .clickBreadCrumbByIndex(0); // return to main forum view
        new AllBoardsPage()
                .navigateToBoardForum34();
        new BoardPage()
                .clickCreateTopicButton();
        new CreateTopicPage()
                .inputSubjectField(subject, false)
                .inputMessageField(message, false)
                .clickPreviewButton()
                .assertSignatureInPreviewMessage(signature)
                .clickBreadCrumbByIndex(1); // return to board view
    }

    @Test
    public void addingAttachmentsTest() {
        String subject = Helpers.generateUniqueText();
        String message = Helpers.generateText();
        String filename = "accepted_file_type.gif";
        String forbiddenExtensionFilename = "forbidden_file_type.bmp";

        new BoardPage()
                .clickCreateTopicButton();
        new CreateTopicPage()
                .inputSubjectField(subject, false)
                .inputMessageField(message, false)
                .toggleOptionsAndAttachementsPanels(false)
                .inputAttachement(forbiddenExtensionFilename)
                .assertForbiddenFileExtensionPopupPresenceAndDismiss(forbiddenExtensionFilename)
                .inputAttachement(filename)
                .waitForFileUploadSuccess()
                .clickPreviewButton()
                .assertAttachedImageInPreviewMessage(filename)
                .clickBreadCrumbByIndex(1); // return to board view
    }



    @AfterClass
    public static void cleanUp() {
        Browser.close();
    }
}

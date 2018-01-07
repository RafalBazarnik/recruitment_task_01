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
    public void sanityCheck() {
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
        new BoardPage().checkIfBoardIsEmpty();
    }

    @Test
    public void validationCheck() {
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
        new BoardPage().checkIfBoardIsEmpty();
    }

    @AfterClass
    public static void cleanUp() {
        Browser.close();
    }
}

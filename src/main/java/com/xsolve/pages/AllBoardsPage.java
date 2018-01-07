package com.xsolve.pages;

import com.xsolve.common.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AllBoardsPage extends AbstractPage {

    public AllBoardsPage() {
        PageFactory.initElements(Browser.driver, this);
    }

    // elements
    @FindBy(how = How.LINK_TEXT, using = "Forum35")
    WebElement boardForum34Link;

    public AllBoardsPage navigateToBoardForum34() {
        boardForum34Link.click();
        return this;
    }
}

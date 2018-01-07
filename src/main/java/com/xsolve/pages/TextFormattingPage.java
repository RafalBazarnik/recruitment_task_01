package com.xsolve.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public abstract class TextFormattingPage extends AbstractPage {

    // elements
    @FindBy(how = How.CSS, using = ".bbcode-b")
    WebElement boldTextButton;

    @FindBy(how = How.CSS, using = ".bbcode-i")
    WebElement italicTextButton;

    @FindBy(how = How.CSS, using = ".bbcode-u")
    WebElement underlineTextButton;

    @FindBy(how = How.CSS, using = ".bbcode-quote")
    WebElement quoteTextButton;

    @FindBy(how = How.CSS, using = ".bbcode-code")
    WebElement codeTextButton;

    @FindBy(how = How.CSS, using = ".bbcode-list")
    WebElement unorderedListTextButton;

    @FindBy(how = How.CSS, using = ".bbcode-list-")
    WebElement orderedListTextButton;

    @FindBy(how = How.CSS, using = ".bbcode-asterisk")
    WebElement asterixTextButton;

    @FindBy(how = How.CSS, using = ".bbcode-img")
    WebElement imgButton;

    @FindBy(how = How.CSS, using = ".bbcode-url")
    WebElement urlButton;

    @FindBy(how = How.ID, using = "bbpalette")
    WebElement colorPaletteButton;

    @FindBy(how = How.CSS, using = "#color_palette_placeholder tbody td a")
    List<WebElement> paletteColors;

    @FindBy(how = How.CLASS_NAME, using = "bbcode-size")
    WebElement fontSizeSelect;

}

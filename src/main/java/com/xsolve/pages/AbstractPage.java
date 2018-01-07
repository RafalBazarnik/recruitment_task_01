package com.xsolve.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public abstract class AbstractPage {

    @FindBy(how = How.CSS, using = ".breadcrumbs .crumb a")
    List<WebElement> breadcrumbs;

    public void clickBreadCrumbByIndex(int index) {
        breadcrumbs.get(index).click();
    }
}

package com.xsolve.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {

    public static WebDriver driver = new FirefoxDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, 10);

    public static void getPage(String url) {
        driver.get(url);
    }

    public static String title() {
        return driver.getTitle();
    }

    public static String currentUrl () {
        return driver.getCurrentUrl();
    }

    public static void close() {
        driver.close();
    }

    public static void maximizeWindow() {
        driver.manage().window().maximize();
    }
}
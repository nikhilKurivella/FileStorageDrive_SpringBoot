package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    private final WebDriver webDriver;
    private final JavascriptExecutor js;

    public Login(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.js = (JavascriptExecutor) this.webDriver;
        PageFactory.initElements(this.webDriver,this);
    }

    public void signIn(String userName, String password){
        js.executeScript("arguments[0].value='"+ userName +"';", inputUsername);
        js.executeScript("arguments[0].value='"+ password +"';", inputPassword);
        js.executeScript("arguments[0].click();", loginButton);
    }
}

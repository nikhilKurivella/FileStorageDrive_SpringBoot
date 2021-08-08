package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Signup {
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    private final WebDriver webDriver;
    private final JavascriptExecutor js;

    public Signup(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.js = (JavascriptExecutor) this.webDriver;
        PageFactory.initElements(this.webDriver,this);
    }

    public void createAccount(String userName, String firstName, String lastName, String password){
        js.executeScript("arguments[0].value='"+ firstName +"';", inputFirstName);
        js.executeScript("arguments[0].value='"+ lastName +"';", inputLastName);
        js.executeScript("arguments[0].value='"+ userName +"';", inputUsername);
        js.executeScript("arguments[0].value='"+ password +"';", inputPassword);
        js.executeScript("arguments[0].click();", submitButton);
    }
}

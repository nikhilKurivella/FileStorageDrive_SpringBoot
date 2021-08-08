package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "addNewNoteButton")
    private WebElement addNewNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleModal;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionModal;

    @FindBy(id = "noteTitleList")
    private WebElement noteTitleList;

    @FindBy(id = "noteDescriptionList")
    private WebElement noteDescriptionList;

    @FindBy(id = "submitNote")
    private WebElement submitNoteButton;

    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNoteButton;

    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="addNewCredentialsButton")
    private WebElement addNewCredentialsButton;

    @FindBy(id="credential-url")
    private WebElement credentialURLModal;

    @FindBy(id="credential-username")
    private WebElement credentialUsernameModal;

    @FindBy(id="credential-password")
    private WebElement credentialPasswordModal;

    @FindBy(id="submitCredentialsButton")
    private WebElement submitCredentialModal;

    @FindBy(id="editCredentialsButton")
    private WebElement editCredentialsButton;

    @FindBy(id="deleteCredentialsButton")
    private WebElement deleteCredentialsButton;

    @FindBy(id="nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id="credentialURLList")
    private WebElement credentialURLList;

    @FindBy(id="credentialUsernameList")
    private WebElement credentialUsernameList;

    private final WebDriver webDriver;
    private final JavascriptExecutor js;
    private WebDriverWait wait;

    public Home(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.js = (JavascriptExecutor) this.webDriver;
        this.wait = new WebDriverWait(this.webDriver, 20);
        PageFactory.initElements(this.webDriver,this);
    }

    public void onClickLogout(){
        this.wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        js.executeScript("arguments[0].click();", logoutButton);
//        this.wait.until(ExpectedConditions.urlMatches("/login?logout"));
    }

    public void onClickNoteTab(){
        this.wait.until(ExpectedConditions.elementToBeClickable(notesTab));
        js.executeScript("arguments[0].click();", notesTab);
    }

    public void onClickCredentialsTab(){
        this.wait.until(ExpectedConditions.elementToBeClickable(credentialsTab));
        js.executeScript("arguments[0].click();", credentialsTab);
    }

    public void addNote(String title, String description){
        this.wait.until(ExpectedConditions.elementToBeClickable(addNewNoteButton));
        js.executeScript("arguments[0].click();", addNewNoteButton);
        this.wait.until(ExpectedConditions.elementToBeClickable(submitNoteButton));
        js.executeScript("arguments[0].value='"+ title +"';", noteTitleModal);
        js.executeScript("arguments[0].value='"+ description +"';", noteDescriptionModal);
        js.executeScript("arguments[0].click();", submitNoteButton);
    }

    public void editNote(String title, String description) {
        this.wait.until(ExpectedConditions.elementToBeClickable(editNoteButton));
        js.executeScript("arguments[0].click();", editNoteButton);
        this.wait.until(ExpectedConditions.elementToBeClickable(submitNoteButton));
        js.executeScript("arguments[0].value='"+ title +"';", noteTitleModal);
        js.executeScript("arguments[0].value='"+ description +"';", noteDescriptionModal);
        js.executeScript("arguments[0].click();", submitNoteButton);
    }

    public String getNoteTitle(){
        return noteTitleList.getAttribute("innerHTML");
    }

    public String getNoteDescription(){
        return noteDescriptionList.getAttribute("innerHTML");
    }

    public void deleteNote(){
        this.wait.until(ExpectedConditions.elementToBeClickable(deleteNoteButton));
        js.executeScript("arguments[0].click();", deleteNoteButton);
    }

    public void addCredentials(String URL, String username, String password){
        this.wait.until(ExpectedConditions.elementToBeClickable(addNewCredentialsButton));
        js.executeScript("arguments[0].click();", addNewCredentialsButton);
        this.wait.until(ExpectedConditions.elementToBeClickable(submitCredentialModal));
        js.executeScript("arguments[0].value='"+ URL +"';", credentialURLModal);
        js.executeScript("arguments[0].value='"+ username +"';", credentialUsernameModal);
        js.executeScript("arguments[0].value='"+ password +"';", credentialPasswordModal);
        js.executeScript("arguments[0].click();", submitCredentialModal);
    }

    public void editCredentials(String URL, String username, String password){
        this.wait.until(ExpectedConditions.elementToBeClickable(editCredentialsButton));
        js.executeScript("arguments[0].click();", editCredentialsButton);
        this.wait.until(ExpectedConditions.elementToBeClickable(submitCredentialModal));
        js.executeScript("arguments[0].value='"+ URL +"';", credentialURLModal);
        js.executeScript("arguments[0].value='"+ username +"';", credentialUsernameModal);
        js.executeScript("arguments[0].value='"+ password +"';", credentialPasswordModal);
        js.executeScript("arguments[0].click();", submitCredentialModal);
    }

    public void deleteCredentials(){
        this.wait.until(ExpectedConditions.elementToBeClickable(deleteCredentialsButton));
        js.executeScript("arguments[0].click();", deleteCredentialsButton);
    }

    public String getCredentialsURL(){
        return credentialURLList.getAttribute("innerHTML");
    }

    public String getCredentialsUsername(){
        return credentialUsernameList.getAttribute("innerHTML");
    }
}

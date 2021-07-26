package com.blz.selenium.pages;

import com.blz.selenium.base.BaseClass1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends BaseClass1 {

    @FindBy(linkText = "Log in")
    WebElement login_btn;

//    @FindBy(xpath = "//span[contains(text(),'Use phone, email or username')]")
//    WebElement button;

    @FindBy(name = "session[username_or_email]")
    WebElement userName;

    @FindBy(name = "session[password]")
    WebElement passWord;

    @FindBy(xpath = "//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[2]/form/div/div[3]/div/div")
    WebElement login;

    public Login(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void login_to_twitter(String username,String password) throws InterruptedException {
        login_btn.click();
        Thread.sleep(3000);
//        button.click();
//        Thread.sleep(1000);
        userName.sendKeys(username);
        passWord.sendKeys(password);
        Thread.sleep(3000);
        login.click();
        Thread.sleep(3000);
    }
}

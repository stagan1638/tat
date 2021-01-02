package com.epam.framework.page;

import com.epam.framework.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    public final String BASE_URL = "https://24shop.by/";

    @FindBy(css = ".top-nav-menu--auth div:last-child a")
    private WebElement loginLink;

    @FindBy(id = "05")
    private WebElement mobileInput;

    @FindBy(css = "input[type=password]")
    private WebElement passwordInput;

    @FindBy(id = "button03")
    private WebElement enterButton;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LoginPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public LoginPage loginUser(User user) {
        loginLink.click();
        mobileInput.sendKeys(user.getMobile());
        passwordInput.sendKeys(user.getPassword());
        enterButton.click();
        return this;
    }

    public boolean isUserLoggedIn() {
        return getTitle().equals("24shop.by - Личные данные") && getCurrentUrl().contains("24shop.by/cabinet/");
    }
}
package com.epam.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class FeedbackPage extends AbstractPage {
    public final String BASE_URL = "https://24shop.by/online_shop/contacts/";

    @FindBy(css = "input[type=text]")
    private List<WebElement> inputs;

    @FindBy(css = "input[type=tel]")
    private WebElement inputMobile;

    @FindBy(css = "textarea")
    private WebElement textarea;

    @FindBy(id = "send_question")
    private WebElement sendButton;

    public FeedbackPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public FeedbackPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public boolean isRedirected() {
        return !getCurrentUrl().equals(BASE_URL);
    }

    public FeedbackPage inputData(String name, String mobile, String message) {
        inputs.get(0).sendKeys(name);
        inputMobile.sendKeys(mobile);
        textarea.sendKeys(message);
        sendButton.click();
        return this;
    }
}

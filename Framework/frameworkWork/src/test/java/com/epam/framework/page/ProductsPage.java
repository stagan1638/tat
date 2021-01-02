package com.epam.framework.page;

import com.epam.framework.model.Product;
import com.epam.framework.service.ProductHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsPage extends AbstractPage {
    public final String BASE_URL = "https://24shop.by/catalog/";
    public final String BASE_CATEGORY = "пылесосы";

    private By selectSelector = By.id("select-sort");
    private By loadLabelSelector = By.cssSelector(".vue-loader");
    private By formSelector = By.cssSelector(".form-item");

    @FindBy(css = ".td_overlay .name a")
    private List<WebElement> productsLinks;

    @FindBy(css = "div .price")
    private List<WebElement> prices;

    @FindBy(css = "option")
    private List<WebElement> options;

    @FindBy(css = ".form-item label")
    private List<WebElement> labels;

    @FindBy(css = ".form-item input")
    private List<WebElement> checkboxes;

    @FindBy(css = ".link_decor")
    private List<WebElement> links;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public ProductsPage openPage(){
        return openPage(BASE_CATEGORY);
    }

    public ProductsPage openPage(String sectionName) {
        driver.navigate().to(BASE_URL);
        for (WebElement link : links){
            if (link.getText().toLowerCase().equals(sectionName.toLowerCase())){
                link.click();
                return this;
            }
        }
        return null;
    }

    public ArrayList<Product> getProducts(){
        return ProductHandler.createProductList(productsLinks.stream().map(WebElement::getText).toArray(String[]::new),
                prices.stream().map(WebElement::getText).toArray(String[]::new));
    }

    public ArrayList<Product> getProductsSortByPriceInc() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(selectSelector))
                .click();
        options.get(1).click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.numberOfElementsToBe(loadLabelSelector, 0));
        return getProducts();
    }

    public ArrayList<Product> getProductsSortByPriceDec() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(selectSelector))
                .click();
        options.get(2).click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.numberOfElementsToBe(loadLabelSelector, 0));
        return getProducts();
    }

    public String[] getCategories(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(formSelector));
        return labels.stream().map(WebElement::getText).toArray(String[]::new);
    }

    public ArrayList<Product> sortByCategory(String category){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(formSelector));
        checkboxes.get(Arrays.asList(getCategories()).indexOf(category)).click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.numberOfElementsToBe(loadLabelSelector, 0));
        return getProducts();
    }
}

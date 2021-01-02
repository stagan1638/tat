package com.epam.framework.page;

import com.epam.framework.model.Product;
import com.epam.framework.service.ProductHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPage {
    public final String BASE_URL = "https://24shop.by/search";

    @FindBy(css = "input[type=search]")
    private WebElement searchField;

    @FindBy(css = ".pseudo-link")
    private List<WebElement> products;

    @FindBy(css = ".accordion__btn")
    private List<WebElement> sections;

    @FindBy(css = ".catalog-byn-prices .price")
    private List<WebElement> prices;

    @FindBy(css = "li .active li")
    private List<WebElement> subsections;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SearchPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public SearchPage search(String text) {
        searchField.sendKeys(text + "\n");
        return this;
    }

    public boolean isSomethingFound() {
        return !getTitle().contains("ничего не найдено");
    }

    public ArrayList<Product> getProducts() {
        return ProductHandler.createProductList(products.stream().map(x -> x.getAttribute("title")).toArray(String[]::new),
                prices.stream().map(WebElement::getText).toArray(String[]::new));
    }
}

package com.epam.framework.test;

import com.epam.framework.page.SearchPage;
import com.epam.framework.service.ProductHandler;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class TestSearch extends CommonConditions {
    @DataProvider
    public Object[][] differentRegister() {
        return new Object[][]{
                {"ПылЕсос", "пылесос"},
                {"ПЫЛЕСОС", "пылесос"},
                {" ноУтбуК !", "ноутбук"},
        };
    }

    @Test(dataProvider = "differentRegister")
    public void searchTestWithDifferentRegister(String toTest, String expected) {
        SearchPage page = new SearchPage(driver).openPage().search(toTest);
        Assert.assertTrue(page.isSomethingFound());
        Assert.assertTrue(ProductHandler.containsProductWithName(page.getProducts(), expected));
    }

    @DataProvider
    public Object[][] unusual() {
        return new Object[][]{
                {"laptop", "ноутбук"},
                {"лэптоп", "ноутбук"}
        };
    }
    @Test(dataProvider = "unusual")
    public void searchTestWithUnusualNames(String toTest, String expected) {
        SearchPage page = new SearchPage(driver).openPage().search("laptop");
        Assert.assertTrue(page.isSomethingFound());
        Assert.assertTrue(ProductHandler.containsProductWithName(page.getProducts(), "ноутбук"));
    }
}

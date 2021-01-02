package com.epam.framework.test;

import com.epam.framework.model.User;
import com.epam.framework.page.LoginPage;
import com.epam.framework.page.ProductsPage;
import com.epam.framework.page.SearchPage;
import com.epam.framework.service.ProductHandler;
import com.epam.framework.service.ReadEnvSpecificData;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.rmi.server.ExportException;
import java.util.ResourceBundle;


@Listeners({TestListener.class})
public class TestLogin extends CommonConditions{
    @DataProvider
    public Object[] users() {
        return new Object[]{
                new User("445444952", "pass"),
                new User("445555551", "pass2")
        };
    }
    @Test
    public void loginUserTestNative() {
        User user = new User(ReadEnvSpecificData.getTestData("test_data.user.mobile"), ReadEnvSpecificData.getTestData("test_data.user.password"));
        Assert.assertTrue(new LoginPage(driver).openPage().loginUser(user).isUserLoggedIn());
    }

    @Test(expectedExceptions = AssertionError.class, dataProvider = "users")
    public void loginUserTestFails(User user) {
        Assert.assertTrue(new LoginPage(driver).openPage().loginUser(user).isUserLoggedIn());
    }
}

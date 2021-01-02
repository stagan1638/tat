package com.epam.framework.test;

import com.epam.framework.model.FeedbackMessage;
import com.epam.framework.page.FeedbackPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class TestFeedback extends CommonConditions {
    @DataProvider
    public Object[] feedBackData() {
        return new Object[]{
                new FeedbackMessage("Name", "445555555", "Message"),
                new FeedbackMessage("", "", ""),
                new FeedbackMessage("Drop tablespace users;", "", "Drop tablespace users;")
        };
    }
    @Test(dataProvider = "feedBackData", expectedExceptions = AssertionError.class)
    public void sendFeedBackTestCapcha(FeedbackMessage message){
        Assert.assertTrue(new FeedbackPage(driver).openPage().inputData(message.getName(), message.getMobile(), message.getMessage()).isRedirected());
    }
}

package com.github.paczek224.qa.login.tests;

import com.github.paczek224.qa.common.BaseTest;
import com.github.paczek224.qa.common.configuration.ApplicationProperties;
import com.github.paczek224.qa.common.playwright.PlayWrightManager;
import com.github.paczek224.qa.login.manager.LoginPageManager;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginTest extends BaseTest {

    private LoginPageManager loginPageManager;

    @Autowired
    public LoginTest(PlayWrightManager playWrightManager, ApplicationProperties applicationProperties) {
        super(playWrightManager, applicationProperties);
    }

    @Override
    protected void initPageManagers(Page page) {
        loginPageManager = new LoginPageManager(getApplicationProperties(), page);
    }

    @AfterEach
    protected void assertAll() {
        loginPageManager.assertAll();
    }

    @Test
    public void loggedUserIsLandedToTheApplicationMainPage() {
        loginPageManager.actions().loginWithValidUser();
        loginPageManager.assertLogin().assertUserLoginDefaultState();
    }

    @Test
    public void tcExample2() {
        System.out.println(loginPageManager.onLoginPage().getPage().url());
    }
}

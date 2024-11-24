package com.github.paczek224.qa.login.manager;

import com.github.paczek224.qa.common.configuration.ApplicationProperties;
import com.github.paczek224.qa.login.page.LoginPage;
import com.github.paczek224.qa.login.page.LoginPageActions;
import com.github.paczek224.qa.login.page.LoginPageAssertions;
import com.microsoft.playwright.Page;

public class LoginPageManager {
    private final LoginPage loginPage;
    private final LoginPageAssertions loginPageAssertions;
    private final LoginPageActions loginPageActions;

    public LoginPageManager(ApplicationProperties applicationProperties, Page page) {
        this.loginPage = new LoginPage(page, applicationProperties);
        this.loginPageAssertions = new LoginPageAssertions(loginPage);
        this.loginPageActions = new LoginPageActions(loginPage, loginPageAssertions);
    }

    public LoginPageActions actions() {
        return loginPageActions;
    }

    public LoginPageAssertions assertLogin() {
        return loginPageAssertions;
    }

    public LoginPage onLoginPage() {
        return loginPage;
    }

    public void assertAll() {
        loginPageAssertions.assertAll();
    }
}

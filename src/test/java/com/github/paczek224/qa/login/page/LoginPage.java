package com.github.paczek224.qa.login.page;

import com.github.paczek224.qa.common.configuration.ApplicationProperties;
import com.github.paczek224.qa.common.page.AbstractTestPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPage extends AbstractTestPage {

    private final ApplicationProperties applicationProperties;
    private final String loginUrl;
    private final String login;
    private final String password;
    public Locator userNameInput;

    public LoginPage(Page page, ApplicationProperties applicationProperties) {
        super(page);
        this.applicationProperties = applicationProperties;
        this.loginUrl = applicationProperties.getLoginUrl();
        this.login = applicationProperties.getUserName();
        this.password = applicationProperties.getUserPassword();

        userNameInput = page.locator("#inputFirstName3");
    }

    public LoginPage navigateToLoginPage() {
        page.navigate(loginUrl);

        return this;
    }

    public LoginPage setUserName(String userName) {
        userNameInput.fill(userName);

        return this;
    }

    public LoginPage setPassword(String userPassword) {
/*
        passwordInput.fill(userPassword);
*/

        return this;
    }

    public LoginPage enterValidUserName() {
        setUserName(applicationProperties.userName);
        return this;
    }

    public LoginPage enterValidPassword() {
        setPassword(applicationProperties.userPassword);
        return this;
    }
}

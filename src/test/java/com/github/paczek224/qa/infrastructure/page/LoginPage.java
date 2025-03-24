package com.github.paczek224.qa.infrastructure.page;

import com.github.paczek224.qa.infrastructure.configuration.ApplicationProperties;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class LoginPage extends AbstractTestPage {

    private final ApplicationProperties applicationProperties;
    private final Locator signInButton;
    private final Locator userNameInput;
    private final Locator userPasswordInput;
    private final Locator loggedUserIcon;

    public LoginPage(Page page, ApplicationProperties applicationProperties) {
        super(page);
        this.applicationProperties = applicationProperties;

        userNameInput = page.locator("#login_field");
        userPasswordInput = page.locator("#password");
        signInButton = page.locator("//input[@type='submit']");
        loggedUserIcon = page.locator("div.AppHeader-user");
    }

    public LoginPage goToLoginPage() {
        page.navigate(applicationProperties.loginUrl);
        return this;
    }

    public LoginPage setUserName(String userName) {
        userNameInput.fill(userName);
        return this;
    }

    public LoginPage setPassword(String userPassword) {
        userPasswordInput.fill(userPassword);
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

    public LoginPage submit() {
        signInButton.click();
        loggedUserIcon.waitFor();
        return this;
    }
}

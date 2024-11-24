package com.github.paczek224.qa.login.page;

public class LoginPageActions {

    private final LoginPage loginPage;
    private final LoginPageAssertions loginPageAssertions;

    public LoginPageActions(LoginPage loginPage, LoginPageAssertions loginPageAssertions) {
        this.loginPage = loginPage;
        this.loginPageAssertions = loginPageAssertions;
    }

    public LoginPageActions loginWithValidUser() {
        loginPage.navigateToLoginPage()
                .enterValidUserName()
                .enterValidPassword();

        return this;
    }
}

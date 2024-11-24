package com.github.paczek224.qa.login.page;

import com.github.paczek224.qa.common.assertions.BaseAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class LoginPageAssertions extends BaseAssertions {

    private final LoginPage loginPage;

    public LoginPageAssertions(LoginPage loginPage) {
        super(loginPage);
        this.loginPage = loginPage;
    }

    public LoginPageAssertions assertUserLoginDefaultState() {
        userInputIsDisabled();
        return this;
    }

    private LoginPageAssertions userInputIsDisabled() {
        softAssert(() -> PlaywrightAssertions.assertThat(loginPage.userNameInput).isEnabled());
        return this;
    }
}

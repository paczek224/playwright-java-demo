package com.github.paczek224.qa.common;

import com.github.paczek224.qa.common.configuration.ApplicationProperties;
import com.github.paczek224.qa.common.playwright.PlayWrightManager;
import com.github.paczek224.qa.login.manager.LoginPageManager;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Paths;

@Slf4j
@Getter
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class BaseTest extends SpringTestCase {

    private final PlayWrightManager playWrightManager;
    private final ApplicationProperties applicationProperties;
    protected LoginPageManager loginPageManager;
    protected BrowserContext context;
    protected Page page;

    private final String contextFilePath = String.format(".context/state-%s.json", this.getClass().getSimpleName());

    @Autowired
    public BaseTest(PlayWrightManager playWrightManager, ApplicationProperties applicationProperties) {
        this.playWrightManager = playWrightManager;
        this.applicationProperties = applicationProperties;
    }

    @BeforeAll
    void injectSystemProperties() {
        //
    }

    @BeforeAll
    void setupBeforeAllContextState() {
        createTestContextState(true);
    }

    @BeforeEach
    void setupBeforeEachContext() {
        context = playWrightManager.getBrowser().newContext(
                new Browser.NewContextOptions().setStorageStatePath(Paths.get(contextFilePath))
        );
        context.setDefaultTimeout(applicationProperties.getTimeOut());

        page = context.newPage();
        page.setDefaultTimeout(applicationProperties.getTimeOut());

        PlaywrightAssertions.setDefaultAssertionTimeout(applicationProperties.getTimeOut());

        loginPageManager = new LoginPageManager(applicationProperties, page);
        loginPageManager.onLoginPage().navigateToLoginPage();

        initPageManagers(page);
    }

    @AfterAll
    void closeBrowserAndRemoveContext(){
        playWrightManager.close();
        Paths.get(contextFilePath).toFile().delete();
    }

    protected abstract void initPageManagers(Page page);

    private void createTestContextState(boolean shouldLogin) {

        try (BrowserContext authContext = playWrightManager.getBrowser().newContext()) {
            Page authPage = authContext.newPage();

            if (shouldLogin) {
                PlaywrightAssertions.setDefaultAssertionTimeout(applicationProperties.getTimeOut());

                loginPageManager = new LoginPageManager(applicationProperties, authPage);
                loginPageManager.onLoginPage().navigateToLoginPage();
                loginPageManager.assertLogin().assertUserLoginDefaultState();
                loginPageManager.actions().loginWithValidUser();
            }

            authContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(contextFilePath)));
        }
    }
}

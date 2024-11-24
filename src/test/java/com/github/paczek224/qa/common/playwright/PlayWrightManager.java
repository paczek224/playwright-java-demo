package com.github.paczek224.qa.common.playwright;

import com.github.paczek224.qa.common.configuration.ApplicationProperties;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Getter
@Scope("prototype")
public class PlayWrightManager {

    private final Playwright playwright;
    private final Browser browser;
    private final Browser.NewContextOptions contextOptions;

    @Autowired
    public PlayWrightManager(ApplicationProperties applicationProperties) {
        this.playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(applicationProperties.isHeadlessMode());
        contextOptions = new Browser.NewContextOptions();

        switch (applicationProperties.getDeviceType().toLowerCase()) {
            case "pc":
                contextOptions
                        .setViewportSize(1280, 720)
                        .setDeviceScaleFactor(1)
                        .setScreenSize(1920, 1080)
                        .setIsMobile(false)
                        .setHasTouch(false);
                this.browser = this.playwright.chromium().launch(launchOptions);
                break;
/*
            case "iphone?":
*/
            default:
                throw new IllegalArgumentException("Unsupported device type " + applicationProperties.getDeviceType());
        }
    }

    public void close() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}

package com.github.paczek224.qa.common.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApplicationProperties {

    @Value("PC")
    private String deviceType;

    @Value("${browser.headless-mode}")
    private boolean headlessMode;

    @Value("${playwright.timeout}")
    public double timeOut;

    @Value("${application.login-url}")
    public String loginUrl;

    @Value("${user-credentials.login}")
    public String userName;

    @Value("${user-credentials.password}")
    public String userPassword;
}

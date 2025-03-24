package com.github.paczek224.qa.tests.repository.create.page;

import com.github.paczek224.qa.infrastructure.page.AbstractTestPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class CreateRepositoryPage extends AbstractTestPage {

    private final Locator repositoryNameInput;
    private final Locator descriptionInput;
    private final Locator createRepositorySubmitButton;
    private final Locator repositoryNameInputMessage;

    public CreateRepositoryPage(Page page) {
        super(page);

        repositoryNameInput = page.locator("//input[contains(@aria-describedby, 'RepoNameInput')]");
        repositoryNameInputMessage = page.locator("#RepoNameInput-message");
        descriptionInput = page.locator("input[name='Description']");
        createRepositorySubmitButton = page.locator("//button[normalize-space()='Create repository']");
    }

    public CreateRepositoryPage submit() {
        createRepositorySubmitButton.click();
        return this;
    }
}

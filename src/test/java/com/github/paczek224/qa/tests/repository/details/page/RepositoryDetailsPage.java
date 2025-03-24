package com.github.paczek224.qa.tests.repository.details.page;

import com.github.paczek224.qa.infrastructure.configuration.ApplicationProperties;
import com.github.paczek224.qa.infrastructure.page.AbstractTestPage;
import com.github.paczek224.qa.tests.repository.details.enums.CloneOption;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class RepositoryDetailsPage extends AbstractTestPage {

    private final ApplicationProperties applicationProperties;

    private final Locator breadCrumbId;
    private final Locator branchPicker;
    private final Locator branchesButtonLink;
    private final Locator codeButton;
    private final Locator clonePopup;

    public RepositoryDetailsPage(Page page, ApplicationProperties applicationProperties) {
        super(page);
        this.applicationProperties = applicationProperties;

        breadCrumbId = page.locator("#file-name-id");
        branchPicker = page.locator("#branch-picker-repos-header-ref-selector");
        codeButton = page.locator("//button[.//span[normalize-space()='Code']]");
        clonePopup = page.locator("div.react-overview-code-button-action-list");
        branchesButtonLink = page.locator("//a[contains(@href, 'branches') and @type='button' and .//span[contains(text(), 'Branches')]]");
    }

    public RepositoryDetailsPage goTo() {
        page.navigate(applicationProperties.repoUrl);
        return this;
    }

    public RepositoryDetailsPage openBranchPicker() {
        branchPicker.click();
        return this;
    }

    public RepositoryDetailsPage openBranchesView() {
        branchesButtonLink.click();
        return this;
    }

    public RepositoryDetailsPage setBranch(String branchName) {
        page.locator(String.format("//ul[@id='branches']//li[.//div[normalize-space()='%s']]", branchName)).click();
        return this;
    }

    public RepositoryDetailsPage openCodeButton() {
        codeButton.click();
        return this;
    }

    public RepositoryDetailsPage openTab(CloneOption cloneOption) {
        clonePopup.locator(String.format("//a[normalize-space()='%s']", cloneOption.getLabel())).click();
        return this;
    }

    public Locator getCloneInput() {
        return clonePopup.locator("input");
    }
}
